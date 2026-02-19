package com.vuzili.uplift.objects.items;

import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemUseContext;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TorchItem extends BlockItem {
    private final Block groundVersion;
    private final Block wallVersion;

    public TorchItem(Block groundVersion, Block wallVersion, Properties properties) {
        super(groundVersion, properties);
        this.groundVersion = groundVersion;
        this.wallVersion = wallVersion;
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        World world = context.getWorld();
        BlockPos clickedPos = context.getPos();
        Direction clickedFace = context.getFace();
        BlockPos placementPos = clickedFace == Direction.DOWN ? clickedPos.down() : clickedPos.offset(clickedFace);
        
        // Check if the block at the target position is already a torch
        if (isTorchAtPosition(world, placementPos)) {
            return ActionResultType.FAIL;
        }

        if (clickedFace == Direction.UP) {
            // Try to place the ground version if the top of a block is clicked
            return placeBlock(context, groundVersion.getDefaultState()) ? ActionResultType.SUCCESS : ActionResultType.FAIL;
        } else if (clickedFace != Direction.DOWN) {
            // Try to place the wall version if a side of a block is clicked
            BlockState wallState = this.wallVersion.getDefaultState().with(BlockStateProperties.HORIZONTAL_FACING, clickedFace);
            if (wallState.isValidPosition(world, placementPos)) {
                return placeBlock(context, wallState) ? ActionResultType.SUCCESS : ActionResultType.FAIL;
            }
        }

        return ActionResultType.FAIL;
    }
    
    private boolean isTorchAtPosition(World world, BlockPos pos) {
        Block block = world.getBlockState(pos).getBlock();
        return block == groundVersion || block == wallVersion;
    }

    private boolean placeBlock(ItemUseContext context, BlockState state) {
        World world = context.getWorld();
        BlockPos pos = context.getPos().offset(context.getFace());
        // Never replace/overwrite an existing block (including other torches, grass, snow layers, etc.)
        if (!world.getBlockState(pos).isAir(world, pos)) {
            return false;
        }
        if (state.isValidPosition(world, pos)) {
            world.setBlockState(pos, state, 11);
            if (context.getPlayer() == null || !context.getPlayer().abilities.isCreativeMode) {
                context.getItem().shrink(1);
            }
            
            // Play the wood placement sound
            world.playSound(
                context.getPlayer(),   // the player who placed the block, can be null for all nearby players to hear
                pos,                   // position where the block was placed
                SoundEvents.BLOCK_WOOD_PLACE, // the sound that will be played
                SoundCategory.BLOCKS,  // this categorizes the sound as a block-related sound
                1.0f,                  // volume level, 1.0f is normal, 0.5f would be half volume, etc.
                0.8f                   // pitch level, 1.0f is normal, lower values lower the pitch
            );
            
            return true;
        }
        return false;
    }
}

