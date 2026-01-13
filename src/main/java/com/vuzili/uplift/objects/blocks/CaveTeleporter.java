package com.vuzili.uplift.objects.blocks;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import com.vuzili.uplift.Uplift;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.particles.RedstoneParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.concurrent.TickDelayedTask;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.GameType;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;

public class CaveTeleporter extends Block {
    private static final Map<UUID, BlockPos> lastPositions = new HashMap<>();
    private static final Map<UUID, DimensionType> lastDimensions = new HashMap<>();
    // Chunk-linking similar to Nether-style portal association
    private static final Map<Long, BlockPos> overworldToCaveLinksByChunk = new HashMap<>();
    private static final Map<Long, BlockPos> caveToOverworldLinksByChunk = new HashMap<>();
    private static final int LINK_SCALE = 1; // Coordinate scale factor (like Nether's 8), keep 1:1 by default
    
	private static final RedstoneParticleData TELEPORTER_PARTICLES = new RedstoneParticleData(0.0F, 0.0F, 0.0F, 1.0F);

    public CaveTeleporter(Properties properties) {
        super(properties);
    }
    
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        // Create a VoxelShape that represents the bottom half of the block.
        // This shapes a slab that occupies the bottom half of the block space, extending across the full 16x16 base but only 8 units high.
        VoxelShape bottomSlabShape = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D);
        return bottomSlabShape;
    }
    
    @Override
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		double d0 = (double) pos.getX() + 0.5D;
		double d1 = (double) pos.getY();
		double d2 = (double) pos.getZ() + 0.5D;
		if (rand.nextDouble() < 0.1D) {
			worldIn.playSound(d0, d1, d2, SoundEvents.BLOCK_BEACON_AMBIENT, SoundCategory.BLOCKS, 1.0F, 1.0F,
					false);
		}
		
        for (int i = 0; i < 3; i++) { // Increase the number of particles by iterating 10 times
            d0 = pos.getX() + 0.5D + (rand.nextDouble() - 0.5D) * 0.4D; // Widen the range
            d1 = pos.getY() + 0.7D + (i * .3D);
            d2 = pos.getZ() + 0.5D + (rand.nextDouble() - 0.5D) * 0.4D; // Widen the range
            worldIn.addParticle(TELEPORTER_PARTICLES, d0, d1, d2, 0.0D, 0.0D, 0.0D);
        }        
    }
    
    @SuppressWarnings("deprecation")
	@Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        // Retrieve the block state of the position directly above this block
        BlockState aboveState = worldIn.getBlockState(pos.up());
        BlockState aboveState2 = worldIn.getBlockState(pos.up(2));
        
        // If the block above is air (meaning no block is present), then return true (the position is valid)
        // This effectively means that while other blocks can't be placed directly on top,
        // your block can exist as long as there's nothing above it.
        if (aboveState.isAir(worldIn, pos.up()) && aboveState2.isAir(worldIn, pos.up())) {
            return super.isValidPosition(state, worldIn, pos);
        }
        
        // Otherwise, return false, preventing any block from being placed on top
        // or this block from being placed if something is already above it.
        return false;
    }

    
    @Override
    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
        if (!worldIn.isRemote && entityIn instanceof ServerPlayerEntity) {
            ServerPlayerEntity serverPlayer = (ServerPlayerEntity) entityIn;
            if (serverPlayer.timeUntilPortal <= 0) {                                
                
	            MinecraftServer minecraftServer = serverPlayer.getServer();
	            if (minecraftServer == null) return; // Server is not available	          
	            
	            UUID playerId = serverPlayer.getUniqueID();
	
	            if (serverPlayer.dimension == DimensionType.OVERWORLD) {
	                DimensionType targetDimension = DimensionType.byName(Uplift.UPLIFT_DIM_TYPE);
	                ServerWorld targetServerWorld = minecraftServer.getWorld(targetDimension);
	                if (targetServerWorld != null) {      	                    	                    	                    
	                    lastPositions.put(playerId, new BlockPos(serverPlayer.getPosX(), serverPlayer.getPosY(), serverPlayer.getPosZ()));
	                    lastDimensions.put(playerId, DimensionType.OVERWORLD);
                        // Persist the overworld teleporter block position for return after relog
                        saveLastOverworldTeleporterToPlayer(serverPlayer, pos);
                        // Determine a linked target position based on the teleporter/block position
                        BlockPos originalPos = pos;
                        BlockPos safePos = getOrCreateLinkedTargetPos((ServerWorld) worldIn, targetServerWorld, originalPos);
                        
                        targetServerWorld.getChunkProvider().forceChunk(new ChunkPos(safePos), false);
	                    GameType originalGameType = serverPlayer.interactionManager.getGameType(); // Save the original game mode

	                    if (originalGameType != GameType.CREATIVE) {
	                        serverPlayer.setGameType(GameType.CREATIVE); // Temporarily switch to Creative mode
	                    }
                        // Origin effects
                        playTeleportEffects((ServerWorld) worldIn, pos);
                        applyTeleportStatusEffects(serverPlayer);
	                    serverPlayer.teleport(targetServerWorld, safePos.getX(), safePos.getY(), safePos.getZ(), serverPlayer.rotationYaw, serverPlayer.rotationPitch);
                        // Destination effects
                        playTeleportEffects(targetServerWorld, safePos);
	                    
	                    if (originalGameType != GameType.CREATIVE) {
	                        // Schedule the original game mode to be restored after 2 seconds (40 ticks)
	                        scheduleGameModeChangeBack(serverPlayer, originalGameType, 40);
	                    }	                    
	                    serverPlayer.timeUntilPortal = 40;
	                    
	                }
	            } else if (serverPlayer.dimension == DimensionType.byName(Uplift.UPLIFT_DIM_TYPE)) {
	                DimensionType targetDimension = DimensionType.OVERWORLD; // Assuming you want to go back to the Overworld.
	                ServerWorld targetServerWorld = minecraftServer.getWorld(targetDimension);
	                if (targetServerWorld != null) {
                        // Prefer linked chunk return, fallback to saved last position
                        BlockPos lastPos = getLinkedReturnPos((ServerWorld) worldIn, pos, targetServerWorld);
                        if (lastPos == null) {
                            // Try persistent per-player data (survives relog/server restart)
                            BlockPos persisted = loadLastOverworldTeleporterFromPlayer(serverPlayer);
                            if (persisted != null) {
                                BlockPos nearTeleporter = findNearestTeleporterBlock(targetServerWorld, persisted, 64);
                                lastPos = (nearTeleporter != null) ? nearTeleporter : findSafePosition(targetServerWorld, persisted);
                                placeTeleporterIfPossible(targetServerWorld, lastPos);
                            } else {
                                lastPos = lastPositions.getOrDefault(playerId, targetServerWorld.getSpawnPoint());
                            }
                        }
	                    if (lastPos.equals(targetServerWorld.getSpawnPoint())) {
                    		clearBlocksAroundPlayer(targetServerWorld, new BlockPos(lastPos.getX(), lastPos.getY() + 1, lastPos.getZ()));
	                    }
	                    
	                    targetServerWorld.getChunkProvider().forceChunk(new ChunkPos(lastPos), false);
	                    GameType originalGameType = serverPlayer.interactionManager.getGameType(); // Save the original game mode

	                    if (originalGameType != GameType.CREATIVE) {
	                        serverPlayer.setGameType(GameType.CREATIVE); // Temporarily switch to Creative mode
	                    }
                        // Origin effects
                        playTeleportEffects((ServerWorld) worldIn, pos);
                        applyTeleportStatusEffects(serverPlayer);
	                    serverPlayer.teleport(targetServerWorld, lastPos.getX(), lastPos.getY(), lastPos.getZ(), serverPlayer.rotationYaw, serverPlayer.rotationPitch);
                        // Destination effects
                        playTeleportEffects(targetServerWorld, lastPos);
	                    
	                    if (originalGameType != GameType.CREATIVE) {
	                        // Schedule the original game mode to be restored after 2 seconds (40 ticks)
	                        scheduleGameModeChangeBack(serverPlayer, originalGameType, 40);
	                    }     
	                    serverPlayer.timeUntilPortal = 40;
	                    
	                    // Clean up after teleporting back
	                    lastPositions.remove(playerId);
	                    lastDimensions.remove(playerId);
	                }
	            }
            }
        }
    }
    
    private void scheduleGameModeChangeBack(ServerPlayerEntity player, GameType originalGameType, int delay) {
        MinecraftServer server = player.getServer();
        if (server != null) {
            server.enqueue(new TickDelayedTask(server.getTickCounter() + delay, () -> {
                if (player != null && player.isAlive()) {
                    player.setGameType(originalGameType); // Switch back to the original game mode
                }
            }));
        }
    }

    public void clearBlocksAroundPlayer(ServerWorld world, BlockPos playerPos) {

        // Define the radius around the player
        int radius = 1; // Clear blocks in a 1-block radius including corners

        // Iterate through the area around the player
        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    BlockPos blockToClear = playerPos.add(x, y, z);
                    // Set the block at the position to air, effectively clearing it
                    world.setBlockState(blockToClear, Blocks.AIR.getDefaultState());
                }
            }
        }
    }

    
    private BlockPos findSafePosition(World world, BlockPos originalPos) {
        // Set the Y level explicitly to 24, as it is the ground level of the dimension
        int yLevel = 24;

        // Start searching for a safe location at Y=24
        BlockPos basePos = new BlockPos(originalPos.getX(), yLevel, originalPos.getZ());

        // Check if the initial position is safe
        if (isLocationSafe(world, basePos, 3, 3)) {
            return basePos; // Position is safe
        }

        // If the initial position is not safe, search nearby for a safe location
        final int searchRadius = 1600; // Define how far to search from the initial position
        for (int dx = -searchRadius; dx <= searchRadius; dx++) {
            for (int dz = -searchRadius; dz <= searchRadius; dz++) {
                BlockPos newPos = new BlockPos(basePos.getX() + dx, yLevel, basePos.getZ() + dz);
                if (isLocationSafe(world, newPos, 3, 3)) {
                    return newPos; // Found a safe position
                }
            }
        }

        // If no safe position is found, fallback to the original base position
        // Consider adding logic to handle this case more gracefully
        return basePos;
    }

    boolean isLocationSafe(World world, BlockPos pos, int width, int height) {
        // Check ground solidity and overhead clearance at Y=24
        for (int x = -width / 2; x <= width / 2; x++) {
            for (int z = -width / 2; z <= width / 2; z++) {
                BlockPos groundPos = pos.add(x, -1, z);
                BlockPos airCheckPos = pos.add(x, 0, z);

                // Ensure the ground is solid
                if (!world.getBlockState(groundPos).getMaterial().isSolid()) {
                    return false;
                }

                // Check for adequate space and no hazardous materials above ground
                for (int y = 0; y < height; y++) {
                    BlockPos checkPos = airCheckPos.up(y);
                    BlockState state = world.getBlockState(checkPos);
                    if (!state.getMaterial().isReplaceable() || state.getMaterial().isLiquid()) {
                        return false;
                    }
                }
            }
        }

        // The location passed all checks
        return true;
    }

    // --- Linking and effects helpers ---
    private BlockPos getOrCreateLinkedTargetPos(ServerWorld originWorld, ServerWorld targetWorld, BlockPos originPos) {
        ChunkPos originChunk = new ChunkPos(originPos);
        long originKey = ChunkPos.asLong(originChunk.x, originChunk.z);

        BlockPos existing = overworldToCaveLinksByChunk.get(originKey);
        if (existing != null) {
            return existing;
        }

        int targetX = Math.floorDiv(originPos.getX(), LINK_SCALE);
        int targetZ = Math.floorDiv(originPos.getZ(), LINK_SCALE);
        BlockPos anchor = new BlockPos(targetX, 24, targetZ);

        // Prefer an existing teleporter block near the anchor
        BlockPos nearTeleporter = findNearestTeleporterBlock(targetWorld, anchor, 64);
        BlockPos safePos = (nearTeleporter != null) ? nearTeleporter : findSafePosition(targetWorld, anchor);

        // Ensure a teleporter block exists at the target if possible
        placeTeleporterIfPossible(targetWorld, safePos);

        // Record bi-directional links by chunk
        overworldToCaveLinksByChunk.put(originKey, safePos);
        long caveKey = ChunkPos.asLong(new ChunkPos(safePos).x, new ChunkPos(safePos).z);
        caveToOverworldLinksByChunk.put(caveKey, new BlockPos(originPos.getX(), originPos.getY(), originPos.getZ()));

        return safePos;
    }

    private BlockPos getLinkedReturnPos(ServerWorld caveWorld, BlockPos caveTeleporterPos, ServerWorld overworld) {
        ChunkPos caveChunk = new ChunkPos(caveTeleporterPos);
        long caveKey = ChunkPos.asLong(caveChunk.x, caveChunk.z);
        BlockPos linked = caveToOverworldLinksByChunk.get(caveKey);
        if (linked != null) {
            // If there's already a teleporter nearby, use it; otherwise find safe and optionally place one.
            BlockPos nearTeleporter = findNearestTeleporterBlock(overworld, linked, 64);
            BlockPos safe = (nearTeleporter != null) ? nearTeleporter : findSafePosition(overworld, linked);
            placeTeleporterIfPossible(overworld, safe);
            return safe;
        }
        return null;
    }

    private BlockPos findNearestTeleporterBlock(ServerWorld world, BlockPos center, int radius) {
        int r = Math.max(1, radius);
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        for (int yOff = -4; yOff <= 4; yOff++) {
            for (int dx = -r; dx <= r; dx++) {
                for (int dz = -r; dz <= r; dz++) {
                    mutable.setPos(center.getX() + dx, center.getY() + yOff, center.getZ() + dz);
                    BlockState s = world.getBlockState(mutable);
                    if (s.getBlock() == this) {
                        return mutable.toImmutable();
                    }
                }
            }
        }
        return null;
    }

    private void placeTeleporterIfPossible(ServerWorld world, BlockPos pos) {
        BlockState stateAt = world.getBlockState(pos);
        if ((stateAt.isAir(world, pos) || stateAt.getMaterial().isReplaceable()) && isLocationSafe(world, pos, 3, 3)) {
            world.setBlockState(pos, this.getDefaultState(), 3);
        }
    }

    private void playTeleportEffects(ServerWorld world, BlockPos pos) {
        world.playSound(null, pos, SoundEvents.AMBIENT_CAVE, SoundCategory.AMBIENT, 0.4F, 0.8F + world.rand.nextFloat() * 0.4F);
        world.playSound(null, pos, SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.PLAYERS, 0.5F, 1.0F);
        double cx = pos.getX() + 0.5;
        double cy = pos.getY() + 0.75;
        double cz = pos.getZ() + 0.5;
        world.spawnParticle(ParticleTypes.PORTAL, cx, cy, cz, 80, 0.6, 0.6, 0.6, 0.01);
        world.spawnParticle(ParticleTypes.ENCHANT, cx, cy, cz, 40, 0.4, 0.4, 0.4, 0.02);
    }

    private void applyTeleportStatusEffects(ServerPlayerEntity player) {
        player.addPotionEffect(new EffectInstance(Effects.NAUSEA, 120, 0, false, false));
        player.addPotionEffect(new EffectInstance(Effects.BLINDNESS, 40, 0, false, false));
        player.addPotionEffect(new EffectInstance(Effects.SLOW_FALLING, 60, 0, false, false));
    }

    // --- Persistent player data for last overworld teleporter ---
    private static final String NBT_UPLIFT_ROOT = "uplift";
    private static final String NBT_LAST_TP_X = "last_tp_x";
    private static final String NBT_LAST_TP_Y = "last_tp_y";
    private static final String NBT_LAST_TP_Z = "last_tp_z";

    private void saveLastOverworldTeleporterToPlayer(ServerPlayerEntity player, BlockPos pos) {
        if (player == null || pos == null) return;
        CompoundNBT root = player.getPersistentData();
        CompoundNBT uplift = root.getCompound(NBT_UPLIFT_ROOT);
        uplift.putInt(NBT_LAST_TP_X, pos.getX());
        uplift.putInt(NBT_LAST_TP_Y, pos.getY());
        uplift.putInt(NBT_LAST_TP_Z, pos.getZ());
        root.put(NBT_UPLIFT_ROOT, uplift);
    }

    private BlockPos loadLastOverworldTeleporterFromPlayer(ServerPlayerEntity player) {
        if (player == null) return null;
        CompoundNBT root = player.getPersistentData();
        if (!root.contains(NBT_UPLIFT_ROOT)) return null;
        CompoundNBT uplift = root.getCompound(NBT_UPLIFT_ROOT);
        if (!uplift.contains(NBT_LAST_TP_X) || !uplift.contains(NBT_LAST_TP_Y) || !uplift.contains(NBT_LAST_TP_Z)) {
            return null;
        }
        return new BlockPos(uplift.getInt(NBT_LAST_TP_X), uplift.getInt(NBT_LAST_TP_Y), uplift.getInt(NBT_LAST_TP_Z));
    }
}
