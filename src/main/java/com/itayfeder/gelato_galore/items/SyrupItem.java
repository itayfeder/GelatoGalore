package com.itayfeder.gelato_galore.items;

import com.itayfeder.gelato_galore.items.api.ToppingItem;
import com.itayfeder.gelato_galore.reload.FlavorData;
import com.itayfeder.gelato_galore.reload.FlavorDataReloadListener;
import com.itayfeder.gelato_galore.toppings.SyrupTopping;
import com.itayfeder.gelato_galore.toppings.Topping;
import com.itayfeder.gelato_galore.toppings.Toppings;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;


public class SyrupItem extends ToppingItem {
    public SyrupItem(Properties p_41383_) {
        super(p_41383_, Toppings.SYRUP, false);
    }

    @Override
    public void fillItemCategory(CreativeModeTab p_41391_, NonNullList<ItemStack> p_41392_) {
        if (this.allowedIn(p_41391_)) {
            for (FlavorData data : FlavorDataReloadListener.INSTANCE.FLAVOR_MAP.values()) {
                ItemStack is = new ItemStack(this, 1);
                setFilled(is, data.id);
                p_41392_.add(is);
            }
        }
    }

    @Override
    public Topping getTopping(ItemStack stack) {
        SyrupTopping top = (SyrupTopping) super.getTopping(stack);
        top.flavor = getFilledFlavor(stack);
        return top;
    }

    @Override
    public ItemStack getDefaultInstance() {
        ItemStack def = super.getDefaultInstance();
        setFilled(def, ResourceLocation.tryParse("gelato_galore:chocolate"));
        return def;
    }

    public static void setFilled(ItemStack p_220011_0_, ResourceLocation p_220011_1_) {
        CompoundTag compoundnbt = p_220011_0_.getOrCreateTag();
        compoundnbt.putString("Filling", p_220011_1_.toString());
    }

    public static FlavorData getFilledFlavor(ItemStack p_220012_0_) {
        CompoundTag compoundnbt = p_220012_0_.getOrCreateTag();
        ResourceLocation filled = ResourceLocation.tryParse(compoundnbt.getString("Filling"));
        return FlavorDataReloadListener.INSTANCE.FLAVOR_MAP.get(filled);
    }
    @Override
    public Component getName(ItemStack p_41458_) {
        FlavorData flavor = getFilledFlavor(p_41458_);
        if (flavor == null) return Component.translatable("flavor." + "unknown").append(Component.literal(" ")).append(super.getName(p_41458_));
        return Component.translatable("flavor." + flavor.name).append(Component.literal(" ")).append(super.getName(p_41458_));
    }
}
