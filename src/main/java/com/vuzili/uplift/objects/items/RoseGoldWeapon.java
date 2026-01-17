package com.vuzili.uplift.objects.items;

import com.vuzili.uplift.util.ArmorPotionEffectParticles;

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
	    if (entityIn instanceof LivingEntity) {
	        LivingEntity livingEntity = (LivingEntity) entityIn;
	        
	        // Check for item in main hand or off hand
	        boolean isHeld = livingEntity.getHeldItemMainhand().getItem() == this || livingEntity.getHeldItemOffhand().getItem() == this;
	        
	        if (isHeld) {
	            // Server: apply potion effect
	            if (!worldIn.isRemote) {
	                livingEntity.addPotionEffect(new EffectInstance(effect, 10, 2, false, false));
	            }
	            // Client: spawn colored particles (matches armor behavior)
	            else {
	                ArmorPotionEffectParticles.spawnParticles(worldIn, livingEntity, stack, this, 255, 125, 229);
	            }
	        }
	    }
	    super.inventoryTick(stack, worldIn, entityIn, itemSlot, isSelected);
	}


}
