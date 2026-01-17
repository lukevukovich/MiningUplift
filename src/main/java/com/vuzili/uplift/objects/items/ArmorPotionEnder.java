package com.vuzili.uplift.objects.items;

import com.vuzili.uplift.init.EffectInit;
import com.vuzili.uplift.init.ItemInit;
import com.vuzili.uplift.util.ArmorPotionEffectParticles;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.world.World;

public class ArmorPotionEnder extends ArmorItem {;


	public ArmorPotionEnder(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builder) {
		super(materialIn, slot, builder);
	}

	@Override
	public void onArmorTick(ItemStack stack, World world, PlayerEntity player) 
	{
		if (player.inventory.armorItemInSlot(3).getItem() == ItemInit.ender_helmet
				&& player.inventory.armorItemInSlot(2).getItem() == ItemInit.ender_chestplate
				&& player.inventory.armorItemInSlot(1).getItem() == ItemInit.ender_leggings
				&& player.inventory.armorItemInSlot(0).getItem() == ItemInit.ender_boots) 
		{
			if (!world.isRemote) {
				player.addPotionEffect(new EffectInstance(EffectInit.FLIGHT, 210, 0, false, false));
			}
			ArmorPotionEffectParticles.spawnParticles(world, player, stack, ItemInit.ender_boots, 212, 218, 146);
		}
		super.onArmorTick(stack, world, player);
	}
	
}
