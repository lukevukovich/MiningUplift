package com.vuzili.uplift.tileentity;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Nullable;

import com.vuzili.uplift.Uplift;
import com.vuzili.uplift.container.SmelterFurnaceContainer;
import com.vuzili.uplift.init.BlockInit;
import com.vuzili.uplift.init.ModTileEntityTypes;
import com.vuzili.uplift.init.RecipeSerializerInit;
import com.vuzili.uplift.recipes.UpliftRecipe;
import com.vuzili.uplift.objects.blocks.UnlitSmelterFurnaceBlock;
import com.vuzili.uplift.util.UpliftItemHandler;
import net.minecraft.block.BlockState;
import net.minecraft.state.properties.BlockStateProperties;

import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.RecipeWrapper;

public class SmelterFurnaceTileEntity extends TileEntity implements ITickableTileEntity, INamedContainerProvider {

	private ITextComponent customName;
	public int currentSmeltTime;
	public final int maxSmeltTime = 150;
	// Fuel system: ticks remaining while smelting; lasts for 100 items worth
	public final int fuelTicksMax = 200 * maxSmeltTime;
	public int fuelTicksRemaining;
	private UpliftItemHandler inventory;

	public SmelterFurnaceTileEntity(TileEntityType<?> tileEntityTypeIn) {
		super(tileEntityTypeIn);

		this.inventory = new UpliftItemHandler(2);
		// New smelters start with full fuel
		this.fuelTicksRemaining = this.fuelTicksMax;
	}

	public SmelterFurnaceTileEntity() {
		this(ModTileEntityTypes.SMELTER_FURNACE.get());
	}

	@Override
	public Container createMenu(final int windowID, final PlayerInventory playerInv, final PlayerEntity playerIn) {
		return new SmelterFurnaceContainer(windowID, playerInv, this);
	}

	@SuppressWarnings("null")
	@Override
	public void tick() {
		boolean dirty = false;

		if (this.world != null && !this.world.isRemote) {
				UpliftRecipe recipe = this.getRecipe(this.inventory.getStackInSlot(0));
				if (recipe != null) {
					boolean canSmelt = this.canSmelt(recipe);
					// Consume fuel and progress only if output can accept the recipe result
					if (canSmelt && this.fuelTicksRemaining > 0) {
						// Use one fuel tick for this smelt step
						this.fuelTicksRemaining--;
						// Increment progress and complete within the same tick when reaching max
						if (this.currentSmeltTime + 1 >= this.maxSmeltTime) {
							this.currentSmeltTime = 0;
							ItemStack output = recipe.getRecipeOutput();
							this.inventory.insertItem(1, output.copy(), false);
							this.inventory.decrStackSize(0, 1);
						} else {
							this.currentSmeltTime++;
						}
						this.world.setBlockState(this.getPos(), this.getBlockState());
						dirty = true;
					} else if (!canSmelt) {
						// Blocked by output: if mismatch, decay progress like when input is removed
						ItemStack outputSlot = this.inventory.getStackInSlot(1);
						ItemStack result = recipe.getRecipeOutput();
						boolean mismatch = !outputSlot.isEmpty() && !ItemStack.areItemsEqual(outputSlot, result);
						if (mismatch && this.currentSmeltTime > 0) {
							int newTime = Math.max(0, this.currentSmeltTime - 1);
							if (newTime != this.currentSmeltTime) {
								this.currentSmeltTime = newTime;
								this.world.setBlockState(this.getPos(), this.getBlockState());
								dirty = true;
							}
						}
					} else {
						// Out of fuel: convert to unlit smelter, preserving facing; reset progress
						BlockState current = this.getBlockState();
						Direction facing = current.has(BlockStateProperties.HORIZONTAL_FACING)
							? current.get(BlockStateProperties.HORIZONTAL_FACING)
							: Direction.NORTH;
						BlockState unlit = BlockInit.unlit_smelter.getDefaultState()
							.with(UnlitSmelterFurnaceBlock.FACING, facing);
						this.currentSmeltTime = 0;
						this.world.setBlockState(this.pos, unlit);
						dirty = true;
					}
				} else {
					// No valid input/recipe; if there is progress, slowly decay it
					if (this.currentSmeltTime > 0) {
						this.currentSmeltTime = Math.max(0, this.currentSmeltTime - 1);
						this.world.setBlockState(this.getPos(), this.getBlockState());
						dirty = true;
					}
				}
		}

		if (dirty) {
			this.markDirty();
			this.world.notifyBlockUpdate(this.getPos(), this.getBlockState(), this.getBlockState(),
					Constants.BlockFlags.BLOCK_UPDATE);
		}
	}

	public void setCustomName(ITextComponent name) {
		this.customName = name;
	}

	public ITextComponent getName() {
		return this.customName != null ? this.customName : this.getDefaultName();
	}

