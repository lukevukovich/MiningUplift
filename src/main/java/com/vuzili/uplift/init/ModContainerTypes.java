package com.vuzili.uplift.init;

import com.vuzili.uplift.Uplift;
import com.vuzili.uplift.container.SmelterFurnaceContainer;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModContainerTypes {

	public static final DeferredRegister<ContainerType<?>> CONTAINER_TYPES = new DeferredRegister<>(ForgeRegistries.CONTAINERS, Uplift.MOD_ID);
	
	public static final RegistryObject<ContainerType<SmelterFurnaceContainer>> SMELTER_FURNACE = CONTAINER_TYPES
			.register("smelter_furnace", () -> IForgeContainerType.create(SmelterFurnaceContainer::new));

}