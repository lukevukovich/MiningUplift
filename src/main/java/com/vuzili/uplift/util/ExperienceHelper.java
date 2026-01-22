package com.vuzili.uplift.util;

import javax.annotation.Nullable;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;

import com.vuzili.uplift.init.ItemInit;

public final class ExperienceHelper {

    private ExperienceHelper() {}

    @Nullable
    private static Integer getXpForSmeltByItem(Item item) {
        if (item == Items.IRON_INGOT) return 1;
        if (item == Items.GOLD_INGOT) return 1;
        if (item == ItemInit.chrome_ingot) return 1;
        if (item == ItemInit.shadowglass) return 2;
        if (item == ItemInit.uranium_ingot) return 2;
        if (item == ItemInit.rose_gold_ingot) return 2;
        if (item == ItemInit.burning_dust) return 3;
        if (item == ItemInit.platinum_ingot) return 3;
        return null;
    }

    /**
     * Returns per-item XP value for a smelted output.
     * Prefers vanilla smelting recipe XP when available; otherwise derives
     * a value from the mod's tool tier harvest level of the material.
     */
    public static int getXpForSmeltOutput(ItemStack output, World world) {
        if (output == null || output.isEmpty()) return 0;

        Integer xp = getXpForSmeltByItem(output.getItem());
        if (xp != null) {
            return xp;
        }

        return 1;
    }
        
}
