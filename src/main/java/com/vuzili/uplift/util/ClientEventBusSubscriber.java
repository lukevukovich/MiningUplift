package com.vuzili.uplift.util;

import com.vuzili.uplift.Uplift;
import com.vuzili.uplift.client.entity.render.StoneEntityRender;
import com.vuzili.uplift.client.gui.SmelterFurnaceScreen;
import com.vuzili.uplift.init.ModContainerTypes;
import com.vuzili.uplift.init.ModEntityTypes;

import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Uplift.MOD_ID, bus = Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {

	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event) 
	{
		
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.STONE_ENTITY.get(), StoneEntityRender::new);
		ScreenManager.registerFactory(ModContainerTypes.SMELTER_FURNACE.get(), SmelterFurnaceScreen::new);
	}
	
}
