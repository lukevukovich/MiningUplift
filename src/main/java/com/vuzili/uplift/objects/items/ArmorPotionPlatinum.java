package com.vuzili.uplift.objects.items;

import com.vuzili.uplift.init.ItemInit;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

public class ArmorPotionPlatinum extends ArmorItem {

	public ArmorPotionPlatinum(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builder) {
		super(materialIn, slot, builder);
	}

	@Override
	public void onArmorTick(ItemStack stack, World world, PlayerEntity player) 
	{
		if (player.inventory.armorItemInSlot(3).getItem() == ItemInit.platinum_helmet
				&& player.inventory.armorItemInSlot(2).getItem() == ItemInit.platinum_chestplate
				&& player.inventory.armorItemInSlot(1).getItem() == ItemInit.platinum_leggings
				&& player.inventory.armorItemInSlot(0).getItem() == ItemInit.platinum_boots) 
		{
			player.addPotionEffect(new EffectInstance(Effects.STRENGTH, 201));
		}
		/*else
		{
			player.removePotionEffect(Effects.NIGHT_VISION);
		}*/
		super.onArmorTick(stack, world, player);
	}

}
