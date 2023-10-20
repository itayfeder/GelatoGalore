package com.itayfeder.gelato_galore.network;

import com.itayfeder.gelato_galore.blockentities.IceCreamCauldronBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkEvent;

import java.util.UUID;
import java.util.function.Supplier;

public class SyncCauldronMessage {
    private final ResourceLocation flavor;
    private final int scoops;
    private final BlockPos pos;

    public SyncCauldronMessage(FriendlyByteBuf buf) {
        flavor = buf.readResourceLocation();
        scoops = buf.readInt();
        pos = buf.readBlockPos();
    }
    public SyncCauldronMessage(ResourceLocation id, int duration, BlockPos pos) {
        this.flavor = id;
        this.scoops = duration;
        this.pos = pos;
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeResourceLocation(flavor);
        buf.writeInt(scoops);
        buf.writeBlockPos(pos);
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        NetworkEvent.Context context = ctx.get();
        if (Minecraft.getInstance().level.getBlockEntity(pos) instanceof IceCreamCauldronBlockEntity be) {
            be.setScoopsLeft(scoops);
            be.setFlavor(flavor);
        }
        return true;
    }

}
