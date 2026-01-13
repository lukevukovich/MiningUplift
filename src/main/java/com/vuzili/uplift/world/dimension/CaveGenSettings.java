package com.vuzili.uplift.world.dimension;

import net.minecraft.world.gen.GenerationSettings;

public class CaveGenSettings extends GenerationSettings
{
	public CaveGenSettings()
	{
		
	}
	
	public int getBiomeSize()
	{
		return 4;
	}
	
	public int getRiverSize()
	{
		return 4;
	}
	
	public int getBiomeId()
	{
		return -1;
	}
	
	@Override
	public int getBedrockFloorHeight()
	{
		return 0;
	}
	
	@Override
	public int getBedrockRoofHeight()
	{
		return 65;
	}
	
}
