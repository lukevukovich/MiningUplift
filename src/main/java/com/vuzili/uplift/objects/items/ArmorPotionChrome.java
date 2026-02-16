package com.vuzili.uplift.objects.items;

import com.vuzili.uplift.init.EffectInit;
import com.vuzili.uplift.init.ItemInit;
import com.vuzili.uplift.util.ArmorEffectToggle;
import com.vuzili.uplift.util.ArmorPotionEffectParticles;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.world.World;

public class ArmorPotionChrome extends ArmorItem {

	public ArmorPotionChrome(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builder) {
		super(materialIn, slot, builder);
	}

	@Override
	public void onArmorTick(ItemStack stack, World world, PlayerEntity player) 
	{
		ItemStack head = player.getItemStackFromSlot(EquipmentSlotType.HEAD);
		ItemStack chest = player.getItemStackFromSlot(EquipmentSlotType.CHEST);
		ItemStack legs = player.getItemStackFromSlot(EquipmentSlotType.LEGS);
		ItemStack feet = player.getItemStackFromSlot(EquipmentSlotType.FEET);

		if (head.getItem() == ItemInit.chrome_helmet
				&& chest.getItem() == ItemInit.chrome_chestplate
				&& legs.getItem() == ItemInit.chrome_leggings
				&& feet.getItem() == ItemInit.chrome_boots) 
		{
			if (ArmorEffectToggle.areEffectsEnabled(player)) {
				if (!world.isRemote) {
					player.addPotionEffect(new EffectInstance(EffectInit.LUMINOUS, Integer.MAX_VALUE, 0, false, false));
				}
				ArmorPotionEffectParticles.spawnParticles(world, player, stack, ItemInit.chrome_boots, 134, 118, 204);
			} else {
				if (!world.isRemote) {
					player.removePotionEffect(EffectInit.LUMINOUS);
				}
			}
		}
		else {
			if (!world.isRemote) {
				player.removePotionEffect(EffectInit.LUMINOUS);
			}
		}
		super.onArmorTick(stack, world, player);
	}

}
