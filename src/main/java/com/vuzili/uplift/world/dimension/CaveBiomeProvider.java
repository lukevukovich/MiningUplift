package com.vuzili.uplift.world.dimension;

import java.util.Random;
import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.vuzili.uplift.init.BiomeInit;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;

public class CaveBiomeProvider extends BiomeProvider
{
	@SuppressWarnings("unused")
	private Random rand;

	protected CaveBiomeProvider() {
		super(biomeList);
		rand = new Random();
	}

	private static final Set<Biome> biomeList = ImmutableSet.of(BiomeInit.ORE_BIOME.get());
	
	@Override
	public Biome getNoiseBiome(int p_225526_1_, int p_225526_2_, int p_225526_3_) {
		return BiomeInit.ORE_BIOME.get();
	}

}
