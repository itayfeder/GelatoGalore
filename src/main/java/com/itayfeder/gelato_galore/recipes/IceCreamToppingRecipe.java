package com.itayfeder.gelato_galore.recipes;

import com.itayfeder.gelato_galore.init.RecipeInit;
import com.itayfeder.gelato_galore.items.IceCreamItem;
import com.itayfeder.gelato_galore.items.api.ToppingItem;
import com.itayfeder.gelato_galore.toppings.Topping;
import com.itayfeder.gelato_galore.toppings.Toppings;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

public class IceCreamToppingRecipe extends CustomRecipe {
    public IceCreamToppingRecipe(ResourceLocation p_43833_) {
        super(p_43833_);
    }

    @Override
    public boolean matches(CraftingContainer p_44324_, Level p_44325_) {
        int i = 0;
        int j = 0;

        for(int k = 0; k < p_44324_.getContainerSize(); ++k) {
            ItemStack itemstack = p_44324_.getItem(k);
            if (!itemstack.isEmpty()) {
                if (itemstack.getItem() instanceof IceCreamItem) {
                    ++i;
                } else {
                    if (!(itemstack.getItem() instanceof ToppingItem)) {
                        return false;
                    }

                    ++j;
                }

                if (j > 1 || i > 1) {
                    return false;
                }
            }
        }

        return i == 1 && j == 1;
    }

    @Override
    public ItemStack assemble(CraftingContainer p_44001_) {
        ItemStack itemstack = ItemStack.EMPTY;
        Topping topping = Toppings.EMPTY;

        for(int i = 0; i < p_44001_.getContainerSize(); ++i) {
            ItemStack itemstack1 = p_44001_.getItem(i);
            if (!itemstack1.isEmpty()) {
                Item item = itemstack1.getItem();
                if (item instanceof IceCreamItem) {
                    itemstack = itemstack1;
                } else {
                    Topping tmp = ((ToppingItem)itemstack1.getItem()).getTopping(itemstack1);
                    if (tmp != null) topping = tmp;
                }
            }
        }

        ItemStack itemstack2 = itemstack.copy();
        if (itemstack.hasTag()) {
            itemstack2.setTag(itemstack.getTag().copy());
        }
        IceCreamItem.setTopping(itemstack2, topping);
        return itemstack2;
    }

    @Override
    public boolean canCraftInDimensions(int p_44314_, int p_44315_) {
        return p_44314_ * p_44315_ >= 2;
    }


    @Override
    public RecipeSerializer<?> getSerializer() {
        return RecipeInit.ICE_CREAM_TOPPING.get();
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems(CraftingContainer p_44004_) {
        return super.getRemainingItems(p_44004_);
    }
}
