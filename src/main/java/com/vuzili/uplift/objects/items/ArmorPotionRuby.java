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

public class ArmorPotionRuby extends ArmorItem {

	public ArmorPotionRuby(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builder) {
		super(materialIn, slot, builder);
	}

	@Override
	public void onArmorTick(ItemStack stack, World world, PlayerEntity player) 
	{
		if (player.inventory.armorItemInSlot(3).getItem() == ItemInit.ruby_helmet
				&& player.inventory.armorItemInSlot(2).getItem() == ItemInit.ruby_chestplate
				&& player.inventory.armorItemInSlot(1).getItem() == ItemInit.ruby_leggings
				&& player.inventory.armorItemInSlot(0).getItem() == ItemInit.ruby_boots) 
		{
			if (!world.isRemote) {
				player.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 210, 0, false, false));
			}
			ArmorPotionEffectParticles.spawnParticles(world, player, stack, ItemInit.ruby_boots, 255, 44, 44);
		}
		super.onArmorTick(stack, world, player);
	}

}
