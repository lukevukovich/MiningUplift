package com.vuzili.uplift.objects.items;

import com.vuzili.uplift.init.BlockInit;
import com.vuzili.uplift.objects.blocks.IgniterFire;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

public class Igniter extends FlintAndSteelIgniter
{

	public Igniter(Properties properties) {
		super(properties);
		// TODO Auto-generated constructor stub
	}

	@Override
	   public ActionResultType onItemUse(ItemUseContext context) {
		      PlayerEntity playerentity = context.getPlayer();
		      IWorld iworld = context.getWorld();
		      BlockPos blockpos = context.getPos();
		      BlockState blockstate = iworld.getBlockState(blockpos);
		      Block block = blockstate.getBlock();
		      
		      if (block.equals(BlockInit.unlit_smelter.getBlock())) {
		    	  BlockState state = BlockInit.smelter.getDefaultState();
		    	  state = state.with(BlockStateProperties.HORIZONTAL_FACING, blockstate.get(BlockStateProperties.HORIZONTAL_FACING));
		    	  
		         if (playerentity != null) {
		        	 
		             ItemStack itemStack = context.getItem();
		             int currentDamage = itemStack.getDamage();
		             int maxDamage = itemStack.getMaxDamage();
		             
		             if ((maxDamage - currentDamage) >= 50) {
				    	  iworld.getWorld().setBlockState(blockpos, state);
				    	  
		                 // If yes, apply 50 damage
		                 itemStack.damageItem(50, playerentity, (p_219999_1_) -> {
		                     p_219999_1_.sendBreakAnimation(context.getHand());
		                 });
				    	 return ActionResultType.SUCCESS;
		             }
		         }
		      }
		      
		      else if (isUnlitCampfire(blockstate)) {
		         iworld.playSound(playerentity, blockpos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, random.nextFloat() * 0.4F + 0.8F);
		         iworld.setBlockState(blockpos, blockstate.with(BlockStateProperties.LIT, Boolean.valueOf(true)), 11);
		         if (playerentity != null) {
		            context.getItem().damageItem(1, playerentity, (p_219999_1_) -> {
		               p_219999_1_.sendBreakAnimation(context.getHand());
		            });
		         }
	
		         return ActionResultType.SUCCESS;
		      } else {
		         BlockPos blockpos1 = blockpos.offset(context.getFace());
		         if (canSetFire(iworld.getBlockState(blockpos1), iworld, blockpos1)) {
		            iworld.playSound(playerentity, blockpos1, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, random.nextFloat() * 0.4F + 0.8F);
		            BlockState blockstate1 = ((IgniterFire)BlockInit.igniter_fire).getStateForPlacement(iworld, blockpos1);
		            iworld.setBlockState(blockpos1, blockstate1, 11);
		            ItemStack itemstack = context.getItem();
		            if (playerentity instanceof ServerPlayerEntity) {
		               CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayerEntity)playerentity, blockpos1, itemstack);
		               itemstack.damageItem(1, playerentity, (p_219998_1_) -> {
		                  p_219998_1_.sendBreakAnimation(context.getHand());
		               });
		            }
	
		            return ActionResultType.SUCCESS;
		         } else {
		            return ActionResultType.FAIL;
		         }
		      }
		      
		      return ActionResultType.FAIL;
		   }

}
