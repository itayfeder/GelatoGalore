package com.itayfeder.gelato_galore.toppings;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Topping implements Cloneable {
    public static final Map<ResourceLocation, Topping> TOPPINGS = new HashMap<>();

    public final ResourceLocation location;
    public final boolean colorable;
    public final RegistryObject<Item> item;

    public Topping(ResourceLocation location, boolean colorable, RegistryObject<Item> item) {
        this.location = location;
        this.colorable = colorable;
        this.item = item;

        TOPPINGS.put(location, this);
    }

    public CompoundTag createTag() {
        CompoundTag tag = new CompoundTag();
        tag.putString("id", this.location.toString());
        addToTag(tag);
        return tag;

    }

    public int getColor() {
        if (!this.colorable) return -1;
        return 0;
    }

    public Item getItem() {
        return item.get();
    }

    public CompoundTag addToTag(CompoundTag original) {
        return original;
    }

    public Topping changeBasedOnTag(CompoundTag tag) {
        return this;
    }

    public Topping copy() {
        try {
            return this.getClass().cast(this.clone());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return Toppings.EMPTY;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Component getText() {
        return Component.literal("None");
    }

    public List<MobEffectInstance> editAppliedEffects(List<MobEffectInstance> effectInstances) {
        return effectInstances;
    }

    public static Topping readTag(CompoundTag tag) {
        ResourceLocation location = ResourceLocation.tryParse(tag.getString("id"));
        if (location != null && TOPPINGS.get(location) != null) {
            //Topping topping = TOPPINGS.get(location).copy().changeBasedOnTag(tag);
            return TOPPINGS.get(location).copy().changeBasedOnTag(tag);
        }
        return Toppings.EMPTY;
    }

}
