package com.itayfeder.gelato_galore.data;

import com.itayfeder.gelato_galore.GelatoGalore;
import com.itayfeder.gelato_galore.GelatoGaloreTags;
import com.itayfeder.gelato_galore.init.ItemInit;
import com.itayfeder.gelato_galore.recipes.ShapelessStackRecipeBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCookingSerializer;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

public class GelatoRecipesGen extends RecipeProvider {
    public GelatoRecipesGen(DataGenerator p_125973_) {
        super(p_125973_);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> p_176532_) {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(Items.SUGAR), ItemInit.CARAMEL.get(), 0.35F, 200).unlockedBy(getHasName(Items.SUGAR), has(Items.SUGAR)).save(p_176532_);
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ItemInit.VANILLA_PODS.get()), ItemInit.DRIED_VANILLA_PODS.get(), 0.25F, 200).unlockedBy(getHasName(ItemInit.VANILLA_PODS.get()), has(ItemInit.VANILLA_PODS.get())).save(p_176532_);
        cookNewCampfireRecipes(p_176532_, "campfire_cooking", RecipeSerializer.CAMPFIRE_COOKING_RECIPE, 600);
        cookNewSmokerRecipes(p_176532_, "smoking", RecipeSerializer.SMOKING_RECIPE, 100);

        ShapedRecipeBuilder.shaped(ItemInit.ICE_CREAM_CONE.get(), 2).define('#', Items.SUGAR).define('X', Items.WHEAT).pattern("   ").pattern("X#X").pattern(" X ").unlockedBy(getHasName(Items.SUGAR), has(Items.SUGAR)).save(p_176532_);
        ShapedRecipeBuilder.shaped(ItemInit.COOKIE_CHIPS.get(), 1).define('I', Items.IRON_INGOT).define('B', ItemTags.BUTTONS).define('G', Tags.Items.GLASS).define('C', Items.COOKIE).pattern("IBI").pattern("GCG").pattern(" I ").unlockedBy(getHasName(Items.COOKIE), has(Items.COOKIE)).save(p_176532_);
        ShapelessRecipeBuilder.shapeless(ItemInit.RAINBOW_SPRINKLES.get(), 1).requires(Items.RED_DYE).requires(Items.ORANGE_DYE).requires(Items.YELLOW_DYE).requires(Items.LIME_DYE).requires(Items.LIGHT_BLUE_DYE).requires(Items.PURPLE_DYE).requires(ItemInit.COOKIE_CHIPS.get()).unlockedBy(getHasName(ItemInit.COOKIE_CHIPS.get()), has(ItemInit.COOKIE_CHIPS.get())).save(p_176532_);

        for (TagKey<Item> itemTag : GelatoGaloreTags.ItemTags.INGREDIENT_TAGS) {
            ItemStack is = new ItemStack(ItemInit.SYRUP.get(), 1);
            is.getOrCreateTag().putString("Filling", itemTag.location().toString());
            ShapelessStackRecipeBuilder.shapeless(is).requires(Items.SUGAR).requires(GelatoGaloreTags.ItemTags.FORGE_MILK).requires(itemTag).unlockedBy("has_ingredient", has(itemTag)).save(p_176532_, itemTag.location().toString() + "_syrup");
        }

    }

    protected static void cookNewCampfireRecipes(Consumer<FinishedRecipe> p_126007_, String p_126008_, SimpleCookingSerializer<?> p_126009_, int p_126010_) {
        SimpleCookingRecipeBuilder.cooking(Ingredient.of(Items.SUGAR), ItemInit.CARAMEL.get(), 0.35F, p_126010_, p_126009_).unlockedBy(getHasName(Items.SUGAR), has(Items.SUGAR)).save(p_126007_, new ResourceLocation(GelatoGalore.MODID, getItemName(ItemInit.CARAMEL.get()) + "_from_" + p_126008_));
        SimpleCookingRecipeBuilder.cooking(Ingredient.of(ItemInit.VANILLA_PODS.get()), ItemInit.DRIED_VANILLA_PODS.get(), 0.25F, p_126010_, p_126009_).unlockedBy(getHasName(ItemInit.VANILLA_PODS.get()), has(ItemInit.VANILLA_PODS.get())).save(p_126007_, new ResourceLocation(GelatoGalore.MODID, getItemName(ItemInit.DRIED_VANILLA_PODS.get()) + "_from_" + p_126008_));

    }

    protected static void cookNewSmokerRecipes(Consumer<FinishedRecipe> p_126007_, String p_126008_, SimpleCookingSerializer<?> p_126009_, int p_126010_) {
        SimpleCookingRecipeBuilder.cooking(Ingredient.of(ItemInit.VANILLA_PODS.get()), ItemInit.DRIED_VANILLA_PODS.get(), 0.25F, p_126010_, p_126009_).unlockedBy(getHasName(ItemInit.VANILLA_PODS.get()), has(ItemInit.VANILLA_PODS.get())).save(p_126007_, new ResourceLocation(GelatoGalore.MODID, getItemName(ItemInit.DRIED_VANILLA_PODS.get()) + "_from_" + p_126008_));
    }
}
