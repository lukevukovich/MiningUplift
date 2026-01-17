package com.vuzili.uplift.objects.items;

import com.vuzili.uplift.init.ItemInit;
import com.vuzili.uplift.util.ArmorPotionEffectParticles;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.InstantEffect;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class ArmorPotionUranium extends ArmorItem {
	
	private static final int r = 129;
	private static final int g = 255;
	private static final int b = 101;
	private static final int decimalColor = (r * 65536) + (g * 256) + b;
	
	private static final Effect HASTE = register(23, "haste", new InstantEffect(EffectType.BENEFICIAL, decimalColor));
	
    @SuppressWarnings("deprecation")
	private static Effect register(int id, String key, Effect effectIn) {
	      return Registry.register(Registry.EFFECTS, id, key, effectIn);
	}

	public ArmorPotionUranium(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builder) {
		super(materialIn, slot, builder);
	}

	@Override
	public void onArmorTick(ItemStack stack, World world, PlayerEntity player) 
	{
		ItemStack head = player.getItemStackFromSlot(EquipmentSlotType.HEAD);
		ItemStack chest = player.getItemStackFromSlot(EquipmentSlotType.CHEST);
		ItemStack legs = player.getItemStackFromSlot(EquipmentSlotType.LEGS);
		ItemStack feet = player.getItemStackFromSlot(EquipmentSlotType.FEET);

		if (head.getItem() == ItemInit.uranium_helmet
				&& chest.getItem() == ItemInit.uranium_chestplate
				&& legs.getItem() == ItemInit.uranium_leggings
				&& feet.getItem() == ItemInit.uranium_boots) 
		{
			if (!world.isRemote) {
				player.addPotionEffect(new EffectInstance(HASTE, 210, 2, false, false));
			}
			ArmorPotionEffectParticles.spawnParticles(world, player, stack, ItemInit.uranium_boots, 92, 255, 55);
		}
		super.onArmorTick(stack, world, player);
	}

}
