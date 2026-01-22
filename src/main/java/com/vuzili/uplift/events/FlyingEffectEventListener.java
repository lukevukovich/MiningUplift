package com.vuzili.uplift.events;

import com.vuzili.uplift.init.EffectInit;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = "uplift", bus = Bus.FORGE)
public class FlyingEffectEventListener {

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.START) {
            return; // Only proceed if at the start of the tick
        }

        PlayerEntity player = event.player;
        if (player.world.isRemote) {
            return; // Skip processing on the client side
        }

        // Here, you implement the logic to enable or disable flying based on the potion effect
        handleFlyingAbility(player);
        
        // Handle hunger drain while flying (similar to sprinting)
        handleFlightHunger(player);
    }
    
    @SubscribeEvent
    public static void normalizeBreakSpeed(BreakSpeed event) {
        PlayerEntity player = event.getPlayer();
        
        // Check if the player is flying due to the flight potion effect
        if (player.isPotionActive(EffectInit.FLIGHT) && player.abilities.isFlying) {
            // Normalize break speed
            event.setNewSpeed(event.getOriginalSpeed() * 5.0F);

        }
        
    }

    private static void handleFlyingAbility(PlayerEntity player) {
        boolean hasEffect = player.isPotionActive(EffectInit.FLIGHT);
        if (hasEffect && !player.abilities.allowFlying) {
            player.abilities.allowFlying = true;
            player.sendPlayerAbilities(); // Synchronize abilities with the client
        } else if (!hasEffect && player.abilities.allowFlying && !player.isCreative() && !player.isSpectator()) {
            // Disable flying only if the player is in survival/adventure and no longer has the effect
            player.abilities.allowFlying = false;
            player.abilities.isFlying = false;
            player.sendPlayerAbilities();
        }
    }
    
    private static void handleFlightHunger(PlayerEntity player) {
        // Only drain hunger if player is flying with the flight effect (not creative/spectator)
        if (player.isPotionActive(EffectInit.FLIGHT) && player.abilities.isFlying && !player.isCreative() && !player.isSpectator()) {
            // Drain hunger more than sprinting since flying is more exhausting
            // Base exhaustion slightly higher than sprinting, scaled by difficulty
            // Peaceful = no drain, Easy = light drain, Normal = moderate, Hard = heavy
            float baseExhaustion = 0.018F; // Slightly more than sprinting (~0.018F equivalent)
            
            int difficulty = player.world.getDifficulty().getId(); // 0=peaceful, 1=easy, 2=normal, 3=hard
            if (difficulty == 0) {
                return; // No hunger drain on peaceful
            }
            
            // Scale: Easy=0.5x, Normal=1.0x, Hard=1.5x
            float difficultyMultiplier = difficulty * 0.75F;
            player.addExhaustion(baseExhaustion * difficultyMultiplier);
        }
    }
}
