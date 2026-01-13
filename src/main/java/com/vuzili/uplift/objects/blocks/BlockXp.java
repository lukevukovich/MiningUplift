package com.vuzili.uplift.objects.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class BlockXp extends Block {

    public BlockXp(Properties properties) {
        super(properties);
    }

    @Override
    public void harvestBlock(World worldIn, PlayerEntity player, BlockPos pos, BlockState state, net.minecraft.tileentity.TileEntity te, net.minecraft.item.ItemStack stack) {
        if (!worldIn.isRemote && state.getHarvestTool() == ToolType.PICKAXE) {
            // Check if the game mode is Survival
            if (!player.isCreative() && !player.isSpectator()) {
                Random rand = new Random();
                ExperienceOrbEntity xpOrb = new ExperienceOrbEntity(worldIn, (double) pos.getX() + 0.5D, (double) pos.getY() + 0.5D, (double) pos.getZ() + 0.5D, rand.nextInt(4) + 1);
                worldIn.addEntity(xpOrb);
            }
        }
        super.harvestBlock(worldIn, player, pos, state, te, stack);
    }
}
