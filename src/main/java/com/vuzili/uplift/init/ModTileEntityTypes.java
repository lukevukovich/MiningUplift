package com.vuzili.uplift.init;

import com.vuzili.uplift.Uplift;
import com.vuzili.uplift.tileentity.SmelterFurnaceTileEntity;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModTileEntityTypes {

	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = new DeferredRegister<>(
			ForgeRegistries.TILE_ENTITIES, Uplift.MOD_ID);
	
	public static final RegistryObject<TileEntityType<SmelterFurnaceTileEntity>> SMELTER_FURNACE = TILE_ENTITY_TYPES
			.register("smelter_furnace", () -> TileEntityType.Builder
					.create(SmelterFurnaceTileEntity::new, BlockInit.smelter.getBlock()).build(null));
	
}