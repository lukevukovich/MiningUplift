package com.vuzili.uplift.init;

import com.vuzili.uplift.Uplift;
import com.vuzili.uplift.entities.StoneEntity;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntityTypes 
{
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = new DeferredRegister<>(ForgeRegistries.ENTITIES, Uplift.MOD_ID);

	public static final RegistryObject<EntityType<StoneEntity>> STONE_ENTITY = ENTITY_TYPES.register("stone_entity", () -> EntityType.Builder.<StoneEntity>create(StoneEntity::new, EntityClassification.MONSTER).size(1.0f, 1.0f).build(new ResourceLocation(Uplift.MOD_ID, "stone_entity").toString()));
	
}