	private ITextComponent getDefaultName() {
		return new TranslationTextComponent("container." + Uplift.MOD_ID + ".smelter_furnace");
	}

	@Override
	public ITextComponent getDisplayName() {
		return this.getName();
	}

	@Nullable
	public ITextComponent getCustomName() {
		return this.customName;
	}

	@Override
	public void read(CompoundNBT compound) {
		super.read(compound);
		if (compound.contains("CustomName", Constants.NBT.TAG_STRING)) {
			this.customName = ITextComponent.Serializer.fromJson(compound.getString("CustomName"));
		}
		
		NonNullList<ItemStack> inv = NonNullList.<ItemStack>withSize(this.inventory.getSlots(), ItemStack.EMPTY);
		ItemStackHelper.loadAllItems(compound, inv);
		this.inventory.setNonNullList(inv);

		this.currentSmeltTime = compound.getInt("CurrentSmeltTime");
		this.fuelTicksRemaining = compound.contains("FuelTicksRemaining", Constants.NBT.TAG_INT)
			? compound.getInt("FuelTicksRemaining")
			: this.fuelTicksMax;

	}

	@Override
	public CompoundNBT write(CompoundNBT compound) {
		super.write(compound);
		if (this.customName != null) {
			compound.putString("CustomName", ITextComponent.Serializer.toJson(this.customName));
		}
		
		ItemStackHelper.saveAllItems(compound, this.inventory.toNonNullList());
		compound.putInt("CurrentSmeltTime", this.currentSmeltTime);
		compound.putInt("FuelTicksRemaining", this.fuelTicksRemaining);

		return compound;
	}

	@Nullable
	private UpliftRecipe getRecipe(ItemStack stack) {
		if (stack == null) {
			return null;
		}

		Set<IRecipe<?>> recipes = findRecipesByType(RecipeSerializerInit.UPLIFT_TYPE, this.world);
		for (IRecipe<?> iRecipe : recipes) {
			UpliftRecipe recipe = (UpliftRecipe) iRecipe;
			if (recipe.matches(new RecipeWrapper(this.inventory), this.world)) {
				return recipe;
			}
		}

		return null;
	}

	public static Set<IRecipe<?>> findRecipesByType(IRecipeType<?> typeIn, World world) {
		return world != null ? world.getRecipeManager().getRecipes().stream()
				.filter(recipe -> recipe.getType() == typeIn).collect(Collectors.toSet()) : Collections.emptySet();
	}

	@SuppressWarnings("resource")
	@OnlyIn(Dist.CLIENT)
	public static Set<IRecipe<?>> findRecipesByType(IRecipeType<?> typeIn) {
		ClientWorld world = Minecraft.getInstance().world;
		return world != null ? world.getRecipeManager().getRecipes().stream()
				.filter(recipe -> recipe.getType() == typeIn).collect(Collectors.toSet()) : Collections.emptySet();
	}

	public static Set<ItemStack> getAllRecipeInputs(IRecipeType<?> typeIn, World worldIn) {
		Set<ItemStack> inputs = new HashSet<ItemStack>();
		Set<IRecipe<?>> recipes = findRecipesByType(typeIn, worldIn);
		for (IRecipe<?> recipe : recipes) {
			NonNullList<Ingredient> ingredients = recipe.getIngredients();
			ingredients.forEach(ingredient -> {
				for (ItemStack stack : ingredient.getMatchingStacks()) {
					inputs.add(stack);
				}
			});
		}
		return inputs;
	}

	public final IItemHandlerModifiable getInventory() {
		return this.inventory;
	}

	@Nullable
	@Override
	public SUpdateTileEntityPacket getUpdatePacket() {
		CompoundNBT nbt = new CompoundNBT();
		this.write(nbt);
		return new SUpdateTileEntityPacket(this.pos, 0, nbt);
	}

	@Override
	public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
		this.read(pkt.getNbtCompound());
	}

	@Override
	public CompoundNBT getUpdateTag() {
		CompoundNBT nbt = new CompoundNBT();
		this.write(nbt);
		return nbt;
	}

	@Override
	public void handleUpdateTag(CompoundNBT nbt) {
		this.read(nbt);
	}

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.orEmpty(cap, LazyOptional.of(() -> this.inventory));
	}

	private boolean canSmelt(UpliftRecipe recipe) {
		if (recipe == null) {
			return false;
		}
		ItemStack result = recipe.getRecipeOutput();
		if (result.isEmpty()) {
			return false;
		}
		ItemStack outputSlot = this.inventory.getStackInSlot(1);
		if (outputSlot.isEmpty()) {
			return true;
		}
		// Must match item type (and tags if needed)
		if (!ItemStack.areItemsEqual(outputSlot, result)) {
			return false;
		}
		// Check stacking capacity
		int combined = outputSlot.getCount() + result.getCount();
		return combined <= outputSlot.getMaxStackSize();
	}
}