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

public class ArmorPotionChrome extends ArmorItem {

	public ArmorPotionChrome(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builder) {
		super(materialIn, slot, builder);
	}

	@Override
	public void onArmorTick(ItemStack stack, World world, PlayerEntity player) 
	{
		if (player.inventory.armorItemInSlot(3).getItem() == ItemInit.chrome_helmet
				&& player.inventory.armorItemInSlot(2).getItem() == ItemInit.chrome_chestplate
				&& player.inventory.armorItemInSlot(1).getItem() == ItemInit.chrome_leggings
				&& player.inventory.armorItemInSlot(0).getItem() == ItemInit.chrome_boots) 
		{
			if (!world.isRemote) {
				player.addPotionEffect(new EffectInstance(Effects.SPEED, 210, 0, false, false));
			}
			ArmorPotionEffectParticles.spawnParticles(world, player, stack, ItemInit.chrome_boots, 134, 118, 204);
		}
		/*else
		{
			player.removePotionEffect(Effects.NIGHT_VISION);
		}*/
		super.onArmorTick(stack, world, player);
	}

}
