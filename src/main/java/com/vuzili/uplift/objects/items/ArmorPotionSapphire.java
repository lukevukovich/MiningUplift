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

public class ArmorPotionSapphire extends ArmorItem {
	
	public ArmorPotionSapphire(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builder) {
		super(materialIn, slot, builder);
	}

	@Override
	public void onArmorTick(ItemStack stack, World world, PlayerEntity player) 
	{
		ItemStack head = player.getItemStackFromSlot(EquipmentSlotType.HEAD);
		ItemStack chest = player.getItemStackFromSlot(EquipmentSlotType.CHEST);
		ItemStack legs = player.getItemStackFromSlot(EquipmentSlotType.LEGS);
		ItemStack feet = player.getItemStackFromSlot(EquipmentSlotType.FEET);

		if (head.getItem() == ItemInit.sapphire_helmet
				&& chest.getItem() == ItemInit.sapphire_chestplate
				&& legs.getItem() == ItemInit.sapphire_leggings
				&& feet.getItem() == ItemInit.sapphire_boots) 
		{
			if (ArmorEffectToggle.areEffectsEnabled(player)) {
				if (!world.isRemote) {
					player.addPotionEffect(new EffectInstance(Effects.WATER_BREATHING, Integer.MAX_VALUE, 0, false, false));
				}
				ArmorPotionEffectParticles.spawnParticles(world, player, stack, ItemInit.sapphire_boots, 49, 49, 250);
			} else {
				if (!world.isRemote) {
					player.removePotionEffect(Effects.WATER_BREATHING);
				}
			}
		}
		else {
			if (!world.isRemote) {
				player.removePotionEffect(Effects.WATER_BREATHING);
			}
		}
		super.onArmorTick(stack, world, player);
	}

}
