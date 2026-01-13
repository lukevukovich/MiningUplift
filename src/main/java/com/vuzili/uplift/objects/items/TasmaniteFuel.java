package com.vuzili.uplift.objects.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TasmaniteFuel extends Item
{

	public TasmaniteFuel(Properties properties) 
	{
		super(properties);
	}
	
	@Override
	public int getBurnTime(ItemStack itemStack) 
	{
		return 2400;
	}
	
}
