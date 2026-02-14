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

public class ArmorPotionAmethyst extends ArmorItem {

	public ArmorPotionAmethyst(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builder) {
		super(materialIn, slot, builder);
	}

	@Override
	public void onArmorTick(ItemStack stack, World world, PlayerEntity player) 
	{
		ItemStack head = player.getItemStackFromSlot(EquipmentSlotType.HEAD);
		ItemStack chest = player.getItemStackFromSlot(EquipmentSlotType.CHEST);
		ItemStack legs = player.getItemStackFromSlot(EquipmentSlotType.LEGS);
		ItemStack feet = player.getItemStackFromSlot(EquipmentSlotType.FEET);

		if (head.getItem() == ItemInit.amethyst_helmet
				&& chest.getItem() == ItemInit.amethyst_chestplate
				&& legs.getItem() == ItemInit.amethyst_leggings
				&& feet.getItem() == ItemInit.amethyst_boots) 
		{
			if (!world.isRemote) {
				player.addPotionEffect(new EffectInstance(Effects.JUMP_BOOST, Integer.MAX_VALUE, 1, false, false));
			}
			ArmorPotionEffectParticles.spawnParticles(world, player, stack, ItemInit.amethyst_boots, 152, 13, 255);
		}
		else {
			if (!world.isRemote) {
				player.removePotionEffect(Effects.JUMP_BOOST);
			}
		}
		super.onArmorTick(stack, world, player);
	}

}
