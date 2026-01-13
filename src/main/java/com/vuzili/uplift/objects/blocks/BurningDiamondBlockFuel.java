package com.vuzili.uplift.objects.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;

public class BurningDiamondBlockFuel extends BlockItem
{

	public BurningDiamondBlockFuel(Block block, Properties properties) 
	{
		super(block,properties);
	}
	
	@Override
	public int getBurnTime(ItemStack itemStack) 
	{
		return 40000;
	}

}
