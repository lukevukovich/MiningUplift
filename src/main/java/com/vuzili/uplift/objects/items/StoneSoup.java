package com.vuzili.uplift.objects.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;

public class StoneSoup extends Item {

	public StoneSoup(Properties properties) {
		super(properties);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
	    if (this.isFood()) {	      

	        if (entityLiving instanceof PlayerEntity && !worldIn.isRemote) {
	            PlayerEntity player = (PlayerEntity) entityLiving;
	            
	            ItemStack newStack;

	            if (!player.abilities.isCreativeMode) {

	                newStack = new ItemStack(Items.BOWL);
	            } else {

	            	newStack = new ItemStack(this);
	            }
	            
	            entityLiving.onFoodEaten(worldIn, stack);
	            return newStack;
	        } else {
	        	return stack;
	        }

	    } else {

	        return stack;
	    }
	}


}
