package com.vuzili.uplift.world.gen;

import com.vuzili.uplift.Uplift;
import com.vuzili.uplift.init.BiomeInit;
import com.vuzili.uplift.init.ModEntityTypes;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = Uplift.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntitySpawns 
{

	@SubscribeEvent
	public static void spawnEntities(FMLLoadCompleteEvent event)
	{
		for(Biome biome : ForgeRegistries.BIOMES)
		{
			if (biome == BiomeInit.ORE_BIOME.get())
			{
				biome.getSpawns(EntityClassification.MONSTER).clear();
				biome.getSpawns(EntityClassification.AMBIENT).clear();
				biome.getSpawns(EntityClassification.CREATURE).clear();
				biome.getSpawns(EntityClassification.MISC).clear();
				biome.getSpawns(EntityClassification.WATER_CREATURE).clear();
				biome.getSpawns(EntityClassification.MONSTER).add(new Biome.SpawnListEntry(ModEntityTypes.STONE_ENTITY.get(), 32, 2, 4));
				biome.getSpawns(EntityClassification.AMBIENT).add(new Biome.SpawnListEntry(EntityType.BAT, 10, 6, 66));
			}
		}
	}
}
