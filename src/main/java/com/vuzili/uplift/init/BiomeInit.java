package com.vuzili.uplift.init;

import com.vuzili.uplift.Uplift;
import com.vuzili.uplift.world.biomes.OreBiome;

import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biome.RainType;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BiomeInit {
	public static final DeferredRegister<Biome> BIOMES = new DeferredRegister<>(ForgeRegistries.BIOMES, Uplift.MOD_ID);

	public static final RegistryObject<Biome> ORE_BIOME = BIOMES.register("ore_biome",
			() -> new OreBiome(new Biome.Builder().precipitation(RainType.NONE).scale(1.2f).temperature(0.3f)
					.waterColor(16741887).waterFogColor(16741887)
					.surfaceBuilder(SurfaceBuilder.DEFAULT,
							new SurfaceBuilderConfig(Blocks.STONE.getDefaultState(), Blocks.STONE.getDefaultState(),
									Blocks.STONE.getDefaultState()))
					.category(Category.PLAINS).downfall(0.0f).depth(0.0f).parent(null)));
	

	public static void registerBiomes() 
	{

	}

	/*
	private static void registerBiome(Biome biome, Type... types) 
	{

	}
	*/
	
}