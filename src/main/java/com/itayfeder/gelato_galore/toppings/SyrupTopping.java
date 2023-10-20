package com.itayfeder.gelato_galore.toppings;

import com.itayfeder.gelato_galore.init.ItemInit;
import com.itayfeder.gelato_galore.reload.FlavorData;
import com.itayfeder.gelato_galore.reload.FlavorDataReloadListener;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;

import java.util.List;

public class SyrupTopping extends Topping {
    public FlavorData flavor;

    public SyrupTopping() {
        super(new ResourceLocation("gelato_galore", "syrup"), true, ItemInit.SYRUP);
    }

    @Override
    public CompoundTag addToTag(CompoundTag original) {
        original.putString("Flavor", flavor != null ? flavor.id.toString() : "gelato_galore:chocolate");
        return super.addToTag(original);
    }

    @Override
    public Topping changeBasedOnTag(CompoundTag tag) {
        flavor = FlavorDataReloadListener.INSTANCE.FLAVOR_MAP.get(ResourceLocation.tryParse(tag.getString("Flavor")));
        return super.changeBasedOnTag(tag);
    }

    @Override
    public int getColor() {
        return flavor.color;
    }

    @Override
    public List<MobEffectInstance> editAppliedEffects(List<MobEffectInstance> effectInstances) {
        effectInstances.add(flavor.effect.constructModified(-flavor.effect.duration / 2, 0));
        return super.editAppliedEffects(effectInstances);
    }

    @Override
    public Component getText() {
        return Component.translatable("flavor." + flavor.name, new Object[0]).withStyle(Style.EMPTY.withColor(flavor.color))
                .append(Component.literal(" "))
                .append(Component.translatable("tooltip.gelato_galore.syrup", new Object[0]).withStyle(Style.EMPTY.withColor(flavor.color)));
    }
}
