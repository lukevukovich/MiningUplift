package com.vuzili.uplift.effects;

import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

public class LightEffect extends Effect {

	private static int r = 255;
	private static int g = 255;
	private static int b = 200;
	private static int decimalColor = (r * 65536) + (g * 256) + b;

	public LightEffect() {
		super(EffectType.BENEFICIAL, decimalColor);
	}

}
