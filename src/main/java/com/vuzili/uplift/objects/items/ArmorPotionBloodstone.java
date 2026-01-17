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
		if (player.inventory.armorItemInSlot(3).getItem() == ItemInit.bloodstone_helmet
				&& player.inventory.armorItemInSlot(2).getItem() == ItemInit.bloodstone_chestplate
				&& player.inventory.armorItemInSlot(1).getItem() == ItemInit.bloodstone_leggings
				&& player.inventory.armorItemInSlot(0).getItem() == ItemInit.bloodstone_boots) 
		{
			if (!world.isRemote) {
				player.addPotionEffect(new EffectInstance(Effects.REGENERATION, 210, 2, false, false));
				player.addPotionEffect(new EffectInstance(Effects.BLINDNESS, 210, 0, false, false));
				player.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 210, 4, false, false));
				player.addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, 210, 2, false, false));
			}
			ArmorPotionEffectParticles.spawnParticles(world, player, stack, ItemInit.bloodstone_boots, 116, 10, 10);
		}
		super.onArmorTick(stack, world, player);
	}

}
