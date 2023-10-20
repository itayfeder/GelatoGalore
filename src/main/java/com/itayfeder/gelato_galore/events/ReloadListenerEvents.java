package com.itayfeder.gelato_galore.events;

import com.itayfeder.gelato_galore.GelatoGalore;
import com.itayfeder.gelato_galore.networking.SyncFlavorDataMessage;
import com.itayfeder.gelato_galore.reload.FlavorDataReloadListener;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.OnDatapackSyncEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = GelatoGalore.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ReloadListenerEvents {
    @SubscribeEvent
    public static void onReloadListenerEvent(AddReloadListenerEvent event) {
        event.addListener(FlavorDataReloadListener.INSTANCE);
    }

    @SubscribeEvent
    public static void onDatapackSyncEvent(OnDatapackSyncEvent event) {
        if (event.getPlayer() != null)
            GelatoGalore.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> event.getPlayer()), new SyncFlavorDataMessage(FlavorDataReloadListener.FLAVOR_MAP));
        else
            GelatoGalore.PACKET_HANDLER.send(PacketDistributor.ALL.noArg(), new SyncFlavorDataMessage(FlavorDataReloadListener.FLAVOR_MAP));

    }
}