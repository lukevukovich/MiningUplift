package com.vuzili.uplift.client.entity.render;

import com.vuzili.uplift.Uplift;
import com.vuzili.uplift.client.entity.model.StoneEntityModel;
import com.vuzili.uplift.entities.StoneEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class StoneEntityRender extends MobRenderer<StoneEntity, StoneEntityModel<StoneEntity>> {

	protected static final ResourceLocation TEXTURE = new ResourceLocation(Uplift.MOD_ID,
			"textures/entity/stone_entity.png");
	
	public StoneEntityRender(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new StoneEntityModel<StoneEntity>(), 0.3f);
	}
	
	@Override
	public ResourceLocation getEntityTexture(StoneEntity entity) {
		return TEXTURE;
	}
}