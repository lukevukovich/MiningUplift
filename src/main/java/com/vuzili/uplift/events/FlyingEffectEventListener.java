package com.vuzili.uplift.events;

import com.vuzili.uplift.init.EffectInit;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
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
}
