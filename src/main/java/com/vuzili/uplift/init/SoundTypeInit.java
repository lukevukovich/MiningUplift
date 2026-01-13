package com.vuzili.uplift.init;

import net.minecraft.block.SoundType;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

public class SoundTypeInit extends SoundType
{

	   public SoundTypeInit(float volumeIn, float pitchIn, SoundEvent breakSoundIn, SoundEvent stepSoundIn,
			SoundEvent placeSoundIn, SoundEvent hitSoundIn, SoundEvent fallSoundIn) 
	   {
		super(volumeIn, pitchIn, breakSoundIn, stepSoundIn, placeSoundIn, hitSoundIn, fallSoundIn);
	   }
	   
	   public static final SoundType NONE = new SoundType(0.3F, 8.0F, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundEvents.BLOCK_WOOL_STEP, SoundEvents.BLOCK_WOOL_PLACE, SoundEvents.BLOCK_WOOL_HIT, SoundEvents.BLOCK_WOOL_FALL);

}
