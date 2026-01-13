package com.vuzili.uplift.recipes;

import com.google.gson.JsonObject;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class UpliftRecipeSerializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<UpliftRecipe>
{

	@Override
	public UpliftRecipe read(ResourceLocation recipeId, JsonObject json) {

		ItemStack output = CraftingHelper.getItemStack(JSONUtils.getJsonObject(json, "output"), true);
		Ingredient input = Ingredient.deserialize(JSONUtils.getJsonObject(json, "input"));
		
		return new UpliftRecipe(recipeId, input, output);
	}

	@Override
	public UpliftRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
		
		ItemStack output = buffer.readItemStack();
		Ingredient input = Ingredient.read(buffer);
		
		return new UpliftRecipe(recipeId, input, output);
	}

	@Override
	public void write(PacketBuffer buffer, UpliftRecipe recipe) {

		// Write order must match read(): first output ItemStack, then input Ingredient
		buffer.writeItemStack(recipe.getRecipeOutput(), false);

		Ingredient input = recipe.getIngredients().get(0);
		input.write(buffer);
	}


}
