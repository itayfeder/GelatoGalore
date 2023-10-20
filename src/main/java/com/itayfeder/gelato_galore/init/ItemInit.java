package com.itayfeder.gelato_galore.init;

import com.itayfeder.gelato_galore.GelatoGalore;
import com.itayfeder.gelato_galore.items.IceCreamItem;
import com.itayfeder.gelato_galore.items.SyrupItem;
import com.itayfeder.gelato_galore.items.api.ToppingItem;
import com.itayfeder.gelato_galore.toppings.Toppings;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, GelatoGalore.MODID);

    public static final CreativeModeTab TAB = new CreativeModeTab(GelatoGalore.MODID) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Items.ICE, 1);
        }
    };

    public static final RegistryObject<Item> CARAMEL = ITEMS.register("caramel", () -> new Item((new Item.Properties()).food((new FoodProperties.Builder()).nutrition(2).saturationMod(0.3F).build()).tab(TAB).stacksTo(64)));

    public static final RegistryObject<Item> VANILLA_PODS = ITEMS.register("vanilla_pods", () -> new ItemNameBlockItem(BlockInit.VANILLA_VINE.get(), (new Item.Properties()).tab(TAB).stacksTo(64)));
    public static final RegistryObject<Item> DRIED_VANILLA_PODS = ITEMS.register("dried_vanilla_pods", () -> new Item((new Item.Properties()).tab(TAB).stacksTo(64)));

    public static final RegistryObject<Item> SYRUP = ITEMS.register("syrup", () -> new SyrupItem((new Item.Properties()).tab(TAB).stacksTo(16)));
    public static final RegistryObject<Item> ICE_CREAM_CONE = ITEMS.register("ice_cream_cone", () -> new Item((new Item.Properties()).tab(TAB).stacksTo(64)));

    public static final RegistryObject<Item> ICE_CREAM_ONE = ITEMS.register("ice_cream_one", () -> new IceCreamItem(1, (new Item.Properties()).tab(TAB).stacksTo(1)));
    public static final RegistryObject<Item> ICE_CREAM_TWO = ITEMS.register("ice_cream_two", () -> new IceCreamItem(2, (new Item.Properties()).tab(TAB).stacksTo(1)));
    public static final RegistryObject<Item> ICE_CREAM_THREE = ITEMS.register("ice_cream_three", () -> new IceCreamItem(3, (new Item.Properties()).tab(TAB).stacksTo(1)));

    public static final RegistryObject<Item> RAINBOW_SPRINKLES = ITEMS.register("rainbow_sprinkles", () -> new ToppingItem((new Item.Properties()).tab(TAB).durability(8), Toppings.RAINBOW_SPRINKLES, true));
    public static final RegistryObject<Item> COOKIE_CHIPS = ITEMS.register("cookie_chips", () -> new ToppingItem((new Item.Properties()).tab(TAB).durability(8), Toppings.COOKIE_CHIPS, true));

}
