package com.vuzili.uplift.objects.items;

import com.vuzili.uplift.init.ItemInit;
import com.vuzili.uplift.util.ArmorPotionEffectParticles;

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
		ItemStack head = player.getItemStackFromSlot(EquipmentSlotType.HEAD);
		ItemStack chest = player.getItemStackFromSlot(EquipmentSlotType.CHEST);
		ItemStack legs = player.getItemStackFromSlot(EquipmentSlotType.LEGS);
		ItemStack feet = player.getItemStackFromSlot(EquipmentSlotType.FEET);

		if (head.getItem() == ItemInit.tourmaline_helmet
				&& chest.getItem() == ItemInit.tourmaline_chestplate
				&& legs.getItem() == ItemInit.tourmaline_leggings
				&& feet.getItem() == ItemInit.tourmaline_boots) 
		{
			if (!world.isRemote) {
				player.addPotionEffect(new EffectInstance(Effects.JUMP_BOOST, Integer.MAX_VALUE, 1, false, false));
			}
			ArmorPotionEffectParticles.spawnParticles(world, player, stack, ItemInit.tourmaline_boots, 208, 60, 250);
		}
		else {
			if (!world.isRemote) {
				player.removePotionEffect(Effects.JUMP_BOOST);
			}
		}
		super.onArmorTick(stack, world, player);
	}

}
