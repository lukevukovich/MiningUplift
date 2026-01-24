package com.vuzili.uplift.objects.blocks;

import java.util.Random;

import com.vuzili.uplift.Uplift;
import com.vuzili.uplift.init.BlockInit;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.server.MinecraftServer;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.concurrent.TickDelayedTask;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import com.vuzili.uplift.world.CavePortalLinkData;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;

public class CavePortal extends Block {
    // Portal search radius - reduced from vanilla's 128 for faster searches
    // Linked portals use smaller radius (16) since we know approximate location
    private static final int PORTAL_SEARCH_RADIUS = 64;
    // Coordinate scale between dimensions (1:1 for cave dimension)
    private static final int LINK_SCALE = 1;
    
    public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.HORIZONTAL_AXIS;

    public CavePortal(Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(AXIS, Direction.Axis.X));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(AXIS);
    }
    
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        Direction facing = context.getPlacementHorizontalFacing();
        Direction.Axis axis = axisFromFacing(facing);
        return this.getDefaultState().with(AXIS, axis);
    }

    private Direction.Axis axisFromFacing(Direction facing) {
        return (facing == Direction.NORTH || facing == Direction.SOUTH) ? Direction.Axis.X : Direction.Axis.Z;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        Direction.Axis axis = state.get(AXIS);
        if (axis == Direction.Axis.X) {
            // Thin plane oriented on X (like Nether portal rotated)
            return Block.makeCuboidShape(0.0D, 0.0D, 7.5D, 16.0D, 16.0D, 8.5D);
        } else {
            // Thin plane oriented on Z
            return Block.makeCuboidShape(7.5D, 0.0D, 0.0D, 8.5D, 16.0D, 16.0D);
        }
    }

    @Override
    public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player) {
        // Return an empty stack to prevent picking the block
        return ItemStack.EMPTY;
    }
    
    @Override
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        double cx = pos.getX() + 0.5D;
        double cy = pos.getY() + 0.5D;
        double cz = pos.getZ() + 0.5D;
        
        // Only one block in a portal cluster should emit ambient sound: the bottom-left block
        Direction.Axis axis = stateIn.get(AXIS);
        Direction horizNeg = (axis == Direction.Axis.X) ? Direction.WEST : Direction.NORTH;
        
        // Check if this is the "leader" block (no portal below AND no portal in the negative horizontal direction)
        boolean hasPortalBelow = worldIn.getBlockState(pos.down()).getBlock() == this;
        boolean hasPortalHorizNeg = worldIn.getBlockState(pos.offset(horizNeg)).getBlock() == this;
        boolean isLeader = !hasPortalBelow && !hasPortalHorizNeg;
        
        // Only the leader plays sound (rarely)
        if (isLeader && rand.nextInt(50) == 0) {
            worldIn.playSound(cx, cy, cz, SoundEvents.BLOCK_PORTAL_AMBIENT, SoundCategory.BLOCKS, 0.3F, 0.8F, false);
        }
        
        // Black smoke particle effects
        // Large smoke rising up - spawn farther out horizontally to avoid getting stuck under blocks
        if (rand.nextInt(2) == 0) {
            double offsetX = (rand.nextDouble() - 0.5) * 2.0;
            double offsetZ = (rand.nextDouble() - 0.5) * 2.0;
            worldIn.addParticle(ParticleTypes.LARGE_SMOKE, cx + offsetX, cy + rand.nextDouble(), cz + offsetZ, 0.0D, 0.03D, 0.0D);
        }
        // Regular smoke - spawn farther out to prevent clipping
        if (rand.nextInt(2) == 0) {
            worldIn.addParticle(ParticleTypes.SMOKE, cx + (rand.nextDouble() - 0.5) * 1.6, cy + rand.nextDouble() * 0.8, cz + (rand.nextDouble() - 0.5) * 1.6, 0.0D, 0.02D, 0.0D);
        }
    }

    @Override
    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
        if (!worldIn.isRemote && entityIn instanceof ServerPlayerEntity) {
            ServerPlayerEntity serverPlayer = (ServerPlayerEntity) entityIn;
            if (serverPlayer.timeUntilPortal <= 0) {                                
                
	            MinecraftServer minecraftServer = serverPlayer.getServer();
	            if (minecraftServer == null) return;
	            
	            // Set cooldown immediately to prevent multiple triggers
	            serverPlayer.timeUntilPortal = 100;
	            
	            // Find the leader block of this portal cluster for consistent linking
	            BlockPos portalLeader = getPortalLeader((ServerWorld) worldIn, pos);
	
	            if (serverPlayer.dimension == DimensionType.OVERWORLD) {
	                teleportToUpliftDimension(serverPlayer, minecraftServer, (ServerWorld) worldIn, portalLeader);
	            } else if (serverPlayer.dimension == DimensionType.byName(Uplift.UPLIFT_DIM_TYPE)) {
	                teleportToOverworld(serverPlayer, minecraftServer, (ServerWorld) worldIn, portalLeader);
	            }
            }
        }
    }

    /**
     * Teleport player from Overworld to Uplift (cave) dimension.
     */
    private void teleportToUpliftDimension(ServerPlayerEntity player, MinecraftServer server, 
            ServerWorld sourceWorld, BlockPos sourcePortal) {
        DimensionType targetDimension = DimensionType.byName(Uplift.UPLIFT_DIM_TYPE);
        ServerWorld targetWorld = server.getWorld(targetDimension);
        if (targetWorld == null) return;
        
        // Save player's current position and portal info for failsafe return
        savePlayerPortalData(player, sourcePortal, sourceWorld.getDimension().getType());
        
        // Get or create linked destination portal
        BlockPos destPortal = findOrCreateDestinationPortal(sourceWorld, targetWorld, sourcePortal, player);
        
        // Force chunk loading
        targetWorld.getChunkProvider().forceChunk(new ChunkPos(destPortal), true);
        
        // Apply effects and play sounds
        applyTeleportStatusEffects(player);
        playTeleportEffects(sourceWorld, sourcePortal);
        
        // Teleport with delay to allow chunk to load
        final BlockPos finalDestPortal = destPortal;
        final Direction.Axis preferredAxis = axisFromFacing(player.getHorizontalFacing());
        server.enqueue(new TickDelayedTask(server.getTickCounter() + 1, () -> {
            // Ensure portal exists at destination
            ensurePortalExists(targetWorld, finalDestPortal, preferredAxis);
            
            // Update bi-directional links
            createBidirectionalLink(sourceWorld, sourcePortal, targetWorld, finalDestPortal);
            
            // Find safe position and teleport
            BlockPos teleportPos = findSafeTeleportPosition(targetWorld, finalDestPortal);
            player.teleport(targetWorld, teleportPos.getX() + 0.5, teleportPos.getY(), teleportPos.getZ() + 0.5, 
                    player.rotationYaw, player.rotationPitch);
            
            playTeleportEffects(targetWorld, finalDestPortal);
            player.sendPlayerAbilities();
        }));
    }

    /**
     * Teleport player from Uplift (cave) dimension back to Overworld.
     */
    private void teleportToOverworld(ServerPlayerEntity player, MinecraftServer server, 
            ServerWorld sourceWorld, BlockPos sourcePortal) {
        ServerWorld targetWorld = server.getWorld(DimensionType.OVERWORLD);
        if (targetWorld == null) return;
        
        // Try to find linked return portal
        BlockPos destPortal = findLinkedReturnPortal(sourceWorld, sourcePortal, targetWorld);
        
        // Fallback chain for return destination
        if (destPortal == null) {
            destPortal = tryLoadPlayerPortalData(player, targetWorld);
        }
        if (destPortal == null) {
            // Last resort: spawn point with safety clearing
            destPortal = targetWorld.getSpawnPoint();
            clearBlocksAroundPlayer(targetWorld, destPortal.up());
        }
        
        // Force chunk loading
        targetWorld.getChunkProvider().forceChunk(new ChunkPos(destPortal), true);
        
        // Apply effects
        applyTeleportStatusEffects(player);
        playTeleportEffects(sourceWorld, sourcePortal);
        
        // Teleport with delay
        final BlockPos finalDestPortal = destPortal;
        final Direction.Axis preferredAxis = axisFromFacing(player.getHorizontalFacing());
        server.enqueue(new TickDelayedTask(server.getTickCounter() + 1, () -> {
            // Ensure portal exists at destination for future use
            ensurePortalExists(targetWorld, finalDestPortal, preferredAxis);
            
            // Update bi-directional links
            createBidirectionalLink(sourceWorld, sourcePortal, targetWorld, finalDestPortal);
            
            // Find safe position and teleport
            BlockPos teleportPos = findSafeTeleportPosition(targetWorld, finalDestPortal);
            player.teleport(targetWorld, teleportPos.getX() + 0.5, teleportPos.getY(), teleportPos.getZ() + 0.5,
                    player.rotationYaw, player.rotationPitch);
            
            playTeleportEffects(targetWorld, finalDestPortal);
            player.sendPlayerAbilities();
        }));
    }

    /**
     * Find an existing linked portal or create a new one in the destination dimension.
     */
    private BlockPos findOrCreateDestinationPortal(ServerWorld sourceWorld, ServerWorld destWorld, 
            BlockPos sourcePortal, ServerPlayerEntity player) {
        // Check for existing link from this portal
        CavePortalLinkData sourceLinks = CavePortalLinkData.get(sourceWorld);
        BlockPos linkedDest = sourceLinks.getLinkedPortal(sourcePortal);
        
        if (linkedDest != null) {
            // Verify the linked portal still exists
            BlockPos existingPortal = findExistingPortalNear(destWorld, linkedDest, 16);
            if (existingPortal != null) {
                return getPortalLeader(destWorld, existingPortal);
            }
            // Link was stale - portal was destroyed, will create new one
        }
        
        // Calculate corresponding position in destination dimension
        int destX = Math.floorDiv(sourcePortal.getX(), LINK_SCALE);
        int destZ = Math.floorDiv(sourcePortal.getZ(), LINK_SCALE);
        int destY = 24; // Cave dimension ground level
        BlockPos searchCenter = new BlockPos(destX, destY, destZ);
        
        // Search for existing portal nearby (like vanilla Nether portal behavior)
        BlockPos existingPortal = findExistingPortalNear(destWorld, searchCenter, PORTAL_SEARCH_RADIUS);
        if (existingPortal != null) {
            return getPortalLeader(destWorld, existingPortal);
        }
        
        // No existing portal found, find safe position for new one
        return findSafePosition(destWorld, searchCenter);
    }

    /**
     * Find linked return portal or search for nearest portal in overworld.
     */
    private BlockPos findLinkedReturnPortal(ServerWorld caveWorld, BlockPos cavePortal, ServerWorld overworld) {
        // Check for existing link
        CavePortalLinkData caveLinks = CavePortalLinkData.get(caveWorld);
        BlockPos linkedReturn = caveLinks.getLinkedPortal(cavePortal);
        
        if (linkedReturn != null) {
            // Verify portal still exists
            BlockPos existingPortal = findExistingPortalNear(overworld, linkedReturn, 16);
            if (existingPortal != null) {
                return getPortalLeader(overworld, existingPortal);
            }
        }
        
        // Calculate corresponding overworld position
        int owX = cavePortal.getX() * LINK_SCALE;
        int owZ = cavePortal.getZ() * LINK_SCALE;
        BlockPos searchCenter = new BlockPos(owX, 64, owZ);
        
        // Search for existing portal
        BlockPos existingPortal = findExistingPortalNear(overworld, searchCenter, PORTAL_SEARCH_RADIUS);
        if (existingPortal != null) {
            return getPortalLeader(overworld, existingPortal);
        }
        
        return null;
    }

    /**
     * Create bi-directional links between two portals.
     */
    private void createBidirectionalLink(ServerWorld world1, BlockPos portal1, ServerWorld world2, BlockPos portal2) {
        BlockPos leader1 = getPortalLeader(world1, portal1);
        BlockPos leader2 = getPortalLeader(world2, portal2);
        
        CavePortalLinkData.get(world1).setLinkedPortal(leader1, leader2);
        CavePortalLinkData.get(world2).setLinkedPortal(leader2, leader1);
    }

    /**
     * Ensure a portal exists at the target position, creating one if necessary.
     */
    private void ensurePortalExists(ServerWorld world, BlockPos pos, Direction.Axis preferredAxis) {
        // Check if portal already exists nearby
        if (hasExistingPortalNearby(world, pos, 8)) {
            return;
        }
        
        // Try to light an existing frame first
        if (tryCreatePortal(world, pos)) {
            return;
        }
        
        // Build new frame and portal
        Direction.Axis axisChoice = preferredAxis;
        if (!isAreaClearForFrame(world, pos, preferredAxis)) {
            Direction.Axis alt = (preferredAxis == Direction.Axis.X) ? Direction.Axis.Z : Direction.Axis.X;
            if (isAreaClearForFrame(world, pos, alt)) {
                axisChoice = alt;
            }
        }
        clearCavityAround(world, pos);
        buildFrameAndPortal(world, pos, axisChoice);
    }

    /**
     * Search for an existing portal block within a radius.
     * Returns the position of any portal block found, or null.
     * Optimized: searches in expanding cubic shells and exits immediately when found.
     */
    private BlockPos findExistingPortalNear(ServerWorld world, BlockPos center, int radius) {
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        
        // Search in expanding cubic shells - check closest positions first
        for (int r = 0; r <= radius; r++) {
            // Only check the shell at distance r (not the interior)
            for (int dy = -Math.min(r, 12); dy <= Math.min(r, 12); dy++) {
                for (int dx = -r; dx <= r; dx++) {
                    for (int dz = -r; dz <= r; dz++) {
                        // Only check positions on the shell boundary
                        if (Math.abs(dx) != r && Math.abs(dz) != r && Math.abs(dy) != Math.min(r, 12)) continue;
                        
                        mutable.setPos(center.getX() + dx, center.getY() + dy, center.getZ() + dz);
                        if (world.getBlockState(mutable).getBlock() == this) {
                            return mutable.toImmutable(); // Return immediately on first find
                        }
                    }
                }
            }
        }
        return null;
    }
    
    // Removed temporary gamemode switching; teleport directly.

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

    private void clearCavityAround(ServerWorld world, BlockPos center) {
        int radiusX = 2;
        int radiusY = 3;
        int radiusZ = 2;
        for (int x = -radiusX; x <= radiusX; x++) {
            for (int y = 0; y <= radiusY; y++) {
                for (int z = -radiusZ; z <= radiusZ; z++) {
                    BlockPos p = center.add(x, y, z);
                    BlockState s = world.getBlockState(p);
                    if (!s.getMaterial().isLiquid()) {
                        world.setBlockState(p, Blocks.AIR.getDefaultState(), 3);
                    }
                }
            }
        }
    }

    
    private BlockPos findSafePosition(World world, BlockPos originalPos) {
        // Determine Y level based on dimension
        boolean isCaveDimension = world.getDimension().getType() == DimensionType.byName(Uplift.UPLIFT_DIM_TYPE);
        int yLevel = isCaveDimension ? 24 : originalPos.getY();
        
        // For overworld, search vertically for a safe spot
        if (!isCaveDimension) {
            yLevel = findSafeYLevel(world, originalPos.getX(), originalPos.getZ());
        }

        BlockPos basePos = new BlockPos(originalPos.getX(), yLevel, originalPos.getZ());

        // Check if the initial position is safe
        if (isLocationSafe(world, basePos, 3, 3)) {
            return basePos;
        }

        // Search in expanding squares (spiral pattern) - much faster than linear scan
        final int maxRadius = 64; // Reduced from 1600 - search nearby first
        for (int r = 1; r <= maxRadius; r++) {
            // Check positions on the perimeter of the square at radius r
            for (int i = -r; i <= r; i++) {
                // Top and bottom edges
                BlockPos top = new BlockPos(basePos.getX() + i, yLevel, basePos.getZ() - r);
                BlockPos bottom = new BlockPos(basePos.getX() + i, yLevel, basePos.getZ() + r);
                if (isLocationSafe(world, top, 3, 3)) return top;
                if (isLocationSafe(world, bottom, 3, 3)) return bottom;
                
                // Left and right edges (excluding corners already checked)
                if (i != -r && i != r) {
                    BlockPos left = new BlockPos(basePos.getX() - r, yLevel, basePos.getZ() + i);
                    BlockPos right = new BlockPos(basePos.getX() + r, yLevel, basePos.getZ() + i);
                    if (isLocationSafe(world, left, 3, 3)) return left;
                    if (isLocationSafe(world, right, 3, 3)) return right;
                }
            }
        }

        // If no safe position is found, fallback to the original base position
        // Consider adding logic to handle this case more gracefully
        return basePos;
    }

    /**
     * Find a safe Y level at the given X/Z coordinates in the overworld.
     * Searches from sky down to find solid ground with air above.
     */
    @SuppressWarnings("deprecation")
    private int findSafeYLevel(World world, int x, int z) {
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        // Start from a reasonable height and search downward
        for (int y = 255; y > 1; y--) {
            mutable.setPos(x, y, z);
            BlockState state = world.getBlockState(mutable);
            BlockState below = world.getBlockState(mutable.down());
            
            // Found air with solid ground below
            if (state.isAir() && below.getMaterial().isSolid() && !below.getMaterial().isLiquid()) {
                // Verify there's headroom
                BlockState above = world.getBlockState(mutable.up());
                if (above.isAir()) {
                    return y;
                }
            }
        }
        return 64; // Default fallback
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

    /**
     * Get the "leader" block of a portal cluster (bottom-left block).
     * This provides a consistent reference point for linking portals.
     */
    private BlockPos getPortalLeader(ServerWorld world, BlockPos anyPortal) {
        BlockPos start = anyPortal;
        if (world.getBlockState(start).getBlock() != this) {
            BlockPos found = findExistingPortalNear(world, anyPortal, 12);
            if (found == null) return anyPortal;
            start = found;
        }
        // Descend to bottom of cluster
        while (world.getBlockState(start.down()).getBlock() == this) {
            start = start.down();
        }
        BlockState s = world.getBlockState(start);
        Direction.Axis axis = (s.getBlock() == this) ? s.get(AXIS) : Direction.Axis.X;
        Direction leftDir = (axis == Direction.Axis.X) ? Direction.NORTH : Direction.WEST;
        // Move to the negative horizontal edge
        while (world.getBlockState(start.offset(leftDir)).getBlock() == this) {
            start = start.offset(leftDir);
        }
        return start;
    }

    private boolean hasExistingPortalNearby(ServerWorld world, BlockPos center, int radius) {
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        for (int dx = -radius; dx <= radius; dx++) {
            for (int dy = -radius; dy <= radius; dy++) {
                for (int dz = -radius; dz <= radius; dz++) {
                    mutable.setPos(center.getX() + dx, center.getY() + dy, center.getZ() + dz);
                    if (world.getBlockState(mutable).getBlock() == BlockInit.cave_portal) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean isAreaClearForFrame(IWorld world, BlockPos innerOrigin, Direction.Axis axis) {
        Direction horizA = (axis == Direction.Axis.X) ? Direction.EAST : Direction.NORTH;
        // Check vertical sides clearance
        for (int h = 0; h < 3; h++) {
            BlockPos left = innerOrigin.offset(horizA, -1).up(h);
            BlockPos right = innerOrigin.offset(horizA, 2).up(h);
            if (!isAirOrReplaceable(world, left) || !isAirOrReplaceable(world, right)) return false;
        }
        // Check horizontal top and bottom clearance
        for (int w = -1; w <= 2; w++) {
            BlockPos bottom = innerOrigin.offset(horizA, w).down(1);
            BlockPos top = innerOrigin.offset(horizA, w).up(3);
            if (!isAirOrReplaceable(world, bottom) || !isAirOrReplaceable(world, top)) return false;
        }
        // Interior clearance
        for (int w = 0; w < 2; w++) {
            for (int h = 0; h < 3; h++) {
                BlockPos p = innerOrigin.offset(horizA, w).up(h);
                if (!isAirOrReplaceable(world, p)) return false;
            }
        }
        return true;
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

    /**
     * Find a safe position for the player to teleport to, offset from the portal block
     * so they don't immediately re-enter the portal.
     */
    @SuppressWarnings("deprecation")
    private BlockPos findSafeTeleportPosition(ServerWorld world, BlockPos portalPos) {
        // Find the portal's axis to determine which direction to offset
        BlockState portalState = world.getBlockState(portalPos);
        Direction.Axis axis = Direction.Axis.X;
        if (portalState.getBlock() == this) {
            axis = portalState.get(AXIS);
        }
        
        // Offset perpendicular to the portal plane
        Direction[] offsetDirs;
        if (axis == Direction.Axis.X) {
            // Portal faces X axis, so offset on Z axis
            offsetDirs = new Direction[]{Direction.NORTH, Direction.SOUTH};
        } else {
            // Portal faces Z axis, so offset on X axis
            offsetDirs = new Direction[]{Direction.WEST, Direction.EAST};
        }
        
        // Find the lowest portal block in the cluster to get ground level
        BlockPos groundPortal = portalPos;
        while (world.getBlockState(groundPortal.down()).getBlock() == this) {
            groundPortal = groundPortal.down();
        }
        
        // Try each direction to find a safe spot
        for (Direction dir : offsetDirs) {
            BlockPos candidate = groundPortal.offset(dir, 2);
            // Check if there's solid ground below and air above
            if (world.getBlockState(candidate.down()).getMaterial().isSolid() &&
                world.getBlockState(candidate).isAir() &&
                world.getBlockState(candidate.up()).isAir()) {
                return candidate;
            }
        }
        
        // Fallback: try offset by 1 block
        for (Direction dir : offsetDirs) {
            BlockPos candidate = groundPortal.offset(dir, 1);
            if (world.getBlockState(candidate.down()).getMaterial().isSolid() &&
                world.getBlockState(candidate).isAir() &&
                world.getBlockState(candidate.up()).isAir()) {
                return candidate;
            }
        }
        
        // Last resort: return the portal position but at ground level
        return groundPortal;
    }

    // --- Persistent player data for failsafe portal recovery ---
    private static final String NBT_UPLIFT_ROOT = "uplift";
    private static final String NBT_LAST_PORTAL_X = "last_portal_x";
    private static final String NBT_LAST_PORTAL_Y = "last_portal_y";
    private static final String NBT_LAST_PORTAL_Z = "last_portal_z";
    private static final String NBT_LAST_DIM = "last_dimension";

    /**
     * Save the player's portal usage data for failsafe recovery.
     * This persists across server restarts and game crashes.
     */
    private void savePlayerPortalData(ServerPlayerEntity player, BlockPos portalPos, DimensionType fromDimension) {
        if (player == null || portalPos == null) return;
        CompoundNBT root = player.getPersistentData();
        CompoundNBT uplift = root.getCompound(NBT_UPLIFT_ROOT);
        
        // Save the portal position the player entered
        uplift.putInt(NBT_LAST_PORTAL_X, portalPos.getX());
        uplift.putInt(NBT_LAST_PORTAL_Y, portalPos.getY());
        uplift.putInt(NBT_LAST_PORTAL_Z, portalPos.getZ());
        
        // Save which dimension they came from
        String dimName = (fromDimension == DimensionType.OVERWORLD) ? "overworld" : "uplift";
        uplift.putString(NBT_LAST_DIM, dimName);
        
        root.put(NBT_UPLIFT_ROOT, uplift);
    }

    /**
     * Try to load the player's last portal data and find a valid return portal.
     * Returns the portal position if found, or searches for a nearby portal.
     */
    private BlockPos tryLoadPlayerPortalData(ServerPlayerEntity player, ServerWorld targetWorld) {
        if (player == null) return null;
        CompoundNBT root = player.getPersistentData();
        if (!root.contains(NBT_UPLIFT_ROOT)) return null;
        
        CompoundNBT uplift = root.getCompound(NBT_UPLIFT_ROOT);
        if (!uplift.contains(NBT_LAST_PORTAL_X)) return null;
        
        BlockPos lastPortal = new BlockPos(
            uplift.getInt(NBT_LAST_PORTAL_X),
            uplift.getInt(NBT_LAST_PORTAL_Y),
            uplift.getInt(NBT_LAST_PORTAL_Z)
        );
        
        // Search for the portal or a nearby one
        BlockPos foundPortal = findExistingPortalNear(targetWorld, lastPortal, PORTAL_SEARCH_RADIUS);
        if (foundPortal != null) {
            return getPortalLeader(targetWorld, foundPortal);
        }
        
        // Portal not found - find a safe position near the last known location
        return findSafePosition(targetWorld, lastPortal);
    }

    /**
     * Clear player's portal data (e.g., after successful return to overworld).
     */
    @SuppressWarnings("unused")
    private void clearPlayerPortalData(ServerPlayerEntity player) {
        if (player == null) return;
        CompoundNBT root = player.getPersistentData();
        if (root.contains(NBT_UPLIFT_ROOT)) {
            root.remove(NBT_UPLIFT_ROOT);
        }
    }

    // --- Portal creation (Nether-like with variable sizes) ---
    // Portal size limits (same as Nether portal)
    private static final int MIN_WIDTH = 2;
    private static final int MIN_HEIGHT = 3;
    private static final int MAX_WIDTH = 21;
    private static final int MAX_HEIGHT = 21;
    
    public static boolean tryCreatePortal(IWorld iworld, BlockPos ignitionPos) {
        // Only allow portal creation in Overworld and Cave dimension
        if (iworld instanceof World) {
            World world = (World) iworld;
            DimensionType dimType = world.getDimension().getType();
            boolean isOverworld = dimType == DimensionType.OVERWORLD;
            boolean isCaveDimension = dimType == DimensionType.byName(Uplift.UPLIFT_DIM_TYPE);
            if (!isOverworld && !isCaveDimension) {
                return false;
            }
        }
        // Attempt both axis orientations around the ignition position
        return tryCreatePortalWithAxis(iworld, ignitionPos, Direction.Axis.X) || tryCreatePortalWithAxis(iworld, ignitionPos, Direction.Axis.Z);
    }

    private static boolean isGemstoneFrame(IWorld world, BlockPos pos) {
        BlockState s = world.getBlockState(pos);
        return s.getBlock() == BlockInit.gemstone;
    }

    @SuppressWarnings("deprecation")
    private static boolean isAirOrReplaceable(IWorld world, BlockPos pos) {
        BlockState s = world.getBlockState(pos);
        return s.isAir() || s.getMaterial().isReplaceable();
    }

    @SuppressWarnings("deprecation")
    private static boolean isAirOrReplaceableOrPortal(IWorld world, BlockPos pos) {
        BlockState s = world.getBlockState(pos);
        return s.isAir() || s.getMaterial().isReplaceable() || s.getBlock() == BlockInit.cave_portal;
    }

    private static boolean tryCreatePortalWithAxis(IWorld world, BlockPos center, Direction.Axis axis) {
        Direction horizDir = (axis == Direction.Axis.X) ? Direction.EAST : Direction.NORTH;
        // Only attempt to create a portal from the exact interior position provided
        if (!isAirOrReplaceableOrPortal(world, center)) {
            return false;
        }
        PortalSize size = findPortalSize(world, center, horizDir, axis);
        if (size != null && size.isValid()) {
            fillVariablePortal(world, size, axis);
            return true;
        }
        return false;
    }
    
    /**
     * Represents a detected portal frame size and position
     */
    private static class PortalSize {
        final BlockPos bottomLeft;
        final int width;
        final int height;
        final Direction horizDir;
        
        PortalSize(BlockPos bottomLeft, int width, int height, Direction horizDir) {
            this.bottomLeft = bottomLeft;
            this.width = width;
            this.height = height;
            this.horizDir = horizDir;
        }
        
        boolean isValid() {
            return width >= MIN_WIDTH && width <= MAX_WIDTH && height >= MIN_HEIGHT && height <= MAX_HEIGHT;
        }
    }
    
    private static PortalSize findPortalSize(IWorld world, BlockPos startPos, Direction horizDir, Direction.Axis axis) {
        // Find the left edge (gemstone pillar)
        BlockPos bottomLeft = findBottomLeftCorner(world, startPos, horizDir);
        if (bottomLeft == null) return null;
        
        // Measure width (interior space between pillars)
        int width = measureWidth(world, bottomLeft, horizDir);
        if (width < MIN_WIDTH || width > MAX_WIDTH) return null;
        
        // Measure height (interior space between floor and ceiling)
        int height = measureHeight(world, bottomLeft, horizDir, width);
        if (height < MIN_HEIGHT || height > MAX_HEIGHT) return null;
        
        // Verify the complete frame
        if (!verifyFrame(world, bottomLeft, horizDir, width, height)) return null;
        
        return new PortalSize(bottomLeft, width, height, horizDir);
    }
    
    private static BlockPos findBottomLeftCorner(IWorld world, BlockPos start, Direction horizDir) {
        // Move down to find the bottom (with limit to prevent infinite loop)
        BlockPos pos = start;
        int maxSearch = MAX_HEIGHT + 2;
        int searched = 0;
        while (isAirOrReplaceableOrPortal(world, pos.down()) && !isGemstoneFrame(world, pos.down())) {
            pos = pos.down();
            searched++;
            if (pos.getY() < 0 || searched > maxSearch) return null;
        }
        
        // Check we're on the floor (gemstone below)
        if (!isGemstoneFrame(world, pos.down())) return null;
        
        // Move left to find the left pillar (with limit)
        Direction leftDir = horizDir.getOpposite();
        searched = 0;
        while (isAirOrReplaceableOrPortal(world, pos.offset(leftDir))) {
            pos = pos.offset(leftDir);
            searched++;
            if (searched > MAX_WIDTH + 2) return null;
        }
        
        // Check we're at the left pillar (gemstone to the left)
        if (!isGemstoneFrame(world, pos.offset(leftDir))) return null;
        
        return pos;
    }
    
    private static int measureWidth(IWorld world, BlockPos bottomLeft, Direction horizDir) {
        int width = 0;
        BlockPos pos = bottomLeft;
        
        while (width < MAX_WIDTH + 1) {
            if (isGemstoneFrame(world, pos)) {
                // Hit the right pillar
                break;
            }
            if (!isAirOrReplaceableOrPortal(world, pos)) {
                return 0; // Invalid - something blocking
            }
            width++;
            pos = pos.offset(horizDir);
        }
        
        return width;
    }
    
    private static int measureHeight(IWorld world, BlockPos bottomLeft, Direction horizDir, int width) {
        int height = 0;
        BlockPos pos = bottomLeft;
        
        while (height < MAX_HEIGHT + 1) {
            // Check the entire row is clear
            boolean rowClear = true;
            for (int w = 0; w < width; w++) {
                BlockPos checkPos = pos.offset(horizDir, w);
                if (isGemstoneFrame(world, checkPos)) {
                    // Hit the ceiling
                    return height;
                }
                if (!isAirOrReplaceableOrPortal(world, checkPos)) {
                    rowClear = false;
                    break;
                }
            }
            if (!rowClear) return 0;
            
            height++;
            pos = pos.up();
        }
        
        return 0; // Too tall
    }
    
    private static boolean verifyFrame(IWorld world, BlockPos bottomLeft, Direction horizDir, int width, int height) {
        Direction leftDir = horizDir.getOpposite();
        
        // Check left pillar
        for (int h = 0; h < height; h++) {
            if (!isGemstoneFrame(world, bottomLeft.offset(leftDir).up(h))) return false;
        }
        
        // Check right pillar
        for (int h = 0; h < height; h++) {
            if (!isGemstoneFrame(world, bottomLeft.offset(horizDir, width).up(h))) return false;
        }
        
        // Check bottom row (corners optional, just check under the interior)
        for (int w = 0; w < width; w++) {
            if (!isGemstoneFrame(world, bottomLeft.offset(horizDir, w).down())) return false;
        }
        
        // Check top row (corners optional, just check above the interior)
        for (int w = 0; w < width; w++) {
            if (!isGemstoneFrame(world, bottomLeft.offset(horizDir, w).up(height))) return false;
        }
        
        // Verify interior is clear
        for (int w = 0; w < width; w++) {
            for (int h = 0; h < height; h++) {
                if (!isAirOrReplaceableOrPortal(world, bottomLeft.offset(horizDir, w).up(h))) return false;
            }
        }
        
        return true;
    }
    
    private static void fillVariablePortal(IWorld world, PortalSize size, Direction.Axis axis) {
        BlockState portal = BlockInit.cave_portal.getDefaultState().with(AXIS, axis);
        for (int w = 0; w < size.width; w++) {
            for (int h = 0; h < size.height; h++) {
                BlockPos p = size.bottomLeft.offset(size.horizDir, w).up(h);
                world.setBlockState(p, portal, 3);
            }
        }
    }

    private static void buildFrameAndPortal(ServerWorld world, BlockPos innerOrigin, Direction.Axis axis) {
        Direction horizA = (axis == Direction.Axis.X) ? Direction.EAST : Direction.NORTH;
        // Place frame (gemstone) around a 2x3 interior starting at innerOrigin
        for (int h = 0; h < 3; h++) {
            BlockPos left = innerOrigin.offset(horizA, -1).up(h);
            BlockPos right = innerOrigin.offset(horizA, 2).up(h);
            world.setBlockState(left, BlockInit.gemstone.getDefaultState(), 3);
            world.setBlockState(right, BlockInit.gemstone.getDefaultState(), 3);
        }
        for (int w = -1; w <= 2; w++) {
            BlockPos bottom = innerOrigin.offset(horizA, w).down(1);
            BlockPos top = innerOrigin.offset(horizA, w).up(3);
            world.setBlockState(bottom, BlockInit.gemstone.getDefaultState(), 3);
            world.setBlockState(top, BlockInit.gemstone.getDefaultState(), 3);
        }
        // Fill interior with portal (2 wide x 3 tall)
        BlockState portal = BlockInit.cave_portal.getDefaultState().with(AXIS, axis);
        for (int w = 0; w < 2; w++) {
            for (int h = 0; h < 3; h++) {
                BlockPos p = innerOrigin.offset(horizA, w).up(h);
                world.setBlockState(p, portal, 3);
            }
        }
    }

    // --- Portal break handling: remove connected portal blocks when one breaks ---
    @SuppressWarnings("deprecation")
    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        super.onReplaced(state, worldIn, pos, newState, isMoving);
        if (!worldIn.isRemote && state.getBlock() == this && state != newState) {
            // Clean up portal links when portal is destroyed
            if (worldIn instanceof ServerWorld) {
                ServerWorld serverWorld = (ServerWorld) worldIn;
                BlockPos leader = getPortalLeader(serverWorld, pos);
                CavePortalLinkData linkData = CavePortalLinkData.get(serverWorld);
                BlockPos linkedPortal = linkData.getLinkedPortal(leader);
                if (linkedPortal != null) {
                    // Remove the link from this side
                    linkData.removeLink(leader);
                    // Also remove the reverse link in the other dimension
                    DimensionType otherDim = (serverWorld.getDimension().getType() == DimensionType.OVERWORLD) 
                        ? DimensionType.byName(Uplift.UPLIFT_DIM_TYPE) 
                        : DimensionType.OVERWORLD;
                    ServerWorld otherWorld = serverWorld.getServer().getWorld(otherDim);
                    if (otherWorld != null) {
                        CavePortalLinkData.get(otherWorld).removeLink(linkedPortal);
                    }
                }
            }
            // Remove all connected portal blocks in this cluster
            breakConnectedPortal(worldIn, pos);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        super.neighborChanged(state, worldIn, pos, blockIn, fromPos, isMoving);
        if (!worldIn.isRemote) {
            // Only check validity when a non-portal block changed (e.g., gemstone frame broken)
            // This prevents breaking during portal creation when portal blocks are being placed
            BlockState fromState = worldIn.getBlockState(fromPos);
            if (fromState.getBlock() != this) {
                // A non-portal block changed - check if the frame is still valid
                if (!isPortalStillValid(worldIn, pos)) {
                    breakConnectedPortal(worldIn, pos);
                }
            }
        }
    }

    private boolean isPortalStillValid(World world, BlockPos portalPos) {
        // A portal block is valid only if it has gemstone on BOTH horizontal sides (left AND right pillars)
        // OR if it's connected to another portal block that has valid frame support
        Direction.Axis axis = Direction.Axis.X;
        BlockState portalState = world.getBlockState(portalPos);
        if (portalState.getBlock() == this) {
            axis = portalState.get(AXIS);
        }
        
        // Determine the horizontal directions based on portal axis
        Direction side1, side2;
        if (axis == Direction.Axis.X) {
            side1 = Direction.NORTH;
            side2 = Direction.SOUTH;
        } else {
            side1 = Direction.WEST;
            side2 = Direction.EAST;
        }
        
        // Check if this portal block has frame support on at least one side
        BlockState s1 = world.getBlockState(portalPos.offset(side1));
        BlockState s2 = world.getBlockState(portalPos.offset(side2));
        
        boolean hasSide1Support = s1.getBlock() == BlockInit.gemstone || s1.getBlock() == this;
        boolean hasSide2Support = s2.getBlock() == BlockInit.gemstone || s2.getBlock() == this;
        
        // Portal is valid if it has support on both perpendicular sides
        // (either gemstone frame or another portal block that might have support)
        if (!hasSide1Support || !hasSide2Support) {
            return false;
        }
        
        // Also check vertical support - must have gemstone or portal above AND below OR be in middle
        BlockState above = world.getBlockState(portalPos.up());
        BlockState below = world.getBlockState(portalPos.down());
        boolean hasAboveSupport = above.getBlock() == BlockInit.gemstone || above.getBlock() == this;
        boolean hasBelowSupport = below.getBlock() == BlockInit.gemstone || below.getBlock() == this;
        
        return hasAboveSupport || hasBelowSupport;
    }

    private void breakConnectedPortal(World world, BlockPos start) {
        Block block = this;
        java.util.ArrayDeque<BlockPos> deque = new java.util.ArrayDeque<>();
        java.util.HashSet<BlockPos> visited = new java.util.HashSet<>();
        deque.add(start);
        boolean playedSound = false;
        while (!deque.isEmpty()) {
            BlockPos p = deque.poll();
            if (!visited.add(p)) continue;
            BlockState s = world.getBlockState(p);
            if (s.getBlock() == block) {
                // Play glass breaking sound once for the first block
                if (!playedSound) {
                    world.playSound(null, p, SoundEvents.BLOCK_GLASS_BREAK, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    playedSound = true;
                }
                world.destroyBlock(p, false);
                for (Direction d : Direction.values()) {
                    deque.add(p.offset(d));
                }
            }
        }
    }

    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, net.minecraft.entity.player.PlayerEntity player) {
        super.onBlockHarvested(worldIn, pos, state, player);
        if (!worldIn.isRemote) {
            breakConnectedPortal(worldIn, pos);
        }
    }
}
