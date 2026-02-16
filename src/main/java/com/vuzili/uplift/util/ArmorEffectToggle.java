package com.vuzili.uplift.util;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.server.ServerWorld;

/**
 * Manages per-player toggle state for armor potion effects.
 * When toggled OFF, armor still provides protection but grants no effects or particles.
 * Players toggle by double-sneaking while wearing a full matching armor set.
 *
 * Toggle state is persisted to the world save via {@link ArmorEffectToggleSavedData},
 * so it survives game restarts and world reloads.
 */
public class ArmorEffectToggle {

	// In-memory cache — mirrors the saved data for fast, side-agnostic access.
	private static final Set<UUID> DISABLED_PLAYERS = new HashSet<>();

	// Reference to the persistent backing store (null on client-only or before world load).
	private static ArmorEffectToggleSavedData savedData;

	/**
	 * Check if armor effects are enabled for this player.
	 * @return true if effects should be applied (default state)
	 */
	public static boolean areEffectsEnabled(PlayerEntity player) {
		return !DISABLED_PLAYERS.contains(player.getUniqueID());
	}

	/**
	 * Toggle effects on/off for a player. Sends an actionbar message and
	 * marks the saved data dirty so the change is written to disk.
	 */
	public static void toggle(PlayerEntity player) {
		UUID uuid = player.getUniqueID();
		if (DISABLED_PLAYERS.contains(uuid)) {
			DISABLED_PLAYERS.remove(uuid);
			if (savedData != null) {
				savedData.getDisabledPlayers().remove(uuid);
				savedData.markDirty();
			}
			player.sendStatusMessage(
				new StringTextComponent(TextFormatting.GREEN + "Armor Effects: ON"), true);
		} else {
			DISABLED_PLAYERS.add(uuid);
			if (savedData != null) {
				savedData.getDisabledPlayers().add(uuid);
				savedData.markDirty();
			}
			player.sendStatusMessage(
				new StringTextComponent(TextFormatting.RED + "Armor Effects: OFF"), true);
		}
	}

	/**
	 * Load persisted toggle state from the overworld's saved data.
	 * Called when the overworld finishes loading.
	 */
	public static void loadFromWorld(ServerWorld overworld) {
		savedData = ArmorEffectToggleSavedData.get(overworld);
		DISABLED_PLAYERS.clear();
		DISABLED_PLAYERS.addAll(savedData.getDisabledPlayers());
	}

	/**
	 * Clear runtime state when the server/world stops.
	 */
	public static void onWorldUnload() {
		DISABLED_PLAYERS.clear();
		savedData = null;
	}
}
