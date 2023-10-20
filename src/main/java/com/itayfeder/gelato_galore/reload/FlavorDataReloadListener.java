package com.itayfeder.gelato_galore.reload;


import com.google.common.collect.HashBiMap;
import com.google.gson.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;

import java.util.Map;

public class FlavorDataReloadListener extends SimpleJsonResourceReloadListener {
    public static final FlavorDataReloadListener INSTANCE;
    public static final Gson GSON = new GsonBuilder().create();
    public final HashBiMap<ResourceLocation, FlavorData> FLAVOR_MAP;

    public FlavorDataReloadListener() {
        super(GSON, "flavors");
        this.FLAVOR_MAP = HashBiMap.create();
    }

    static {
        INSTANCE = new FlavorDataReloadListener();
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
