package com.vuzili.uplift.events;

import java.util.HashSet;
import java.util.Set;

import com.vuzili.uplift.Uplift;
import com.vuzili.uplift.objects.items.RoseGoldWeapon;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = Uplift.MOD_ID)
public class RoseGoldWeaponEffects {

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        PlayerEntity player = event.player;
        if (player.world.isRemote) return; // server-side only for effect removal

        // Determine which effects should be kept based on currently held RoseGoldWeapons
        Set<Effect> effectsHeld = new HashSet<>();
        addIfRoseGold(effectsHeld, player.getHeldItemMainhand());
        addIfRoseGold(effectsHeld, player.getHeldItemOffhand());

        // Build the set of all effects that RoseGoldWeapons can grant
        Set<Effect> allRoseGoldEffects = new HashSet<>();
        for (Item item : ForgeRegistries.ITEMS.getValues()) {
            if (item instanceof RoseGoldWeapon) {
                Effect eff = ((RoseGoldWeapon) item).getEffect();
                if (eff != null) allRoseGoldEffects.add(eff);
            }
        }

        // Remove any RoseGold effect not currently provided by a held weapon.
        // Only remove if the amplifier matches weapon-level (2) so we don't
        // strip armor-granted effects that happen to share the same Effect type.
        for (Effect eff : allRoseGoldEffects) {
            if (!effectsHeld.contains(eff) && player.isPotionActive(eff)) {
                EffectInstance instance = player.getActivePotionEffect(eff);
                if (instance != null && instance.getAmplifier() == 2) {
                    player.removePotionEffect(eff);
                }
            }
        }
    }

    private static void addIfRoseGold(Set<Effect> collect, ItemStack stack) {
        if (stack == null || stack.isEmpty()) return;
        Item item = stack.getItem();
        if (item instanceof RoseGoldWeapon) {
            Effect eff = ((RoseGoldWeapon) item).getEffect();
            if (eff != null) collect.add(eff);
        }
    }
}
