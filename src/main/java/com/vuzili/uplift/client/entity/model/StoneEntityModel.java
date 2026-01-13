package com.vuzili.uplift.client.entity.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.vuzili.uplift.entities.StoneEntity;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;


	public class StoneEntityModel<T extends StoneEntity> extends EntityModel<T> {
		private final ModelRenderer LeftLeg;
		private final ModelRenderer RightLeg;
		private final ModelRenderer Body;
		private final ModelRenderer LeftArm;
		private final ModelRenderer RightArm;
		private final ModelRenderer Head;

		public StoneEntityModel() {
			textureWidth = 32;
			textureHeight = 32;

			LeftLeg = new ModelRenderer(this);
			LeftLeg.setRotationPoint(1.0F, 19.0F, 0.0F);
			LeftLeg.setTextureOffset(0, 18).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, false);

			RightLeg = new ModelRenderer(this);
			RightLeg.setRotationPoint(-1.0F, 19.0F, 0.0F);
			RightLeg.setTextureOffset(14, 16).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, false);

			Body = new ModelRenderer(this);
			Body.setRotationPoint(0.0F, 24.0F, 0.0F);
			Body.setTextureOffset(0, 0).addBox(-3.0F, -11.0F, -2.0F, 6.0F, 6.0F, 4.0F, 0.0F, false);

			LeftArm = new ModelRenderer(this);
			LeftArm.setRotationPoint(3.0F, 14.0F, 0.0F);
			LeftArm.setTextureOffset(18, 8).addBox(0.0F, -1.0F, -1.0F, 1.0F, 5.0F, 2.0F, 0.0F, false);

			RightArm = new ModelRenderer(this);
			RightArm.setRotationPoint(-3.0F, 14.0F, 0.0F);
			RightArm.setTextureOffset(8, 18).addBox(-1.0F, -1.0F, -1.0F, 1.0F, 5.0F, 2.0F, 0.0F, false);

			Head = new ModelRenderer(this);
			Head.setRotationPoint(0.0F, 12.0F, 0.0F);
			Head.setTextureOffset(0, 10).addBox(-2.0F, -3.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.0F, false);
		}


		@Override
		public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
			LeftLeg.render(matrixStack, buffer, packedLight, packedOverlay);
			RightLeg.render(matrixStack, buffer, packedLight, packedOverlay);
			Body.render(matrixStack, buffer, packedLight, packedOverlay);
			LeftArm.render(matrixStack, buffer, packedLight, packedOverlay);
			RightArm.render(matrixStack, buffer, packedLight, packedOverlay);
			Head.render(matrixStack, buffer, packedLight, packedOverlay);
		}

		public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		@Override
		public void setRotationAngles(StoneEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks,
				float netHeadYaw, float headPitch) 
		{
			this.Head.rotateAngleX = headPitch * ((float) Math.PI / 700F);
			this.Head.rotateAngleY = netHeadYaw * ((float) Math.PI / 700F);
			//this.Body.rotateAngleZ = ((float)Math.PI / 2F);
			this.LeftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.0F * limbSwingAmount;
			this.LeftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.0F * limbSwingAmount;
			this.RightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.0F * limbSwingAmount;
			this.RightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.0F * limbSwingAmount;
		}
		
	}