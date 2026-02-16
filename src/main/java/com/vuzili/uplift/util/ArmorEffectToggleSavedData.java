package com.vuzili.uplift.util;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.StringNBT;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraftforge.common.util.Constants;

/**
 * Persists the set of player UUIDs who have armor effects toggled OFF.
 * Stored in the overworld's data folder as "uplift_armor_toggle.dat".
 */
public class ArmorEffectToggleSavedData extends WorldSavedData {

	private static final String DATA_NAME = "uplift_armor_toggle";
	private final Set<UUID> disabledPlayers = new HashSet<>();

	public ArmorEffectToggleSavedData() {
		super(DATA_NAME);
	}

	public Set<UUID> getDisabledPlayers() {
		return disabledPlayers;
	}

	@Override
	public void read(CompoundNBT nbt) {
		disabledPlayers.clear();
		ListNBT list = nbt.getList("DisabledPlayers", Constants.NBT.TAG_STRING);
		for (int i = 0; i < list.size(); i++) {
			try {
				disabledPlayers.add(UUID.fromString(list.getString(i)));
			} catch (IllegalArgumentException ignored) {
				// Skip malformed UUIDs
			}
		}
	}

	@Override
	public CompoundNBT write(CompoundNBT compound) {
		ListNBT list = new ListNBT();
		for (UUID uuid : disabledPlayers) {
			list.add(StringNBT.valueOf(uuid.toString()));
		}
		compound.put("DisabledPlayers", list);
		return compound;
	}

	/**
	 * Get or create the saved data from the overworld.
	 */
	public static ArmorEffectToggleSavedData get(ServerWorld overworld) {
		return overworld.getSavedData().getOrCreate(ArmorEffectToggleSavedData::new, DATA_NAME);
	}
}
