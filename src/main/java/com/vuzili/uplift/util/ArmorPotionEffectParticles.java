package com.vuzili.uplift.util;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.World;

public class ArmorPotionEffectParticles {
    // Only spawn client-side, once every 8 ticks, and only from boots to avoid 4x duplication
    // Spawn around the full body (random across player height), but only once from boots to avoid duplication
    public static void spawnParticles(World world, PlayerEntity player, ItemStack stack, Item boots, int rColor, int gColor, int bColor) {
            if (world.isRemote && stack.getItem() == boots && world.getGameTime() % 6 == 0) {
                double r = rColor / 255.0D;
                double g = gColor / 255.0D;
                double b = bColor / 255.0D;
                float height = player.getHeight();
                float halfWidth = player.getWidth() * 0.5F;
                for (int i = 0; i < 2; i++) {
                    double x = player.getPosX() + (world.rand.nextDouble() * 2 - 1) * (halfWidth + 0.3D);
                    double y = player.getPosY() + world.rand.nextDouble() * height;
                    double z = player.getPosZ() + (world.rand.nextDouble() * 2 - 1) * (halfWidth + 0.3D);
                    world.addParticle(ParticleTypes.ENTITY_EFFECT, x, y, z, r, g, b);
                }
			}
    }

    // Spawn particles around a living entity (used for weapons)
    public static void spawnParticles(World world, LivingEntity entity, ItemStack stack, Item item, int rColor, int gColor, int bColor) {
        if (world.isRemote && stack.getItem() == item && world.getGameTime() % 6 == 0) {
            double r = rColor / 255.0D;
            double g = gColor / 255.0D;
            double b = bColor / 255.0D;
            float height = entity.getHeight();
            float halfWidth = entity.getWidth() * 0.5F;
            for (int i = 0; i < 2; i++) {
                double x = entity.getPosX() + (world.rand.nextDouble() * 2 - 1) * (halfWidth + 0.3D);
                double y = entity.getPosY() + world.rand.nextDouble() * height;
                double z = entity.getPosZ() + (world.rand.nextDouble() * 2 - 1) * (halfWidth + 0.3D);
                world.addParticle(ParticleTypes.ENTITY_EFFECT, x, y, z, r, g, b);
            }
        }
    }
}