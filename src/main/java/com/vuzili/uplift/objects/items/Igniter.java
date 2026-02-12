package com.vuzili.uplift.objects.items;

import com.vuzili.uplift.init.BlockInit;
import com.vuzili.uplift.objects.blocks.CavePortal;
import com.vuzili.uplift.objects.blocks.IgniterFire;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import com.vuzili.uplift.tileentity.SmelterFurnaceTileEntity;

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
						 World world = iworld.getWorld();
						 world.setBlockState(blockpos, state);
						 // Ensure fuel resets to full and progress resets on relight
						 TileEntity te = world.getTileEntity(blockpos);
						 if (te instanceof SmelterFurnaceTileEntity) {
							 SmelterFurnaceTileEntity smelter = (SmelterFurnaceTileEntity) te;
							 smelter.fuelTicksRemaining = smelter.fuelTicksMax;
							 smelter.currentSmeltTime = 0;
							 smelter.markDirty();
						 }
				    	  
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
				// Only allow ignition when clicking the inner face of a valid rim (gemstone)
				BlockPos interiorPos = blockpos.offset(context.getFace());
				// If clicking on or near an existing portal, do nothing (don't consume durability)
				if (isPortalNearby(iworld, blockpos, interiorPos)) {
					return ActionResultType.PASS;
				}
				boolean clickedFrame = iworld.getBlockState(blockpos).getBlock() == BlockInit.gemstone;
				@SuppressWarnings("deprecation")
				boolean interiorIsEmpty = iworld.getBlockState(interiorPos).isAir() || iworld.getBlockState(interiorPos).getMaterial().isReplaceable();
				if (clickedFrame && interiorIsEmpty) {
					// Require sufficient igniter durability/power to create portal
					ItemStack itemstack = context.getItem();
					int available = itemstack.getMaxDamage() - itemstack.getDamage();
					if (available >= 100) {
						// Try to create the portal strictly from the interior position
						if (CavePortal.tryCreatePortal(iworld, interiorPos)) {
							 iworld.playSound(playerentity, interiorPos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, random.nextFloat() * 0.4F + 0.8F);
							 if (playerentity instanceof ServerPlayerEntity) {
								 CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayerEntity)playerentity, interiorPos, itemstack);
								 itemstack.damageItem(100, playerentity, (p_219998_1_) -> {
									 p_219998_1_.sendBreakAnimation(context.getHand());
								 });
							 }
							 return ActionResultType.SUCCESS;
						}
					}
				}
				// Otherwise, fallback to placing igniter fire if allowed
				if (canSetFire(iworld.getBlockState(interiorPos), iworld, interiorPos)) {
					 iworld.playSound(playerentity, interiorPos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, random.nextFloat() * 0.4F + 0.8F);
					 BlockState blockstate1 = ((IgniterFire)BlockInit.igniter_fire).getStateForPlacement(iworld, interiorPos);
					 iworld.setBlockState(interiorPos, blockstate1, 11);
					 ItemStack itemstack = context.getItem();
					 if (playerentity instanceof ServerPlayerEntity) {
						 CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayerEntity)playerentity, interiorPos, itemstack);
						 itemstack.damageItem(1, playerentity, (p_219998_1_) -> {
							 p_219998_1_.sendBreakAnimation(context.getHand());
						 });
					 }
					 return ActionResultType.SUCCESS;
				}
				return ActionResultType.FAIL;
		      }
		      
		      return ActionResultType.FAIL;
		   }

			private boolean isPortalNearby(IWorld world, BlockPos base, BlockPos offsetPos) {
				// Check if there's already a portal block nearby
				BlockPos[] candidates = new BlockPos[] {
					offsetPos,
					base,
					offsetPos.up(),
					offsetPos.down(),
					base.up(),
					base.down(),
					offsetPos.north(), offsetPos.south(), offsetPos.east(), offsetPos.west(),
					base.north(), base.south(), base.east(), base.west()
				};
				for (BlockPos p : candidates) {
					if (world.getBlockState(p).getBlock() == BlockInit.cave_portal) {
						return true;
					}
				}
				return false;
			}

			// Removed area search: portal creation only allowed from the inner rim face

}
