package com.vuzili.uplift.objects.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class BurningDiamondFuel extends Item
{

	public BurningDiamondFuel(Properties properties) 
	{
		super(properties);
	}
	
	@Override
	public int getBurnTime(ItemStack itemStack) 
	{
		return 40000;
	}
	
}
