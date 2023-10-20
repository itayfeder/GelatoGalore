package com.itayfeder.gelato_galore;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.List;

public class GelatoGaloreTags {
    public static class BlockTags {

        private static TagKey<Block> create(String name) {
            return TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(GelatoGalore.MODID, name));
        }
    }

    public static class ItemTags {
        public static final List<TagKey<Item>> INGREDIENT_TAGS = new ArrayList<>();

        public static final TagKey<Item> CHOCOLATE_INGREDIENT = createIngredientTag("chocolate");
        public static final TagKey<Item> SWEET_BERRY_INGREDIENT = createIngredientTag("sweet_berry");
        public static final TagKey<Item> CARAMEL_INGREDIENT = createIngredientTag("caramel");
        public static final TagKey<Item> HONEY_INGREDIENT = createIngredientTag("honey");
        public static final TagKey<Item> VANILLA_INGREDIENT = createIngredientTag("vanilla");
        public static final TagKey<Item> MELON_INGREDIENT = createIngredientTag("melon");

        public static final TagKey<Item> FORGE_MILK = createForgeTag("milk");


        private static TagKey<Item> createIngredientTag(String name) {
            TagKey<Item> itemtag = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(GelatoGalore.MODID, name));
            INGREDIENT_TAGS.add(itemtag);
            return itemtag;
        }

        private static TagKey<Item> createForgeTag(String name) {
            TagKey<net.minecraft.world.item.Item> itemtag = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation("forge", name));
            return itemtag;
        }
    }
}
