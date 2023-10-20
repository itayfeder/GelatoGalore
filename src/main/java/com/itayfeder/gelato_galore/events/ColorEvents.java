package com.itayfeder.gelato_galore.events;

import com.itayfeder.gelato_galore.GelatoGalore;
import com.itayfeder.gelato_galore.init.ItemInit;
import com.itayfeder.gelato_galore.items.IceCreamItem;
import com.itayfeder.gelato_galore.items.SyrupItem;
import com.itayfeder.gelato_galore.reload.FlavorData;
import net.minecraft.client.color.item.ItemColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = GelatoGalore.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ColorEvents {
    @SubscribeEvent
    public static void registerColorHandlers(RegisterColorHandlersEvent.Item event) {
        registerItemColorHandlers(event.getItemColors());
    }

    private static void registerItemColorHandlers(final ItemColors itemColors) {
        itemColors.register((stack, color) -> {
            if (stack.getItem() instanceof IceCreamItem item) {
                if (color <= 0) return -1;
                else {
                    FlavorData flavor = item.getFlavor(stack, color-1);
                    if (flavor == null) return -1;
                    else return flavor.color;
                }
            } else return -1;
        }, ItemInit.ICE_CREAM_ONE.get(), ItemInit.ICE_CREAM_TWO.get(), ItemInit.ICE_CREAM_THREE.get());

        itemColors.register((stack, color) -> {
            if (stack.getItem() instanceof SyrupItem item) {
                FlavorData flavor = SyrupItem.getFilledFlavor(stack);
                if (flavor == null) return -1;
                return color <= 0 ? -1 : flavor.color;
            } else return -1;
        }, ItemInit.SYRUP.get());
    }
}
