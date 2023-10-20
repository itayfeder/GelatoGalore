package com.itayfeder.gelato_galore.toppings;

import com.itayfeder.gelato_galore.init.ItemInit;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class RainbowSprinklesTopping extends TickableTopping {
    public static float CHROMATIC_NUM = 0;

    public RainbowSprinklesTopping() {
        super(new ResourceLocation("gelato_galore", "rainbow_sprinkles"), false, ItemInit.RAINBOW_SPRINKLES);
    }

    @Override
    public void tick(ItemStack stack, Level level) {
        CHROMATIC_NUM = (CHROMATIC_NUM + 2) % 360;
        super.tick(stack, level);
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
        Color newColor = new Color(Color.HSBtoRGB(CHROMATIC_NUM / 360f, 0.8f,0.8f));
        return Component.translatable("tooltip.gelato_galore.rainbow_sprinkles", new Object[0]).withStyle(Style.EMPTY.withColor(newColor.getRGB()));
    }

    @Override
    public List<MobEffectInstance> editAppliedEffects(List<MobEffectInstance> effectInstances) {
        List<MobEffectInstance> newEffects = new ArrayList<>();
        for (MobEffectInstance instance : effectInstances) {
            newEffects.add(new MobEffectInstance(instance.getEffect(), instance.getDuration(), instance.getAmplifier() + 1, instance.isAmbient(), instance.isVisible(), instance.showIcon()));
        }
        return super.editAppliedEffects(newEffects);
    }
}
