package com.itayfeder.gelato_galore.toppings;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.RegistryObject;

public class TickableTopping extends Topping {
    public TickableTopping(ResourceLocation location, boolean colorable, RegistryObject<Item> item) {
        super(location, colorable, item);
    }

    public void tick(ItemStack stack, Level level) {

    }
}
