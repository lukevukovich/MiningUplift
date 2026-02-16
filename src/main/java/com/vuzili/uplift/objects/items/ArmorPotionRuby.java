package com.vuzili.uplift.objects.items;

import com.vuzili.uplift.init.ItemInit;
import com.vuzili.uplift.util.ArmorEffectToggle;
import com.vuzili.uplift.util.ArmorPotionEffectParticles;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

public class ArmorPotionRuby extends ArmorItem {

	public ArmorPotionRuby(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builder) {
		super(materialIn, slot, builder);
	}

	@Override
	public void onArmorTick(ItemStack stack, World world, PlayerEntity player) 
	{
		ItemStack head = player.getItemStackFromSlot(EquipmentSlotType.HEAD);
		ItemStack chest = player.getItemStackFromSlot(EquipmentSlotType.CHEST);
		ItemStack legs = player.getItemStackFromSlot(EquipmentSlotType.LEGS);
		ItemStack feet = player.getItemStackFromSlot(EquipmentSlotType.FEET);

		if (head.getItem() == ItemInit.ruby_helmet
				&& chest.getItem() == ItemInit.ruby_chestplate
				&& legs.getItem() == ItemInit.ruby_leggings
				&& feet.getItem() == ItemInit.ruby_boots) 
		{
			if (ArmorEffectToggle.areEffectsEnabled(player)) {
				if (!world.isRemote) {
					player.addPotionEffect(new EffectInstance(Effects.RESISTANCE, Integer.MAX_VALUE, 0, false, false));
				}
				ArmorPotionEffectParticles.spawnParticles(world, player, stack, ItemInit.ruby_boots, 255, 44, 44);
			} else {
				if (!world.isRemote) {
					player.removePotionEffect(Effects.RESISTANCE);
				}
			}
		}
		else {
			if (!world.isRemote) {
				player.removePotionEffect(Effects.RESISTANCE);
			}
		}
		super.onArmorTick(stack, world, player);
	}

}
