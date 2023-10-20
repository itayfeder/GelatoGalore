package com.itayfeder.gelato_galore.toppings;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

public class Toppings {
    public static final Topping EMPTY = new Topping(new ResourceLocation("gelato_galore", "empty"), false, null);
    public static final Topping SYRUP = new SyrupTopping();
    public static final Topping RAINBOW_SPRINKLES = new RainbowSprinklesTopping();
    public static final Topping COOKIE_CHIPS = new CookieChipsTopping();

    public static void Init() {

    }
}
