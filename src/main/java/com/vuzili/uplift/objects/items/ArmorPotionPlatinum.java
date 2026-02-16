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

public class ArmorPotionPlatinum extends ArmorItem {

	public ArmorPotionPlatinum(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builder) {
		super(materialIn, slot, builder);
	}

	@Override
	public void onArmorTick(ItemStack stack, World world, PlayerEntity player) 
	{
		ItemStack head = player.getItemStackFromSlot(EquipmentSlotType.HEAD);
		ItemStack chest = player.getItemStackFromSlot(EquipmentSlotType.CHEST);
		ItemStack legs = player.getItemStackFromSlot(EquipmentSlotType.LEGS);
		ItemStack feet = player.getItemStackFromSlot(EquipmentSlotType.FEET);

		if (head.getItem() == ItemInit.platinum_helmet
				&& chest.getItem() == ItemInit.platinum_chestplate
				&& legs.getItem() == ItemInit.platinum_leggings
				&& feet.getItem() == ItemInit.platinum_boots) 
		{
			if (ArmorEffectToggle.areEffectsEnabled(player)) {
				if (!world.isRemote) {
					player.addPotionEffect(new EffectInstance(Effects.STRENGTH, Integer.MAX_VALUE, 0, false, false));
				}
				ArmorPotionEffectParticles.spawnParticles(world, player, stack, ItemInit.platinum_boots, 212, 245, 249);
			} else {
				if (!world.isRemote) {
					player.removePotionEffect(Effects.STRENGTH);
				}
			}
		}
		else {
			if (!world.isRemote) {
				player.removePotionEffect(Effects.STRENGTH);
			}
		}
		super.onArmorTick(stack, world, player);
	}

}
