package com.itayfeder.gelato_galore.data.advancements;

import com.google.common.collect.ImmutableSet;
import com.itayfeder.gelato_galore.GelatoGalore;
import com.itayfeder.gelato_galore.init.ItemInit;
import com.itayfeder.gelato_galore.items.IceCreamItem;
import com.itayfeder.gelato_galore.items.SyrupItem;
import com.itayfeder.gelato_galore.reload.FlavorDataReloadListener;
import com.itayfeder.gelato_galore.toppings.Topping;
import com.itayfeder.gelato_galore.toppings.Toppings;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.critereon.*;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class GelatoGaloreAdvancements implements Consumer<Consumer<Advancement>>  {
    public static final GelatoGaloreAdvancements INSTANCE = new GelatoGaloreAdvancements();

    private static ResourceLocation[] DEFAULT_FLAVORS = new ResourceLocation[] {
            new ResourceLocation(GelatoGalore.MODID, "chocolate"),
            new ResourceLocation(GelatoGalore.MODID, "sweet_berry"),
            new ResourceLocation(GelatoGalore.MODID, "vanilla"),
            new ResourceLocation(GelatoGalore.MODID, "caramel"),
            new ResourceLocation(GelatoGalore.MODID, "honey"),
            new ResourceLocation(GelatoGalore.MODID, "melon")
    };

    @Override
    public void accept(Consumer<Advancement> advancementConsumer) {
        ItemStack stack = new ItemStack(ItemInit.ICE_CREAM_THREE.get());
        CompoundTag compoundnbt = stack.getOrCreateTag();
        compoundnbt.put("Flavors", ((IceCreamItem)ItemInit.ICE_CREAM_THREE.get()).toListTag(new ResourceLocation[] { new ResourceLocation(GelatoGalore.MODID, "chocolate"), new ResourceLocation(GelatoGalore.MODID, "sweet_berry") , new ResourceLocation(GelatoGalore.MODID, "vanilla") }));

        Advancement root = Advancement.Builder.advancement().display(stack, Component.translatable("advancements.gelato_galore.root.title"), Component.translatable("advancements.gelato_galore.root.description"), new ResourceLocation("textures/block/ice.png"), FrameType.TASK, false, false, false).addCriterion("get_powder_snow", InventoryChangeTrigger.TriggerInstance.hasItems(Items.POWDER_SNOW_BUCKET)).save(advancementConsumer, "gelato_galore:gelato/root");

        ItemStack stack2 = new ItemStack(ItemInit.SYRUP.get());
        SyrupItem.setFilled(stack2, new ResourceLocation(GelatoGalore.MODID, "sweet_berry"));

        Advancement use_syrup =  Advancement.Builder.advancement().parent(root).display(stack2, Component.translatable("advancements.gelato_galore.use_syrup.title"), Component.translatable("advancements.gelato_galore.use_syrup.description"), null, FrameType.TASK, true, true, false).addCriterion("get_syrup", InventoryChangeTrigger.TriggerInstance.hasItems(ItemInit.SYRUP.get())).save(advancementConsumer, "gelato_galore:gelato/use_syrup");

        ItemStack stack3 = new ItemStack(ItemInit.ICE_CREAM_ONE.get());
        CompoundTag compoundnbt2 = stack3.getOrCreateTag();
        compoundnbt2.put("Flavors", ((IceCreamItem)ItemInit.ICE_CREAM_ONE.get()).toListTag(new ResourceLocation[] { new ResourceLocation(GelatoGalore.MODID, "sweet_berry") }));

        Advancement get_ice_cream =  Advancement.Builder.advancement().parent(use_syrup).display(stack3, Component.translatable("advancements.gelato_galore.get_ice_cream.title"), Component.translatable("advancements.gelato_galore.get_ice_cream.description"), null, FrameType.TASK, true, true, false).addCriterion("get_ice_cream", InventoryChangeTrigger.TriggerInstance.hasItems(ItemInit.ICE_CREAM_ONE.get())).save(advancementConsumer, "gelato_galore:gelato/get_ice_cream");

        Advancement full_ice_cream =  Advancement.Builder.advancement().parent(get_ice_cream).display(stack, Component.translatable("advancements.gelato_galore.full_ice_cream.title"), Component.translatable("advancements.gelato_galore.full_ice_cream.description"), null, FrameType.TASK, true, true, false).addCriterion("full_ice_cream", InventoryChangeTrigger.TriggerInstance.hasItems(ItemInit.ICE_CREAM_THREE.get())).save(advancementConsumer, "gelato_galore:gelato/full_ice_cream");

        ItemStack stack4 = new ItemStack(ItemInit.ICE_CREAM_ONE.get());
        CompoundTag compoundnbt3 = stack4.getOrCreateTag();
        compoundnbt3.put("Flavors", ((IceCreamItem)ItemInit.ICE_CREAM_ONE.get()).toListTag(new ResourceLocation[] { new ResourceLocation(GelatoGalore.MODID, "vanilla") }));

        Advancement all_ice_cream =  addVariants(Advancement.Builder.advancement()).parent(get_ice_cream).display(stack4, Component.translatable("advancements.gelato_galore.all_ice_cream.title"), Component.translatable("advancements.gelato_galore.all_ice_cream.description"), null, FrameType.CHALLENGE, true, true, false).rewards(AdvancementRewards.Builder.experience(500)).save(advancementConsumer, "gelato_galore:gelato/all_ice_cream");

        ItemStack stack5 = new ItemStack(ItemInit.ICE_CREAM_ONE.get());
        CompoundTag compoundnbt4 = stack5.getOrCreateTag();
        compoundnbt4.put("Flavors", ((IceCreamItem)ItemInit.ICE_CREAM_ONE.get()).toListTag(new ResourceLocation[] { new ResourceLocation(GelatoGalore.MODID, "vanilla") }));
        compoundnbt4.put("Topping", Toppings.COOKIE_CHIPS.createTag());

        Advancement top_ice_cream =  addTopping(Advancement.Builder.advancement()).parent(get_ice_cream).display(stack5, Component.translatable("advancements.gelato_galore.top_ice_cream.title"), Component.translatable("advancements.gelato_galore.top_ice_cream.description"), null, FrameType.TASK, true, true, false).save(advancementConsumer, "gelato_galore:gelato/top_ice_cream");

    }

    private static Advancement.Builder createAdvancement(String name, ResourceLocation parent, ItemLike icon, FrameType frame, boolean showToast, boolean announceToChat, boolean hidden) {
        return Advancement.Builder.advancement().parent(Advancement.Builder.advancement().build(parent)).display(icon,
                Component.translatable("advancements." + GelatoGalore.MODID + "." + name + ".title"),
                Component.translatable("advancements." + GelatoGalore.MODID + "." + name + ".description"),
                null, frame, showToast, announceToChat, hidden);
    }

    protected static Advancement.Builder addVariants(Advancement.Builder p_123987_) {
        for(ResourceLocation resourcekey : DEFAULT_FLAVORS) {
            ItemStack stack3 = new ItemStack(ItemInit.ICE_CREAM_ONE.get());
            CompoundTag compoundnbt2 = stack3.getOrCreateTag();
            compoundnbt2.put("Flavors", ((IceCreamItem)ItemInit.ICE_CREAM_ONE.get()).toListTag(new ResourceLocation[] { resourcekey }));

            ItemPredicate predicate = new ItemPredicate((TagKey<Item>)null, ImmutableSet.of(ItemInit.ICE_CREAM_ONE.get()), MinMaxBounds.Ints.ANY, MinMaxBounds.Ints.ANY, EnchantmentPredicate.NONE, EnchantmentPredicate.NONE, (Potion)null, new NbtPredicate(compoundnbt2));

            p_123987_.addCriterion(resourcekey.toString(), InventoryChangeTrigger.TriggerInstance.hasItems(predicate));
        }
        return p_123987_;
    }

    protected static Advancement.Builder addTopping(Advancement.Builder p_123987_) {
        for(Topping top : Topping.TOPPINGS.values()) {
            if (!top.location.equals(Toppings.EMPTY.location)) {
                ItemStack stack1 = new ItemStack(ItemInit.ICE_CREAM_ONE.get());
                CompoundTag compoundnbt2 = stack1.getOrCreateTag();
                compoundnbt2.put("Topping", top.createTag());

                ItemPredicate predicate = new ItemPredicate((TagKey<Item>)null, ImmutableSet.of(ItemInit.ICE_CREAM_ONE.get(), ItemInit.ICE_CREAM_TWO.get(), ItemInit.ICE_CREAM_THREE.get()),
                        MinMaxBounds.Ints.ANY, MinMaxBounds.Ints.ANY, EnchantmentPredicate.NONE, EnchantmentPredicate.NONE, (Potion)null, new NbtPredicate(compoundnbt2));
                p_123987_.addCriterion(top.location.toString(), InventoryChangeTrigger.TriggerInstance.hasItems(predicate));
            }
        }

        return p_123987_;
    }
}
