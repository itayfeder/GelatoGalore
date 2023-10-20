package com.itayfeder.gelato_galore.reload;


import com.google.common.collect.HashBiMap;
import com.google.gson.*;
import com.itayfeder.gelato_galore.networking.SyncFlavorDataMessage;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.util.thread.EffectiveSide;
import net.minecraftforge.server.ServerLifecycleHooks;

import java.util.Map;

public class FlavorDataReloadListener extends SimpleJsonResourceReloadListener {
    public static final FlavorDataReloadListener INSTANCE;
    public static final Gson GSON = new GsonBuilder().create();
    public static final HashBiMap<ResourceLocation, FlavorData> FLAVOR_MAP = HashBiMap.create();

    public FlavorDataReloadListener() {
        super(GSON, "flavors");
    }

    static {
        INSTANCE = new FlavorDataReloadListener();
    }

    public static Map<ResourceLocation, FlavorData> getSidedMap() {
        if(EffectiveSide.get().isServer()) {
            return FLAVOR_MAP;
        }
        return SyncFlavorDataMessage.CLIENT_FLAVORS_MAP;
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> objectIn, ResourceManager p_10794_, ProfilerFiller p_10795_) {
        FLAVOR_MAP.clear();
        FlavorData.CURRENT_MAX_ID = 0;
        for (Map.Entry<ResourceLocation, JsonElement> entry : objectIn.entrySet()) {
            ResourceLocation name = entry.getKey();
            String[] split = name.getPath().split("/");
            if (split[split.length - 1].startsWith("_"))
                continue;
            JsonObject json = entry.getValue().getAsJsonObject();
            try {
                FlavorData recipe = FlavorData.deserialize(json, name);
                FLAVOR_MAP.put(name, recipe);
            } catch (IllegalArgumentException | JsonParseException e) {
                System.out.println(String.format("I got an error!!!: ", e));
            }
        }

        System.out.println(String.format("%s Flavors loaded successfully !", FLAVOR_MAP.size()));
        for (Map.Entry<ResourceLocation, FlavorData> entry : FLAVOR_MAP.entrySet()) {
            System.out.println(String.format(entry.getKey() + " ==> " + entry.getValue()));

        }
    }
}
