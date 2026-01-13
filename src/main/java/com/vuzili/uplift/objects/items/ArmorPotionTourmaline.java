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

public class ArmorPotionTourmaline extends ArmorItem {

	public ArmorPotionTourmaline(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builder) {
		super(materialIn, slot, builder);
	}

	@Override
	public void onArmorTick(ItemStack stack, World world, PlayerEntity player) 
	{
		if (player.inventory.armorItemInSlot(3).getItem() == ItemInit.tourmaline_helmet
				&& player.inventory.armorItemInSlot(2).getItem() == ItemInit.tourmaline_chestplate
				&& player.inventory.armorItemInSlot(1).getItem() == ItemInit.tourmaline_leggings
				&& player.inventory.armorItemInSlot(0).getItem() == ItemInit.tourmaline_boots) 
		{
			player.addPotionEffect(new EffectInstance(Effects.SPEED, 201));
		}
		/*else
		{
			player.removePotionEffect(Effects.NIGHT_VISION);
		}*/
		super.onArmorTick(stack, world, player);
	}

}
