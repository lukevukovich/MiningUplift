package com.vuzili.uplift.init;

import com.vuzili.uplift.Uplift;
import com.vuzili.uplift.effects.FlyingEffect;

import net.minecraft.potion.Effect;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(Uplift.MOD_ID)
@EventBusSubscriber(modid = Uplift.MOD_ID, bus = Bus.MOD)
public class EffectInit {
	
    public static final Effect FLIGHT = null;

    @SubscribeEvent
    public static void registerEffects(RegistryEvent.Register<Effect> event) {
    	event.getRegistry().register(new FlyingEffect().setRegistryName("flight"));
    }
}
