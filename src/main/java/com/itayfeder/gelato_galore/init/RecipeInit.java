package com.itayfeder.gelato_galore.init;

import com.itayfeder.gelato_galore.GelatoGalore;
import com.itayfeder.gelato_galore.recipes.IceCreamToppingRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleRecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RecipeInit {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, GelatoGalore.MODID);

    public static final RegistryObject<RecipeSerializer<IceCreamToppingRecipe>> ICE_CREAM_TOPPING = RECIPE_SERIALIZERS.register("special_icecreamtopping", () -> new SimpleRecipeSerializer<>(IceCreamToppingRecipe::new));

}
