package com.itayfeder.gelato_galore.reload;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.tags.ITag;

import java.awt.*;

public class FlavorData {
    public static int CURRENT_MAX_ID = 0;

    public ResourceLocation id;
    public String name;
    public int color;
    public FlavorEffect effect;
    public int patternId;
    public int patternColor;
    public ResourceLocation flavorTag;

    public FlavorData(ResourceLocation id, String name, int color, FlavorEffect instance, int patternId, int patternColor, ResourceLocation flavorTag) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.effect = instance;
        this.patternId = patternId;
        this.patternColor = patternColor;
        this.flavorTag = flavorTag;
    }

    public FlavorData(ResourceLocation id, String name, int color, ResourceLocation location, int duration, int amplifier, int patternId, int patternColor, ResourceLocation flavorTag) {
        this.id = id;
        this.name = name;
        this.color = color;
        MobEffect effect = ForgeRegistries.MOB_EFFECTS.getValue(location);
        if (effect != null)
            this.effect = new FlavorData.FlavorEffect(effect, duration, amplifier);
        this.patternId = patternId;
        this.patternColor = patternColor;
        this.flavorTag = flavorTag;
    }

    public Color getAsColor() {
        String hexColor = String.format("#%06X", (0xFFFFFF & this.color));
        Color color = Color.decode(hexColor);
        return color;
    }

    public boolean isFlavorIngredient(Item item) {
        return getFlavorTag().contains(item);
    }

    public ITag<Item> getFlavorTag() {
        return ForgeRegistries.ITEMS.tags().getTag(ForgeRegistries.ITEMS.tags().createTagKey(this.flavorTag));
    }

    public static FlavorData deserialize(JsonObject json, ResourceLocation origin) {
        if (!json.isJsonObject())
            throw new JsonParseException("FlavorData must be a JSON Object");
        JsonObject jsonObject = json.getAsJsonObject();

        String name = GsonHelper.getAsString(jsonObject, "name", null);
        if (name == null)
            throw new JsonParseException("name is not valid");

        int color = GsonHelper.getAsInt(jsonObject, "color", -1);
        if (color == -1)
            throw new JsonParseException("color is not valid");

        ResourceLocation flavorTag = ResourceLocation.tryParse(GsonHelper.getAsString(jsonObject.getAsJsonObject(), "flavorIngredientTag", origin.toString()));

        JsonElement effectElement = json.get("effectInstance");
        FlavorEffect instance = null;
        if (effectElement != null) {
            ResourceLocation location = ResourceLocation.tryParse(GsonHelper.getAsString(effectElement.getAsJsonObject(), "effect", null));
            if (location == null)
                throw new JsonParseException("effect is not valid");

            int duration = GsonHelper.getAsInt(effectElement.getAsJsonObject(), "duration", 900);
            int amplifier = GsonHelper.getAsInt(effectElement.getAsJsonObject(), "amplifier", 0);

            MobEffect effect = ForgeRegistries.MOB_EFFECTS.getValue(location);
            if (effect != null)
                instance = new FlavorEffect(effect, duration, amplifier);
        }

        int patternId = GsonHelper.getAsInt(jsonObject, "patternId", 0);
        int patternColor = GsonHelper.getAsInt(jsonObject, "patternColor", color);


        return new FlavorData(origin, name, color, instance, patternId, patternColor, flavorTag);

    }

    public static class FlavorEffect {
        private final MobEffect effect;
        public int duration;
        private int amplifier;

        public FlavorEffect(MobEffect p_19513_) {
            this(p_19513_, 0, 0);
        }

        public FlavorEffect(MobEffect p_19515_, int p_19516_) {
            this(p_19515_, p_19516_, 0);
        }

        public FlavorEffect(MobEffect p_216887_, int p_216888_, int p_216889_) {
            this.effect = p_216887_;
            this.duration = p_216888_;
            this.amplifier = p_216889_;
        }

        public MobEffectInstance construct() {
            return new MobEffectInstance(this.effect, this.duration, this.amplifier);
        }

        public MobEffectInstance constructModified(int durationMod, int amplifierMod) {
            return new MobEffectInstance(this.effect, this.duration + durationMod, this.amplifier + amplifierMod);
        }
    }
}
