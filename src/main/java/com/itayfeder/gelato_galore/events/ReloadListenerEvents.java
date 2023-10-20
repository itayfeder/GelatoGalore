package com.itayfeder.gelato_galore.events;

import com.itayfeder.gelato_galore.GelatoGalore;
import com.itayfeder.gelato_galore.reload.FlavorDataReloadListener;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = GelatoGalore.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ReloadListenerEvents {
    @SubscribeEvent
    public static void onServerAboutToStart(AddReloadListenerEvent event) {
        event.addListener(FlavorDataReloadListener.INSTANCE);
    }

}