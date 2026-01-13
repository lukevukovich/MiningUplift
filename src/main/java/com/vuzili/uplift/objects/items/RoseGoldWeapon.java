package com.vuzili.uplift.objects.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.world.World;

public class RoseGoldWeapon extends SwordItem
{
	
	private Effect effect;

	public RoseGoldWeapon(Effect e, IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties builder) {
		super(tier, attackDamageIn, attackSpeedIn, builder);
		effect = e;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
	    if (!worldIn.isRemote && entityIn instanceof LivingEntity) {
	        LivingEntity livingEntity = (LivingEntity) entityIn;
	        
	        // Check for item in main hand or off hand
	        boolean isHeld = livingEntity.getHeldItemMainhand().getItem() == this || livingEntity.getHeldItemOffhand().getItem() == this;
	        
	        if (isHeld) {
	            livingEntity.addPotionEffect(new EffectInstance(effect, 1, 2));
	        }
	    }
	    super.inventoryTick(stack, worldIn, entityIn, itemSlot, isSelected);
	}


}
