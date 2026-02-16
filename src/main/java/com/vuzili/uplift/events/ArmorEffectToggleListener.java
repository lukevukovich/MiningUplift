package com.vuzili.uplift.events;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.vuzili.uplift.util.ArmorEffectToggle;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = "uplift", bus = Bus.FORGE)
public class ArmorEffectToggleListener {

	// Tracks the tick when each player last started sneaking
	private static final Map<UUID, Long> LAST_SNEAK_START = new HashMap<>();
	// Tracks whether the player was sneaking on the previous tick
	private static final Map<UUID, Boolean> WAS_SNEAKING = new HashMap<>();

	// Maximum ticks between two sneaks to count as a double-sneak (10 ticks = 0.5 sec)
	private static final int DOUBLE_SNEAK_WINDOW = 10;

	/**
	 * Detect double-sneak (press sneak twice quickly) while wearing a full matching armor set.
	 */
	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase != TickEvent.Phase.START) {
			return;
		}
		PlayerEntity player = event.player;
		if (player.world.isRemote) {
			return;
		}

		UUID uuid = player.getUniqueID();
		boolean isSneaking = player.isSneaking();
		boolean wasSneaking = WAS_SNEAKING.getOrDefault(uuid, false);
		WAS_SNEAKING.put(uuid, isSneaking);

		// Detect the moment the player STARTS sneaking (transition from not sneaking to sneaking)
		if (isSneaking && !wasSneaking) {
			long currentTick = player.world.getGameTime();
			Long lastStart = LAST_SNEAK_START.get(uuid);

			if (lastStart != null && (currentTick - lastStart) <= DOUBLE_SNEAK_WINDOW) {
				// Double-sneak detected — check for full matching armor set
				if (isWearingFullMatchingSet(player)) {
					if (player.abilities.isFlying) {
						// Prevent toggling while flying to avoid accidentally
						// disabling flight effect mid-air and falling to death
						player.sendStatusMessage(
								new StringTextComponent("Cannot toggle armor effects while flying!")
								.applyTextStyle(TextFormatting.RED), true);
					} else {
						ArmorEffectToggle.toggle(player);
					}
				}
				LAST_SNEAK_START.remove(uuid);
			} else {
				LAST_SNEAK_START.put(uuid, currentTick);
			}
		}
	}

	/**
	 * Check if the player is wearing a full set of armor where all 4 pieces
	 * share the same armor material (i.e. a matching set).
	 */
	private static boolean isWearingFullMatchingSet(PlayerEntity player) {
		ItemStack head = player.getItemStackFromSlot(EquipmentSlotType.HEAD);
		ItemStack chest = player.getItemStackFromSlot(EquipmentSlotType.CHEST);
		ItemStack legs = player.getItemStackFromSlot(EquipmentSlotType.LEGS);
		ItemStack feet = player.getItemStackFromSlot(EquipmentSlotType.FEET);

		if (head.isEmpty() || chest.isEmpty() || legs.isEmpty() || feet.isEmpty()) {
			return false;
		}

		if (!(head.getItem() instanceof ArmorItem) || !(chest.getItem() instanceof ArmorItem)
				|| !(legs.getItem() instanceof ArmorItem) || !(feet.getItem() instanceof ArmorItem)) {
			return false;
		}

		IArmorMaterial mat = ((ArmorItem) head.getItem()).getArmorMaterial();
		return ((ArmorItem) chest.getItem()).getArmorMaterial() == mat
				&& ((ArmorItem) legs.getItem()).getArmorMaterial() == mat
				&& ((ArmorItem) feet.getItem()).getArmorMaterial() == mat;
	}

	/**
	 * Clean up transient sneak-tracking state when player logs out.
	 * Toggle state is NOT cleared here — it persists via WorldSavedData.
	 */
	@SubscribeEvent
	public static void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent event) {
		UUID uuid = event.getPlayer().getUniqueID();
		LAST_SNEAK_START.remove(uuid);
		WAS_SNEAKING.remove(uuid);
	}

	/**
	 * Load persisted toggle state when the overworld loads.
	 */
	@SubscribeEvent
	public static void onWorldLoad(WorldEvent.Load event) {
		if (event.getWorld() instanceof ServerWorld) {
			ServerWorld serverWorld = (ServerWorld) event.getWorld();
			if (serverWorld.getDimension().getType() == DimensionType.OVERWORLD) {
				ArmorEffectToggle.loadFromWorld(serverWorld);
			}
		}
	}

	/**
	 * Clear runtime state when the overworld unloads (server stop / world change).
	 */
	@SubscribeEvent
	public static void onWorldUnload(WorldEvent.Unload event) {
		if (event.getWorld() instanceof ServerWorld) {
			ServerWorld serverWorld = (ServerWorld) event.getWorld();
			if (serverWorld.getDimension().getType() == DimensionType.OVERWORLD) {
				ArmorEffectToggle.onWorldUnload();
				LAST_SNEAK_START.clear();
				WAS_SNEAKING.clear();
			}
		}
	}
}
