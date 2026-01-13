package com.vuzili.uplift.objects.itemHandler;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SmelterOutputSlot extends SlotItemHandler {

    public SmelterOutputSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        /*if (getSlotIndex() == 1){
            Item validItem = this.inventory.getStackInSlot(1).getItem();
            if (validItem.equals(ItemInit.burning_diamond)){
                return true;
            }
            if (validItem.equals(ItemInit.platinum_ingot)){
                return true;
            }
        }*/
        return false;
    }
}
