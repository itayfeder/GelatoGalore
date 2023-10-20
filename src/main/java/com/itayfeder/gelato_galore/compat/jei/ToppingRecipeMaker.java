package com.itayfeder.gelato_galore.compat.jei;

import com.itayfeder.gelato_galore.GelatoGalore;
import com.itayfeder.gelato_galore.init.ItemInit;
import com.itayfeder.gelato_galore.items.IceCreamItem;
import com.itayfeder.gelato_galore.items.SyrupItem;
import com.itayfeder.gelato_galore.reload.FlavorData;
import com.itayfeder.gelato_galore.reload.FlavorDataReloadListener;
import com.itayfeder.gelato_galore.toppings.Topping;
import com.itayfeder.gelato_galore.toppings.Toppings;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.ShapelessRecipe;
import net.minecraft.world.level.ItemLike;

import java.util.ArrayList;
import java.util.List;

public class ToppingRecipeMaker {
    public static List<CraftingRecipe> createRecipes() {
        List<CraftingRecipe> recipe = new ArrayList<>();
        ItemLike[] vals = new ItemLike[]
                {ItemInit.ICE_CREAM_ONE.get(), ItemInit.ICE_CREAM_TWO.get(), ItemInit.ICE_CREAM_THREE.get()};
        for (Topping topping : Topping.TOPPINGS.values()) {
            if (!topping.location.equals(Toppings.EMPTY.location))
            for (int i = 0; i < vals.length; i++) {
                String group = "jei.topping." + topping.location.getPath() + "_" + (i + 1);
                ResourceLocation id = new ResourceLocation(GelatoGalore.MODID, group);
                Ingredient ice_cream = Ingredient.of(vals[i].asItem().getDefaultInstance());
                Ingredient top_item = Ingredient.of(topping.getItem());

                NonNullList<Ingredient> inputs = NonNullList.of(Ingredient.EMPTY, new Ingredient[]{ice_cream, top_item});

                ItemStack output = vals[i].asItem().getDefaultInstance();
                IceCreamItem.setTopping(output, topping);

                recipe.add(new ShapelessRecipe(id, group, output, inputs));
            }
        }
        return recipe;
    }
}
