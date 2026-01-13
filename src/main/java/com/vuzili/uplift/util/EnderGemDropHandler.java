package com.vuzili.uplift.util;

import java.util.Random;

import com.vuzili.uplift.init.ItemInit;

import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.EndermanEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class EnderGemDropHandler {

    @SubscribeEvent
    public static void onLivingDrops(LivingDropsEvent event) {
        if (!(event.getEntityLiving() instanceof EndermanEntity)) {
            return; // Not an Enderman, ignore
        }

        World world = event.getEntityLiving().getEntityWorld();
        if (world.getDimension().getType() != DimensionType.THE_END) {
            return; // Not in the End, ignore
        }
        
        Random rand = new Random();
        int dropChance = rand.nextInt(3);
        
        if (dropChance == 0) {	
	        // Your logic to adjust drops, e.g., adding Ender Gems
	        ItemStack stack = new ItemStack(ItemInit.ender_gem, 1);
	        event.getDrops().add(new ItemEntity(world, event.getEntityLiving().getPosX(), event.getEntityLiving().getPosY(), event.getEntityLiving().getPosZ(), stack));
        }
    }
}
