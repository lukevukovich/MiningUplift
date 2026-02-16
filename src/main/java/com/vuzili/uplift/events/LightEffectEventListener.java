package com.vuzili.uplift.events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.vuzili.uplift.init.BlockInit;
import com.vuzili.uplift.init.EffectInit;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = "uplift", bus = Bus.FORGE)
public class LightEffectEventListener {

	// Current active light block per player
	private static final Map<UUID, BlockPos> PLAYER_LIGHT_POSITIONS = new HashMap<>();

	// Old light blocks scheduled for delayed removal (position + tick countdown)
	private static final List<PendingRemoval> PENDING_REMOVALS = new ArrayList<>();

	// How often (in ticks) to update the light position. 20 ticks = 1 second.
	private static final int UPDATE_INTERVAL_TICKS = 20;

	// Per-player tick counter for time-based updates
	private static final Map<UUID, Integer> PLAYER_TICK_COUNTERS = new HashMap<>();

	// How many ticks to keep the old light block alive (overlap period).
	// 20 ticks = 1 full second of both lights active simultaneously.
	private static final int REMOVAL_DELAY_TICKS = 20;

	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase != TickEvent.Phase.START) {
			return;
		}
		PlayerEntity player = event.player;
		if (player.world.isRemote) {
			return;
		}

		boolean hasEffect = player.isPotionActive(EffectInit.LUMINOUS);
		UUID uuid = player.getUniqueID();

		// Always process pending removals
		tickPendingRemovals(player.world);

		if (hasEffect) {
			// Increment tick counter
			int counter = PLAYER_TICK_COUNTERS.getOrDefault(uuid, 0) + 1;

			// If no light placed yet, do it immediately; otherwise wait for interval
			boolean hasLight = PLAYER_LIGHT_POSITIONS.containsKey(uuid);
			if (!hasLight || counter >= UPDATE_INTERVAL_TICKS) {
				counter = 0;
				updateLightPosition(player, uuid);
			}

			PLAYER_TICK_COUNTERS.put(uuid, counter);
		} else {
			PLAYER_TICK_COUNTERS.remove(uuid);
			removeLightForPlayer(player.world, uuid);
		}
	}

	private static void updateLightPosition(PlayerEntity player, UUID uuid) {
		World world = player.world;
		BlockPos currentPos = PLAYER_LIGHT_POSITIONS.get(uuid);

		BlockPos feetPos = player.getPosition();
		BlockPos headPos = feetPos.up();

		// Choose the best position: prefer feet, then head
		BlockPos targetPos;
		if (canPlaceLight(world, feetPos)) {
			targetPos = feetPos;
		} else if (canPlaceLight(world, headPos)) {
			targetPos = headPos;
		} else {
			return;
		}

		if (currentPos != null && currentPos.equals(targetPos)) {
			return;
		}

		// Place new light (flag 2 = client sync only, no neighbor block updates)
		// Avoids triggering neighborChanged on adjacent blocks like cave portals
		world.setBlockState(targetPos, BlockInit.light_source.getDefaultState(), 2);

		// Schedule old light for delayed removal (keeps both lit during transition)
		if (currentPos != null && !currentPos.equals(targetPos)) {
			PENDING_REMOVALS.add(new PendingRemoval(uuid, currentPos, REMOVAL_DELAY_TICKS));
		}

		PLAYER_LIGHT_POSITIONS.put(uuid, targetPos);
	}

	private static void tickPendingRemovals(World world) {
		Iterator<PendingRemoval> it = PENDING_REMOVALS.iterator();
		while (it.hasNext()) {
			PendingRemoval pending = it.next();
			pending.ticksRemaining--;
			if (pending.ticksRemaining <= 0) {
				// Only remove if it's still our light block (not overwritten by something else)
				if (world.getBlockState(pending.pos).getBlock() == BlockInit.light_source) {
					// Make sure no player is currently using this position
					if (!PLAYER_LIGHT_POSITIONS.containsValue(pending.pos)) {
						world.setBlockState(pending.pos, Blocks.AIR.getDefaultState(), 2);
					}
				}
				it.remove();
			}
		}
	}

	private static void removeLightForPlayer(World world, UUID uuid) {
		BlockPos lastPos = PLAYER_LIGHT_POSITIONS.remove(uuid);
		if (lastPos != null && world.getBlockState(lastPos).getBlock() == BlockInit.light_source) {
			world.setBlockState(lastPos, Blocks.AIR.getDefaultState(), 2);
		}
	}

	private static void removeAllLightsForPlayer(World world, UUID uuid) {
		// Remove active light
		removeLightForPlayer(world, uuid);
		// Clean up only this player's pending removals
		Iterator<PendingRemoval> it = PENDING_REMOVALS.iterator();
		while (it.hasNext()) {
			PendingRemoval pending = it.next();
			if (pending.owner.equals(uuid)) {
				if (world.getBlockState(pending.pos).getBlock() == BlockInit.light_source) {
					if (!PLAYER_LIGHT_POSITIONS.containsValue(pending.pos)) {
						world.setBlockState(pending.pos, Blocks.AIR.getDefaultState(), 2);
					}
				}
				it.remove();
			}
		}
	}

	private static boolean canPlaceLight(World world, BlockPos pos) {
		BlockState state = world.getBlockState(pos);
		// Only place in true air or our own existing light block — never replace any other block
		if (state.getBlock() == BlockInit.light_source) {
			return true;
		}
		if (!state.isAir(world, pos) || state.getBlock() != Blocks.AIR) {
			return false;
		}
		// Don't place light adjacent to cave portal blocks (would break the portal structure)
		for (Direction dir : Direction.values()) {
			if (world.getBlockState(pos.offset(dir)).getBlock() == BlockInit.cave_portal) {
				return false;
			}
		}
		return true;
	}

	@SubscribeEvent
	public static void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent event) {
		PlayerEntity player = event.getPlayer();
		PLAYER_TICK_COUNTERS.remove(player.getUniqueID());
		removeAllLightsForPlayer(player.world, player.getUniqueID());
	}

	@SubscribeEvent
	public static void onPlayerDeath(net.minecraftforge.event.entity.living.LivingDeathEvent event) {
		if (event.getEntity() instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) event.getEntity();
			PLAYER_TICK_COUNTERS.remove(player.getUniqueID());
			removeAllLightsForPlayer(player.world, player.getUniqueID());
		}
	}

	@SubscribeEvent
	public static void onPlayerChangedDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
		PlayerEntity player = event.getPlayer();
		UUID uuid = player.getUniqueID();
		PLAYER_LIGHT_POSITIONS.remove(uuid);
		PLAYER_TICK_COUNTERS.remove(uuid);
		// Only remove this player's pending removals (not other players')
		Iterator<PendingRemoval> it = PENDING_REMOVALS.iterator();
		while (it.hasNext()) {
			PendingRemoval pending = it.next();
			if (pending.owner.equals(uuid)) {
				it.remove();
			}
		}
	}

	// Helper class to track blocks scheduled for delayed removal, with owner tracking
	private static class PendingRemoval {
		final UUID owner;
		final BlockPos pos;
		int ticksRemaining;

		PendingRemoval(UUID owner, BlockPos pos, int ticksRemaining) {
			this.owner = owner;
			this.pos = pos;
			this.ticksRemaining = ticksRemaining;
		}
	}
}
