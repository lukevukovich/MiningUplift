package com.vuzili.uplift.objects.blocks;

import java.util.Random;

import com.vuzili.uplift.init.BlockInit;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.WallTorchBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.RedstoneParticleData;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class WallTorch extends WallTorchBlock 
{
	//1.0f = 255/255
	public static final RedstoneParticleData RUBY_TORCH_DUST = new RedstoneParticleData(0.9F, 0.0F, 0.0F, 1.0F);
	public static final RedstoneParticleData BURNING_DIAMOND_TORCH_DUST = new RedstoneParticleData(1.0F, 0.33F, 0.11F, 1.0F);
	public static final RedstoneParticleData SAPPHIRE_TORCH_DUST = new RedstoneParticleData(0.0F, 0.0F, 0.9F, 1.0F);
	public static final RedstoneParticleData AMETHYST_TORCH_DUST = new RedstoneParticleData(0.60F, 0.05F, 1.0F, 1.0F);
	public static final RedstoneParticleData TASMANITE_TORCH_DUST = new RedstoneParticleData(0.87F, 1.0F, 0.0F, 1.0F);
	public static final RedstoneParticleData OBSIDIAN_TORCH_DUST = new RedstoneParticleData(0.08F, 0.08F, 0.08F, 1.0F);
	public static final RedstoneParticleData OPAL_TORCH_DUST = new RedstoneParticleData(0.8F, 0.8F, 0.8F, 1.0F);
	public static final RedstoneParticleData ENDER_TORCH_DUST = new RedstoneParticleData(0.176F, 0.321F, 0.282F, 1.0F);
	public static final RedstoneParticleData BLOODSTONE_TORCH_DUST = new RedstoneParticleData(0.427F, 0.063F, 0.063F, 1.0F);
	
	public WallTorch(Properties properties) 
	{
		super(properties);
	}
	
	@Override
	public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos,
			PlayerEntity player) {
		
		Block block = state.getBlock();
		
		if (block == BlockInit.burning_diamond_wall_torch) {
			return new ItemStack(BlockInit.burning_diamond_torch);
		}
		else if (block == BlockInit.ruby_wall_torch) {
			return new ItemStack(BlockInit.ruby_torch);
		}
		else if (block == BlockInit.sapphire_wall_torch) {
			return new ItemStack(BlockInit.sapphire_torch);
		}
		else if (block == BlockInit.amethyst_wall_torch) {
			return new ItemStack(BlockInit.amethyst_torch);
		}
		else if (block == BlockInit.tasmanite_wall_torch) {
			return new ItemStack(BlockInit.tasmanite_torch);
		}
		else if (block == BlockInit.obsidian_wall_torch) {
			return new ItemStack(BlockInit.obsidian_torch);
		}
		else if (block == BlockInit.opal_wall_torch) {
			return new ItemStack(BlockInit.opal_torch);
		}
		else if (block == BlockInit.bloodstone_wall_torch) {
			return new ItemStack(BlockInit.bloodstone_torch);
		}
		else {
			return new ItemStack(BlockInit.ender_torch);
		}
		
	}
	
	@Override
	public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) 
	{
	    Direction direction = stateIn.get(HORIZONTAL_FACING);
	    double d0 = (double)pos.getX() + 0.5D;
	    double d1 = (double)pos.getY() + 0.7D;
	    double d2 = (double)pos.getZ() + 0.5D;
	    Direction direction1 = direction.getOpposite();

		if(this.getBlock() == BlockInit.ruby_wall_torch)
		{
			worldIn.addParticle(RUBY_TORCH_DUST, d0 + 0.27D * (double)direction1.getXOffset(), d1 + 0.22D, d2 + 0.27D * (double)direction1.getZOffset(), 0.0D, 0.0D, 0.0D);
		}
		else if(this.getBlock() == BlockInit.burning_diamond_wall_torch)
		{
			worldIn.addParticle(BURNING_DIAMOND_TORCH_DUST, d0 + 0.27D * (double)direction1.getXOffset(), d1 + 0.22D, d2 + 0.27D * (double)direction1.getZOffset(), 0.0D, 0.0D, 0.0D);
		}
		else if(this.getBlock() == BlockInit.sapphire_wall_torch)
		{
			worldIn.addParticle(SAPPHIRE_TORCH_DUST, d0 + 0.27D * (double)direction1.getXOffset(), d1 + 0.22D, d2 + 0.27D * (double)direction1.getZOffset(), 0.0D, 0.0D, 0.0D);
		}
		else if(this.getBlock() == BlockInit.amethyst_wall_torch)
		{
			worldIn.addParticle(AMETHYST_TORCH_DUST, d0 + 0.27D * (double)direction1.getXOffset(), d1 + 0.22D, d2 + 0.27D * (double)direction1.getZOffset(), 0.0D, 0.0D, 0.0D);
		}
		else if(this.getBlock() == BlockInit.tasmanite_wall_torch)
		{
			worldIn.addParticle(TASMANITE_TORCH_DUST, d0 + 0.27D * (double)direction1.getXOffset(), d1 + 0.22D, d2 + 0.27D * (double)direction1.getZOffset(), 0.0D, 0.0D, 0.0D);
		}
		else if(this.getBlock() == BlockInit.obsidian_wall_torch)
		{
			worldIn.addParticle(OBSIDIAN_TORCH_DUST, d0 + 0.27D * (double)direction1.getXOffset(), d1 + 0.22D, d2 + 0.27D * (double)direction1.getZOffset(), 0.0D, 0.0D, 0.0D);
		}
		else if(this.getBlock() == BlockInit.opal_wall_torch)
		{
			worldIn.addParticle(OPAL_TORCH_DUST, d0 + 0.27D * (double)direction1.getXOffset(), d1 + 0.22D, d2 + 0.27D * (double)direction1.getZOffset(), 0.0D, 0.0D, 0.0D);
		}
		else if(this.getBlock() == BlockInit.ender_wall_torch)
		{
			worldIn.addParticle(ENDER_TORCH_DUST, d0 + 0.27D * (double)direction1.getXOffset(), d1 + 0.22D, d2 + 0.27D * (double)direction1.getZOffset(), 0.0D, 0.0D, 0.0D);
		}
		else if(this.getBlock() == BlockInit.bloodstone_wall_torch)
		{
			worldIn.addParticle(BLOODSTONE_TORCH_DUST, d0 + 0.27D * (double)direction1.getXOffset(), d1 + 0.22D, d2 + 0.27D * (double)direction1.getZOffset(), 0.0D, 0.0D, 0.0D);
		}
	}
	

}
