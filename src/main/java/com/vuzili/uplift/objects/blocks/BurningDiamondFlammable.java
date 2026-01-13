package com.vuzili.uplift.objects.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class BurningDiamondFlammable extends Block
{

	public BurningDiamondFlammable(Properties properties) {
		super(properties);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean isFireSource(BlockState state, IBlockReader world, BlockPos pos, Direction side) 
	{
		return true;
	}

}
