package com.itayfeder.gelato_galore.networking;

import com.google.common.collect.HashBiMap;
import com.itayfeder.gelato_galore.blockentities.IceCreamCauldronBlockEntity;
import com.itayfeder.gelato_galore.reload.FlavorData;
import com.mojang.serialization.Codec;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkEvent;

import java.util.*;
import java.util.function.Supplier;

public class SyncFlavorDataMessage {
    public static final Map<ResourceLocation, FlavorData> CLIENT_FLAVORS_MAP = HashBiMap.create();

    private final Map<ResourceLocation, FlavorData> flavors;

    public SyncFlavorDataMessage(FriendlyByteBuf buf) {
        this.flavors = buf.readWithCodec(Codec.unboundedMap(ResourceLocation.CODEC, FlavorData.CODEC));
    }

    public SyncFlavorDataMessage(Map<ResourceLocation, FlavorData> flavors) {
        this.flavors = flavors;
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeWithCodec(Codec.unboundedMap(ResourceLocation.CODEC, FlavorData.CODEC), this.flavors);
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        NetworkEvent.Context context = ctx.get();
        context.enqueueWork(() -> {
            CLIENT_FLAVORS_MAP.clear();
            CLIENT_FLAVORS_MAP.putAll(flavors);
        });

        context.setPacketHandled(true);
        return true;
    }
}
