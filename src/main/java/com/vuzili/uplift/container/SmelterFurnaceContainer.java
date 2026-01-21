package com.vuzili.uplift.container;

import java.util.Objects;
import java.util.Random;

import javax.annotation.Nonnull;

import com.vuzili.uplift.init.BlockInit;
import com.vuzili.uplift.init.ModContainerTypes;
import com.vuzili.uplift.objects.itemHandler.SmelterOutputSlot;
import com.vuzili.uplift.tileentity.SmelterFurnaceTileEntity;
import com.vuzili.uplift.util.FunctionalIntReferenceHolder;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ClickType;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.items.SlotItemHandler;

public class SmelterFurnaceContainer extends Container {

	public SmelterFurnaceTileEntity tileEntity;
	private IWorldPosCallable canInteractWithCallable;
	public FunctionalIntReferenceHolder currentSmeltTime;
	public FunctionalIntReferenceHolder fuelTicksRemaining;

	// Server Constructor
	public SmelterFurnaceContainer(final int windowID, final PlayerInventory playerInv,
			final SmelterFurnaceTileEntity tile) {
		super(ModContainerTypes.SMELTER_FURNACE.get(), windowID);

		this.tileEntity = tile;
		this.canInteractWithCallable = IWorldPosCallable.of(tile.getWorld(), tile.getPos());
		
		// Furnace Slots
		this.addSlot(new SlotItemHandler(tile.getInventory(), 0, 56, 34));
		this.addSlot(new SmelterOutputSlot(tile.getInventory(), 1, 116, 35));

		final int slotSizePlus2 = 18;
		final int startX = 8;

		// Hotbar
		int hotbarY = 142;
		for (int column = 0; column < 9; column++) {
			this.addSlot(new Slot(playerInv, column, startX + (column * slotSizePlus2), hotbarY));
		}

		// Main Player Inventory
		final int startY = 84;

		for (int row = 0; row < 3; row++) {
			for (int column = 0; column < 9; column++) {
				this.addSlot(new Slot(playerInv, 9 + (row * 9) + column, startX + (column * slotSizePlus2),
						startY + (row * slotSizePlus2)));
			}
		}

		this.trackInt(currentSmeltTime = new FunctionalIntReferenceHolder(() -> this.tileEntity.currentSmeltTime,
				value -> this.tileEntity.currentSmeltTime = value));
		this.trackInt(fuelTicksRemaining = new FunctionalIntReferenceHolder(() -> this.tileEntity.fuelTicksRemaining,
				value -> this.tileEntity.fuelTicksRemaining = value));
	}

	// Client Constructor
	public SmelterFurnaceContainer(final int windowID, final PlayerInventory playerInv, final PacketBuffer data) {
		this(windowID, playerInv, getTileEntity(playerInv, data));
	}

	private static SmelterFurnaceTileEntity getTileEntity(final PlayerInventory playerInv, final PacketBuffer data) {
		Objects.requireNonNull(playerInv, "playerInv cannot be null");
		Objects.requireNonNull(data, "data cannot be null");
		final TileEntity tileAtPos = playerInv.player.world.getTileEntity(data.readBlockPos());
		if (tileAtPos instanceof SmelterFurnaceTileEntity) {
			return (SmelterFurnaceTileEntity) tileAtPos;
		}
		throw new IllegalStateException("TileEntity is not correct " + tileAtPos);
	}

	@Override
	public boolean canInteractWith(PlayerEntity playerIn) {
		return isWithinUsableDistance(canInteractWithCallable, playerIn, BlockInit.smelter.getBlock())
			|| isWithinUsableDistance(canInteractWithCallable, playerIn, BlockInit.unlit_smelter.getBlock());
	}

	@Nonnull
	@Override
	public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
	    ItemStack itemstack = ItemStack.EMPTY;
	    Slot slot = this.inventorySlots.get(index);

