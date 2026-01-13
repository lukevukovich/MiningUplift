package com.vuzili.uplift.objects.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FireBlock;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.RedstoneParticleData;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class IgniterFire extends FireBlock
{
	
	public static final RedstoneParticleData IGNITER_SMOKE = new RedstoneParticleData(1.0F, 0.33F, 0.11F, 1.0F);

	public IgniterFire(Properties builder) 
	{
		super(builder);
	}

	@Override
	public boolean isTransparent(BlockState state) 
	{
		return true;
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) 
	{
		return VoxelShapes.fullCube();
	}
	
	@Override
	public boolean addDestroyEffects(BlockState state, World world, BlockPos pos, ParticleManager manager) 
	{
		return true;
	}
	
	@Override
	public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
	      if (rand.nextInt(24) == 0) {
		         worldIn.playSound((double)((float)pos.getX() + 0.5F), (double)((float)pos.getY() + 0.5F), (double)((float)pos.getZ() + 0.5F), SoundEvents.BLOCK_FIRE_AMBIENT, SoundCategory.BLOCKS, 1.0F + rand.nextFloat(), rand.nextFloat() * 0.7F + 0.3F, false);
		      }

		      BlockPos blockpos = pos.down();
		      BlockState blockstate = worldIn.getBlockState(blockpos);
		      if (!this.canCatchFire(worldIn, blockpos, Direction.UP) && !Block.hasSolidSide(blockstate, worldIn, blockpos, Direction.UP)) {
		         if (this.canCatchFire(worldIn, blockpos.west(), Direction.EAST)) {
		            for(int j = 0; j < 2; ++j) {
		               double d3 = (double)pos.getX() + rand.nextDouble() * (double)0.1F;
		               double d8 = (double)pos.getY() + rand.nextDouble();
		               double d13 = (double)pos.getZ() + rand.nextDouble();
		               worldIn.addParticle(IGNITER_SMOKE, d3, d8, d13, 0.0D, 0.0D, 0.0D);
		            }
		         }

		         if (this.canCatchFire(worldIn, pos.east(), Direction.WEST)) {
		            for(int k = 0; k < 2; ++k) {
		               double d4 = (double)(pos.getX() + 1) - rand.nextDouble() * (double)0.1F;
		               double d9 = (double)pos.getY() + rand.nextDouble();
		               double d14 = (double)pos.getZ() + rand.nextDouble();
		               worldIn.addParticle(IGNITER_SMOKE, d4, d9, d14, 0.0D, 0.0D, 0.0D);
		            }
		         }

		         if (this.canCatchFire(worldIn, pos.north(), Direction.SOUTH)) {
		            for(int l = 0; l < 2; ++l) {
		               double d5 = (double)pos.getX() + rand.nextDouble();
		               double d10 = (double)pos.getY() + rand.nextDouble();
		               double d15 = (double)pos.getZ() + rand.nextDouble() * (double)0.1F;
		               worldIn.addParticle(IGNITER_SMOKE, d5, d10, d15, 0.0D, 0.0D, 0.0D);
		            }
		         }

		         if (this.canCatchFire(worldIn, pos.south(), Direction.NORTH)) {
		            for(int i1 = 0; i1 < 2; ++i1) {
		               double d6 = (double)pos.getX() + rand.nextDouble();
		               double d11 = (double)pos.getY() + rand.nextDouble();
		               double d16 = (double)(pos.getZ() + 1) - rand.nextDouble() * (double)0.1F;
		               worldIn.addParticle(IGNITER_SMOKE, d6, d11, d16, 0.0D, 0.0D, 0.0D);
		            }
		         }

		         if (this.canCatchFire(worldIn, pos.up(), Direction.DOWN)) {
		            for(int j1 = 0; j1 < 2; ++j1) {
		               double d7 = (double)pos.getX() + rand.nextDouble();
		               double d12 = (double)(pos.getY() + 1) - rand.nextDouble() * (double)0.1F;
		               double d17 = (double)pos.getZ() + rand.nextDouble();
		               worldIn.addParticle(IGNITER_SMOKE, d7, d12, d17, 0.0D, 0.0D, 0.0D);
		            }
		         }
		      } else {
		         for(int i = 0; i < 3; ++i) {
		            double d0 = (double)pos.getX() + rand.nextDouble();
		            double d1 = (double)pos.getY() + rand.nextDouble() * 0.5D + 0.5D;
		            double d2 = (double)pos.getZ() + rand.nextDouble();
		            worldIn.addParticle(IGNITER_SMOKE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
		         }
		      }

	} 
	
	@SuppressWarnings("deprecation")
	@Override
	public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) 
	{
		if (worldIn.isRaining() == true)
		{
			if (entityIn instanceof PlayerEntity)
			{
				entityIn.attackEntityFrom(DamageSource.IN_FIRE, 1.0f);
			}
			else
			{
				entityIn.attackEntityFrom(DamageSource.IN_FIRE, 4.0f);
			}
		}
		else
		{
			if (entityIn instanceof PlayerEntity)
			{
				entityIn.setFire(1);
			}
			else
			{
				entityIn.attackEntityFrom(DamageSource.IN_FIRE, 6.0f);
				entityIn.setFire(30);
			}
		}
		super.onEntityCollision(state, worldIn, pos, entityIn);
	}
	
	@Override
	public void setFireInfo(Block blockIn, int encouragement, int flammability) {
		// TODO Auto-generated method stub
		super.setFireInfo(Blocks.TNT, 5, 100);
	}
	

		
}