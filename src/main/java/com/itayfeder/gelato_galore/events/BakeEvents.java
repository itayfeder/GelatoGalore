package com.itayfeder.gelato_galore.events;

import com.itayfeder.gelato_galore.GelatoGalore;
import com.itayfeder.gelato_galore.client.renderers.IceCreamItemRenderer;
import com.itayfeder.gelato_galore.items.IceCreamItem;
import com.itayfeder.gelato_galore.toppings.Topping;
import com.itayfeder.gelato_galore.toppings.Toppings;
import com.itayfeder.gelato_galore.utils.ScoopPattern;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = GelatoGalore.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class BakeEvents {
    @SubscribeEvent
    public static void registerColorHandlers(ModelEvent.RegisterAdditional event) {
        for (int scoops = 1; scoops <= IceCreamItem.MAX_SCOOPS; scoops++) {
            event.register(new ResourceLocation(GelatoGalore.MODID, String.format("item/%d_scoop/base", scoops)));
            for (ScoopPattern pattern : ScoopPattern.values()) {
                for (int i = 0; i < scoops; i++) {
                    if (pattern.getId() != 0) event.register(new ResourceLocation(GelatoGalore.MODID,
                            String.format("item/%d_scoop/", scoops) + IceCreamItemRenderer.MODEL_FILES.get(scoops)[i] + pattern.getSerializedName()));
                }
            }

            for (Topping topping : Topping.TOPPINGS.values()) {
                if (!topping.location.equals(Toppings.EMPTY.location)) event.register(new ResourceLocation(GelatoGalore.MODID,
                        String.format("item/%d_scoop/toppings/", scoops) + topping.location.getPath()));

            }
        }
    }
}
