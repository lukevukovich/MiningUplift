package com.vuzili.uplift.objects.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;

public class GemstoneBlock extends Block
{

	public GemstoneBlock(Properties properties) {
		super(properties);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean isPortalFrame(BlockState state, IWorldReader world, BlockPos pos) {
		// TODO Auto-generated method stub
		return true;
	}
	
}
