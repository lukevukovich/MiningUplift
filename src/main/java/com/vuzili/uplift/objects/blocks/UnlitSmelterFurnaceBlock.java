package com.vuzili.uplift.objects.blocks;

import com.vuzili.uplift.init.ItemInit;
import com.vuzili.uplift.init.ModTileEntityTypes;
import com.vuzili.uplift.tileentity.SmelterFurnaceTileEntity;
import com.vuzili.uplift.util.UpliftItemHandler;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class UnlitSmelterFurnaceBlock extends Block {

	public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

	public UnlitSmelterFurnaceBlock(Properties properties) {
		super(properties);
		this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));
	}

	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		super.fillStateContainer(builder);
		builder.add(FACING);
	}

	@Override
	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.toRotation(state.get(FACING)));
	}

	@Override
	public BlockState rotate(BlockState state, Rotation rot) {
		return state.with(FACING, rot.rotate(state.get(FACING)));
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
	}

	@Override
	public boolean hasComparatorInputOverride(BlockState state) {
		return true;
	}

	@Override
	public int getComparatorInputOverride(BlockState blockState, World worldIn, BlockPos pos) {
		return Container.calcRedstone(worldIn.getTileEntity(pos));
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return ModTileEntityTypes.SMELTER_FURNACE.get().create();
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
		super.onBlockAdded(state, worldIn, pos, oldState, isMoving);
		TileEntity te = worldIn.getTileEntity(pos);
		if (te instanceof SmelterFurnaceTileEntity) {
			SmelterFurnaceTileEntity smelter = (SmelterFurnaceTileEntity) te;
			smelter.fuelTicksRemaining = 0;
			smelter.currentSmeltTime = 0;
			smelter.markDirty();
		}
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
		TileEntity te = worldIn.getTileEntity(pos);
		if (te instanceof SmelterFurnaceTileEntity) {
			SmelterFurnaceTileEntity smelter = (SmelterFurnaceTileEntity) te;
			smelter.fuelTicksRemaining = 0;
			smelter.currentSmeltTime = 0;
			smelter.markDirty();
		}
	}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
			Hand handIn, BlockRayTraceResult hit) {
		// If holding igniter, let the item handle (turns block into lit smelter)
		ItemStack held = player.getHeldItem(handIn);
		if (!held.isEmpty() && held.getItem() == ItemInit.igniter) {
			return ActionResultType.PASS;
		}
		if (worldIn != null && !worldIn.isRemote) {
			TileEntity tile = worldIn.getTileEntity(pos);
			if (tile instanceof SmelterFurnaceTileEntity) {
				NetworkHooks.openGui((ServerPlayerEntity) player, (INamedContainerProvider) tile, pos);
				return ActionResultType.SUCCESS;
			}
		}
		return ActionResultType.SUCCESS;
	}

	@Override
	public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
		TileEntity tile = worldIn.getTileEntity(pos);
		// Preserve inventory and tile when switching to lit variant
		if (!worldIn.isRemote && tile instanceof SmelterFurnaceTileEntity && state.getBlock() != newState.getBlock()) {
			if (newState.getBlock() instanceof SmelterFurnaceBlock) {
				// keep tile, don't drop
			} else {
				// Dropping when replaced by non-smelter block
				SmelterFurnaceTileEntity furnace = (SmelterFurnaceTileEntity) tile;
				((UpliftItemHandler) furnace.getInventory()).toNonNullList().forEach(item -> {
					worldIn.addEntity(new net.minecraft.entity.item.ItemEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), item));
				});
			}
		}

		// Only remove tile entity if not swapping to lit variant
		if (state.hasTileEntity() && state.getBlock() != newState.getBlock()) {
			if (!(newState.getBlock() instanceof SmelterFurnaceBlock)) {
				worldIn.removeTileEntity(pos);
			}
		}
	}
}