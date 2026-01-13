package com.vuzili.uplift.objects.items;

import com.vuzili.uplift.init.ItemInit;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.InstantEffect;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class ArmorPotionUranium extends ArmorItem {
	
	private static final int r = 129;
	private static final int g = 255;
	private static final int b = 101;
	private static final int decimalColor = (r * 65536) + (g * 256) + b;
	
	private static final Effect HASTE = register(23, "haste", new InstantEffect(EffectType.BENEFICIAL, decimalColor));
	
    @SuppressWarnings("deprecation")
	private static Effect register(int id, String key, Effect effectIn) {
	      return Registry.register(Registry.EFFECTS, id, key, effectIn);
	}

	public ArmorPotionUranium(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builder) {
		super(materialIn, slot, builder);
	}

	@Override
	public void onArmorTick(ItemStack stack, World world, PlayerEntity player) 
	{
		if (player.inventory.armorItemInSlot(3).getItem() == ItemInit.uranium_helmet
				&& player.inventory.armorItemInSlot(2).getItem() == ItemInit.uranium_chestplate
				&& player.inventory.armorItemInSlot(1).getItem() == ItemInit.uranium_leggings
				&& player.inventory.armorItemInSlot(0).getItem() == ItemInit.uranium_boots) 
		{
			player.addPotionEffect(new EffectInstance(HASTE, 201, 2));
		}
		/*else
		{
			player.removePotionEffect(Effects.FIRE_RESISTANCE);
		}*/
		super.onArmorTick(stack, world, player);
	}

}
