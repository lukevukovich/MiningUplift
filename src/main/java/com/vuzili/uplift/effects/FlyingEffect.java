package com.vuzili.uplift.effects;

import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

public class FlyingEffect extends Effect {
	
	private static int r = 212;
	private static int g = 218;
	private static int b = 146;
	private static int decimalColor = (r * 65536) + (g * 256) + b;

    public FlyingEffect() {
        super(EffectType.BENEFICIAL, decimalColor); // White color
    }

}
