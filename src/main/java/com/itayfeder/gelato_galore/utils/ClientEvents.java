package com.itayfeder.gelato_galore.utils;

import com.itayfeder.gelato_galore.GelatoGalore;
import com.itayfeder.gelato_galore.client.renderers.IceCreamCauldronRenderer;
import com.itayfeder.gelato_galore.init.BlockEntityTypeInit;
import com.itayfeder.gelato_galore.init.BlockInit;
import com.itayfeder.gelato_galore.init.ItemInit;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = GelatoGalore.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientEvents {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(BlockInit.VANILLA_VINE.get(), RenderType.translucent());
    }

    @SubscribeEvent
    public static void onClientSetup(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(BlockEntityTypeInit.ICE_CREAM_CAULDRON.get(), IceCreamCauldronRenderer::new);
    }

    @SubscribeEvent
    public static void onLayerRenderer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(IceCreamCauldronRenderer.LAYER_LOCATION, () -> IceCreamCauldronRenderer.createBodyLayer());

    }
}
