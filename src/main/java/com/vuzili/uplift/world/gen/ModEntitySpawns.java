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
				biome.getSpawns(EntityClassification.MONSTER).add(new Biome.SpawnListEntry(EntityType.ZOMBIE,    8, 1, 3));
				biome.getSpawns(EntityClassification.MONSTER).add(new Biome.SpawnListEntry(EntityType.SKELETON, 8, 1, 3));
				biome.getSpawns(EntityClassification.MONSTER).add(new Biome.SpawnListEntry(EntityType.CREEPER,  6, 1, 2));
				biome.getSpawns(EntityClassification.MONSTER).add(new Biome.SpawnListEntry(EntityType.SPIDER,   6, 1, 2));
				biome.getSpawns(EntityClassification.MONSTER).add(new Biome.SpawnListEntry(ModEntityTypes.STONE_ENTITY.get(), 12, 2, 4));
				biome.getSpawns(EntityClassification.AMBIENT).add(new Biome.SpawnListEntry(EntityType.BAT, 10, 2, 4));
			}
		}
	}
}
