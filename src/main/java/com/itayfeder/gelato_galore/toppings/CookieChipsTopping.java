package com.itayfeder.gelato_galore.toppings;

import com.itayfeder.gelato_galore.init.ItemInit;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CookieChipsTopping extends Topping {
    public CookieChipsTopping() {
        super(new ResourceLocation("gelato_galore", "cookie_chips"), false, ItemInit.COOKIE_CHIPS);
    }

    @Override
    public CompoundTag addToTag(CompoundTag original) {
        return super.addToTag(original);
    }

    @Override
    public Topping changeBasedOnTag(CompoundTag tag) {
        return super.changeBasedOnTag(tag);
    }

    @Override
    public Component getText() {
        String translationText = "tooltip.gelato_galore.cookie_chips";
        String translated = I18n.get(translationText, new Object[0]);
        MutableComponent component = Component.literal("");
        for (int charId = 0; charId < translated.toCharArray().length; charId++) {
            char charIndex = translated.toCharArray()[charId];
            if ((charId % 2) == 0) {
                component = component.append(Component.literal(String.valueOf(charIndex)).withStyle(Style.EMPTY.withColor(6705731)));
            } else {
                component = component.append(Component.literal(String.valueOf(charIndex)).withStyle(Style.EMPTY.withColor(5850165)));
            }
        }
        return component;
    }

    @Override
    public java.util.List<MobEffectInstance> editAppliedEffects(java.util.List<MobEffectInstance> effectInstances) {
        List<MobEffectInstance> newEffects = new ArrayList<>();
        for (MobEffectInstance instance : effectInstances) {
            newEffects.add(new MobEffectInstance(instance.getEffect(), instance.getDuration() * 2, instance.getAmplifier(), instance.isAmbient(), instance.isVisible(), instance.showIcon()));
        }
        return super.editAppliedEffects(newEffects);
    }
}
