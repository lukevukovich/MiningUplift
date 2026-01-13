package com.vuzili.uplift.world.biomes;


import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.ProbabilityConfig;

public class OreBiome extends Biome
{

	public OreBiome(Builder biomeBuilder) 
	{
		
		super(biomeBuilder);
		this.addCarver(GenerationStage.Carving.AIR, Biome.createCarver(WorldCarver.CAVE, new ProbabilityConfig(0.14285715F)));

	}
	
}