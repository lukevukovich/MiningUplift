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

public class ArmorPotionSapphire extends ArmorItem {
	
	public ArmorPotionSapphire(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builder) {
		super(materialIn, slot, builder);
	}

	@Override
	public void onArmorTick(ItemStack stack, World world, PlayerEntity player) 
	{
		if (player.inventory.armorItemInSlot(3).getItem() == ItemInit.sapphire_helmet
				&& player.inventory.armorItemInSlot(2).getItem() == ItemInit.sapphire_chestplate
				&& player.inventory.armorItemInSlot(1).getItem() == ItemInit.sapphire_leggings
				&& player.inventory.armorItemInSlot(0).getItem() == ItemInit.sapphire_boots) 
		{
			player.addPotionEffect(new EffectInstance(Effects.WATER_BREATHING, 201));
		}
		/*else
		{
			player.removePotionEffect(Effects.WATER_BREATHING);
		}*/
		super.onArmorTick(stack, world, player);
	}

}
