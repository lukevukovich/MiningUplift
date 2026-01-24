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

public class ArmorPotionBloodstone extends ArmorItem {

	public ArmorPotionBloodstone(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builder) {
		super(materialIn, slot, builder);
	}

	@Override
	public void onArmorTick(ItemStack stack, World world, PlayerEntity player) 
	{
		ItemStack head = player.getItemStackFromSlot(EquipmentSlotType.HEAD);
		ItemStack chest = player.getItemStackFromSlot(EquipmentSlotType.CHEST);
		ItemStack legs = player.getItemStackFromSlot(EquipmentSlotType.LEGS);
		ItemStack feet = player.getItemStackFromSlot(EquipmentSlotType.FEET);

		if (head.getItem() == ItemInit.bloodstone_helmet
				&& chest.getItem() == ItemInit.bloodstone_chestplate
				&& legs.getItem() == ItemInit.bloodstone_leggings
				&& feet.getItem() == ItemInit.bloodstone_boots) 
		{
			if (!world.isRemote) {
				player.addPotionEffect(new EffectInstance(Effects.INSTANT_HEALTH, Integer.MAX_VALUE, 0, false, false));
				player.addPotionEffect(new EffectInstance(Effects.BLINDNESS, Integer.MAX_VALUE, 0, false, false));
				player.addPotionEffect(new EffectInstance(Effects.SLOWNESS, Integer.MAX_VALUE, 4, false, false));
				player.addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, Integer.MAX_VALUE, 2, false, false));
			}
			ArmorPotionEffectParticles.spawnParticles(world, player, stack, ItemInit.bloodstone_boots, 116, 10, 10);
		} else {
			if (!world.isRemote) {
				player.removePotionEffect(Effects.INSTANT_HEALTH);
				player.removePotionEffect(Effects.BLINDNESS);
				player.removePotionEffect(Effects.SLOWNESS);
				player.removePotionEffect(Effects.MINING_FATIGUE);
			}
		}
		super.onArmorTick(stack, world, player);
	}

}
