package com.vuzili.uplift.objects.blocks;

import com.vuzili.uplift.init.ItemInit;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public class GemCrusher extends Block
{

	public GemCrusher(Properties properties) {
		super(properties);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("deprecation")
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
			Hand handIn, BlockRayTraceResult hit) 
	{

		if(ItemInit.ruby == player.getHeldItemMainhand().getItem())
		{
			worldIn.playSound(player, pos, SoundEvents.BLOCK_PISTON_EXTEND, SoundCategory.BLOCKS, 1.0f, 0.8f);
			int count = player.getHeldItemMainhand().getCount();
            ItemEntity drop = new ItemEntity(worldIn, pos.getX()+0.5f, pos.getY()+1, pos.getZ()+0.5f, new ItemStack(ItemInit.gem_dust));
            worldIn.addEntity(drop);
			player.getHeldItemMainhand().setCount(count-1);
			return ActionResultType.SUCCESS;
		}
		else if(ItemInit.sapphire == player.getHeldItemMainhand().getItem() || ItemInit.tourmaline == player.getHeldItemMainhand().getItem())
		{
			worldIn.playSound(player, pos, SoundEvents.BLOCK_PISTON_EXTEND, SoundCategory.BLOCKS, 1.0f, 0.8f);
			int count = player.getHeldItemMainhand().getCount();
			for(int x = 0; x<3; x++)
			{
	            ItemEntity drop = new ItemEntity(worldIn, pos.getX()+0.5f, pos.getY()+1, pos.getZ()+0.5f, new ItemStack(ItemInit.gem_dust));
	            worldIn.addEntity(drop);
			}
			player.getHeldItemMainhand().setCount(count-1);
			return ActionResultType.SUCCESS;
		}
		
		else if(Items.DIAMOND == player.getHeldItemMainhand().getItem() || Items.EMERALD == player.getHeldItemMainhand().getItem() || ItemInit.ender_gem == player.getHeldItemMainhand().getItem())
		{
			worldIn.playSound(player, pos, SoundEvents.BLOCK_PISTON_EXTEND, SoundCategory.BLOCKS, 1.0f, 0.8f);
			int count = player.getHeldItemMainhand().getCount();
			for(int x = 0; x<6; x++)
			{
	            ItemEntity drop = new ItemEntity(worldIn, pos.getX()+0.5f, pos.getY()+1, pos.getZ()+0.5f, new ItemStack(ItemInit.gem_dust));
	            worldIn.addEntity(drop);
			}
			player.getHeldItemMainhand().setCount(count-1);
			return ActionResultType.SUCCESS;
		}

		else if(ItemInit.burning_diamond == player.getHeldItemMainhand().getItem() || ItemInit.opal == player.getHeldItemMainhand().getItem())
		{
			worldIn.playSound(player, pos, SoundEvents.BLOCK_PISTON_EXTEND, SoundCategory.BLOCKS, 1.0f, 0.8f);
			int count = player.getHeldItemMainhand().getCount();
			for(int x = 0; x<9; x++)
			{
	            ItemEntity drop = new ItemEntity(worldIn, pos.getX()+0.5f, pos.getY()+1, pos.getZ()+0.5f, new ItemStack(ItemInit.gem_dust));
	            worldIn.addEntity(drop);
			}
			player.getHeldItemMainhand().setCount(count-1);
			return ActionResultType.SUCCESS;
		}
		return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
		
	}
	

}
