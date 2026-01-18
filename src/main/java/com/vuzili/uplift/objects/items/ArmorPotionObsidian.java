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

public class ArmorPotionObsidian extends ArmorItem {

	public ArmorPotionObsidian(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builder) {
		super(materialIn, slot, builder);
	}

	@Override
	public void onArmorTick(ItemStack stack, World world, PlayerEntity player) 
	{
		ItemStack head = player.getItemStackFromSlot(EquipmentSlotType.HEAD);
		ItemStack chest = player.getItemStackFromSlot(EquipmentSlotType.CHEST);
		ItemStack legs = player.getItemStackFromSlot(EquipmentSlotType.LEGS);
		ItemStack feet = player.getItemStackFromSlot(EquipmentSlotType.FEET);

		if (head.getItem() == ItemInit.obsidian_helmet
				&& chest.getItem() == ItemInit.obsidian_chestplate
				&& legs.getItem() == ItemInit.obsidian_leggings
				&& feet.getItem() == ItemInit.obsidian_boots) 
		{
			if (!world.isRemote) {
				player.addPotionEffect(new EffectInstance(Effects.NIGHT_VISION, 210, 0, false, false));
			}
			ArmorPotionEffectParticles.spawnParticles(world, player, stack, ItemInit.obsidian_boots, 106, 40, 202);
		}
		else {
			if (!world.isRemote) {
				player.removePotionEffect(Effects.NIGHT_VISION);
			}
		}
		super.onArmorTick(stack, world, player);
	}

}