	    if (slot != null && slot.getHasStack()) {
	        ItemStack stack = slot.getStack();
	        itemstack = stack.copy();

	        // Output slot index is 1, input slot index is 0
	        if (index == 1) { // If the player is shift-clicking from the output slot
	            Random rand = new Random();
	            playerIn.giveExperiencePoints((rand.nextInt(4) + 1) * stack.getCount());
	            playerIn.world.playSound(null, playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(), 
	                    SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.PLAYERS, 
	                    0.2f, ((float) rand.nextInt(8) / 10) + 0.5f);

	            if (!this.mergeItemStack(stack, 2, 37, false)) { // Move to player's inventory
	                return ItemStack.EMPTY;
	            }
	            slot.onSlotChange(stack, itemstack);
	        } else if (index >= 2 && index <= 10) { // Hotbar slots
	            if (!this.mergeItemStack(stack, 0, 1, false)) { // Move from hotbar to main inventory
	                if (!this.mergeItemStack(stack, 11, 37, false)) { // Try moving into input slot if possible
	                    return ItemStack.EMPTY;
	                }
	            }
	        } else if (index >= 11) { // Main inventory slots
	            if (!this.mergeItemStack(stack, 0, 1, false)) { // Move from main inventory to hotbar
	                if (!this.mergeItemStack(stack, 2, 10, false)) { // Try moving into input slot if possible
	                    return ItemStack.EMPTY;
	                }
	            }
	        } else if (!this.mergeItemStack(stack, 2, 37, false)) { // Catch-all, mainly for custom slots other than output
	            return ItemStack.EMPTY;
	        }

	        if (stack.isEmpty()) {
	            slot.putStack(ItemStack.EMPTY);
	        } else {
	            slot.onSlotChanged();
	        }

	        if (stack.getCount() == itemstack.getCount()) {
	            return ItemStack.EMPTY;
	        }

	        slot.onTake(playerIn, stack);
	    }

	    return itemstack;
	}

	
	//Used for XP
	@Override
	public ItemStack slotClick(int slotId, int dragType, ClickType clickTypeIn, PlayerEntity player) {
	    // Ensure the slot ID is valid before proceeding
	    if (slotId >= 0 && slotId < this.inventorySlots.size()) {
	        Slot slot = this.inventorySlots.get(slotId);
	        // Check if we are dealing with the output slot (index 1, xPos 116)
	        if (slot != null && slot.getSlotIndex() == 1 && slot.xPos == 116) {
	            ItemStack slotStack = slot.getStack();
	            int count = slotStack.getCount();
	            // If there are items in the output slot and the player is retrieving them
	            if (count > 0 && (clickTypeIn == ClickType.PICKUP || clickTypeIn == ClickType.PICKUP_ALL)) {
	                Random rand = new Random();
	                player.giveExperiencePoints((rand.nextInt(4) + 1) * count);
	                player.world.playSound(null, player.getPosX(), player.getPosY(), player.getPosZ(), 
	                        SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.PLAYERS, 
	                        0.2f, ((float) rand.nextInt(8) / 10) + 0.5f);
	            }
	        }
	    }
	    // Call the superclass method to handle the actual slot click logic
	    return super.slotClick(slotId, dragType, clickTypeIn, player);
	}


	public int getSmeltProgressionScaled() {
		final int smeltBarMaxWidth = 24; // full width of smelt progress bar texture segment
		return this.currentSmeltTime.get() != 0 && this.tileEntity.maxSmeltTime != 0
				? this.currentSmeltTime.get() * smeltBarMaxWidth / this.tileEntity.maxSmeltTime
				: 0;
	}

	public int getFuelProgressionScaled() {
		final int fuelBarMaxWidth = 119; // full width of fuel bar texture segment
		return this.tileEntity.fuelTicksMax != 0
				? this.fuelTicksRemaining.get() * fuelBarMaxWidth / this.tileEntity.fuelTicksMax
				: 0;
	}
}