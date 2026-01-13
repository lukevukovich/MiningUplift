package com.vuzili.uplift.init;

import com.vuzili.uplift.Uplift;

import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = Uplift.MOD_ID, bus = Bus.FORGE)
public class ForgeEventBusSubscriber 
{
	@SubscribeEvent
	public static void registerDimensions(final RegisterDimensionsEvent event)
	{
		if(DimensionType.byName(Uplift.UPLIFT_DIM_TYPE) == null)
		{
			DimensionManager.registerDimension(Uplift.UPLIFT_DIM_TYPE, DimensionInit.CAVE_DIM.get(), null, false);
		}
		Uplift.LOGGER.info("Dimensions Registered!");
	}
}
