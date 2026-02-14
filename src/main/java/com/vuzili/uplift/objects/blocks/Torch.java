package com.vuzili.uplift.objects.blocks;

import java.util.Random;

import com.vuzili.uplift.init.BlockInit;

import net.minecraft.block.BlockState;
import net.minecraft.block.TorchBlock;
import net.minecraft.particles.RedstoneParticleData;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Torch extends TorchBlock
{
	//1.0f = 255/255
	public static final RedstoneParticleData RUBY_TORCH_DUST = new RedstoneParticleData(0.9F, 0.0F, 0.0F, 1.0F);
	public static final RedstoneParticleData BURNING_DIAMOND_TORCH_DUST = new RedstoneParticleData(1.0F, 0.33F, 0.11F, 1.0F);
	public static final RedstoneParticleData SAPPHIRE_TORCH_DUST = new RedstoneParticleData(0.0F, 0.0F, 0.9F, 1.0F);
	public static final RedstoneParticleData TOURMALINE_TORCH_DUST = new RedstoneParticleData(0.60F, 0.05F, 1.0F, 1.0F);
	public static final RedstoneParticleData TASMANITE_TORCH_DUST = new RedstoneParticleData(0.87F, 1.0F, 0.0F, 1.0F);
	public static final RedstoneParticleData SHADOWGLASS_TORCH_DUST = new RedstoneParticleData(0.415F, 0.157F, 0.792F, 1.0F); // 106, 40, 202
	public static final RedstoneParticleData OPAL_TORCH_DUST = new RedstoneParticleData(0.6F, 0.6F, 0.6F, 1.0F);
	public static final RedstoneParticleData ENDER_TORCH_DUST = new RedstoneParticleData(0.176F, 0.321F, 0.282F, 1.0F);

	public Torch(Properties properties) 
	{
		super(properties);
	}
	
	@Override
	public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) 
	{
		double d0 = (double)pos.getX() + 0.5D;
		double d1 = (double)pos.getY() + 0.7D;
		double d2 = (double)pos.getZ() + 0.5D;
		if(this.getBlock() == BlockInit.ruby_torch)
		{
			worldIn.addParticle(RUBY_TORCH_DUST, d0, d1, d2, 0.0D, 0.0D, 0.0D);
		}
		else if(this.getBlock() == BlockInit.burning_diamond_torch)
		{
			worldIn.addParticle(BURNING_DIAMOND_TORCH_DUST, d0, d1, d2, 0.0D, 0.0D, 0.0D);
		}
		else if(this.getBlock() == BlockInit.sapphire_torch)
		{
			worldIn.addParticle(SAPPHIRE_TORCH_DUST, d0, d1, d2, 0.0D, 0.0D, 0.0D);
		}
		else if(this.getBlock() == BlockInit.tourmaline_torch)
		{
			worldIn.addParticle(TOURMALINE_TORCH_DUST, d0, d1, d2, 0.0D, 0.0D, 0.0D);
		}
		else if(this.getBlock() == BlockInit.tasmanite_torch)
		{
			worldIn.addParticle(TASMANITE_TORCH_DUST, d0, d1, d2, 0.0D, 0.0D, 0.0D);
		}
		else if(this.getBlock() == BlockInit.obsidian_torch)
		{
			worldIn.addParticle(SHADOWGLASS_TORCH_DUST, d0, d1, d2, 0.0D, 0.0D, 0.0D);
		}
		else if(this.getBlock() == BlockInit.opal_torch)
		{
			worldIn.addParticle(OPAL_TORCH_DUST, d0, d1, d2, 0.0D, 0.0D, 0.0D);
		}
		else if(this.getBlock() == BlockInit.ender_torch)
		{
			worldIn.addParticle(ENDER_TORCH_DUST, d0, d1, d2, 0.0D, 0.0D, 0.0D);
		}
	}
	
}