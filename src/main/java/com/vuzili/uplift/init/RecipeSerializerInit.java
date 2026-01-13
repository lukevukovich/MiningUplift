package com.vuzili.uplift.init;

import com.vuzili.uplift.Uplift;
import com.vuzili.uplift.recipes.IUpliftRecipe;
import com.vuzili.uplift.recipes.UpliftRecipe;
import com.vuzili.uplift.recipes.UpliftRecipeSerializer;

import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;


public class RecipeSerializerInit {

	public static final IRecipeSerializer<UpliftRecipe> UPLIFT_RECIPE_SERIALIZER = new UpliftRecipeSerializer();
	public static final IRecipeType<IUpliftRecipe> UPLIFT_TYPE = registerType(IUpliftRecipe.RECIPE_TYPE_ID);

	public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZERS = new DeferredRegister<>(
			ForgeRegistries.RECIPE_SERIALIZERS, Uplift.MOD_ID);

	public static final RegistryObject<IRecipeSerializer<?>> UPLIFT_SERIALIZER = RECIPE_SERIALIZERS.register("uplift",
			() -> UPLIFT_RECIPE_SERIALIZER);

	private static class RecipeType<T extends IRecipe<?>> implements IRecipeType<T> {
		@Override
		public String toString() {
			return Registry.RECIPE_TYPE.getKey(this).toString();
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static <T extends IRecipeType> T registerType(ResourceLocation recipeTypeId) {
		return (T) Registry.register(Registry.RECIPE_TYPE, recipeTypeId, new RecipeType<>());
	}
}
