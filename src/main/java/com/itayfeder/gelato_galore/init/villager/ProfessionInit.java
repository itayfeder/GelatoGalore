package com.itayfeder.gelato_galore.init.villager;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.itayfeder.gelato_galore.GelatoGalore;
import com.itayfeder.gelato_galore.init.ItemInit;
import com.itayfeder.gelato_galore.items.IceCreamItem;
import com.itayfeder.gelato_galore.reload.FlavorData;
import com.itayfeder.gelato_galore.reload.FlavorDataReloadListener;
import com.itayfeder.gelato_galore.toppings.Toppings;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.core.Registry;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.stream.Collectors;

public class ProfessionInit {
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS = DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS,
            GelatoGalore.MODID);

    public static final RegistryObject<VillagerProfession> CONFECTIONER = VILLAGER_PROFESSIONS.register("confectioner", () ->
            new VillagerProfession("confectioner", holder -> holder.value().equals(PoiTypeInit.CONFECTIONER_POI.get()), holder -> holder.value().equals(PoiTypeInit.CONFECTIONER_POI.get()), ImmutableSet.of(), ImmutableSet.of(), SoundEvents.VILLAGER_WORK_FARMER));

    public static void fillTradeData() {
        VillagerTrades.ItemListing[] Level1 = new VillagerTrades.ItemListing[]{
                new VillagerTrades.EmeraldForItems(Items.COCOA_BEANS,17,16,2),
                new VillagerTrades.EmeraldForItems(Items.HONEYCOMB,15,16,2),
                new VillagerTrades.EmeraldForItems(Items.EGG,16,16,2),
                new VillagerTrades.EmeraldForItems(Items.MELON_SLICE,14,16,2),
                new VillagerTrades.EmeraldForItems(Items.SUGAR,24,16,2)
        };
        VillagerTrades.ItemListing[] Level2 = new VillagerTrades.ItemListing[]{
                new VillagerTrades.ItemsForEmeralds(Items.COOKIE, 8, 4, 16, 3),
                new VillagerTrades.ItemsForEmeralds(ItemInit.ICE_CREAM_CONE.get(), 16, 6, 16, 3)
        };
        VillagerTrades.ItemListing[] Level3 = new VillagerTrades.ItemListing[]{
                new VillagerTrades.ItemsForEmeralds(ItemInit.CARAMEL.get(),21,4,12),
                new VillagerTrades.ItemsForEmeralds(ItemInit.VANILLA_PODS.get(),18,6,12)
                //new VillagerTrades.EmeraldForItems(Items.SUGAR,24,16,12)
        };
        VillagerTrades.ItemListing[] Level4 = new VillagerTrades.ItemListing[]{
                new VillagerTrades.ItemsForEmeralds(Items.CAKE, 25, 1, 16, 17),
                new IceCreamForEmeralds((IceCreamItem) ItemInit.ICE_CREAM_ONE.get(), 17)
        };
        VillagerTrades.ItemListing[] Level5 = new VillagerTrades.ItemListing[]{
                new IceCreamForEmeralds((IceCreamItem) ItemInit.ICE_CREAM_THREE.get(), 22),
                new IceCreamForEmeralds((IceCreamItem) ItemInit.ICE_CREAM_THREE.get(), 22)
        };
        VillagerTrades.TRADES.put(CONFECTIONER.get(),toIntMap(ImmutableMap.of(1,Level1,2,Level2,3,Level3,4,Level4,5,Level5)));
    }

    private static Int2ObjectMap<VillagerTrades.ItemListing[]> toIntMap(ImmutableMap<Integer, VillagerTrades.ItemListing[]> p_221238_0_) {
        return new Int2ObjectOpenHashMap<>(p_221238_0_);
    }

    public static class IceCreamForEmeralds implements VillagerTrades.ItemListing {
        private final int villagerXp;
        private final IceCreamItem itemThing;

        public IceCreamForEmeralds(IceCreamItem itemThing, int p_35683_) {
            this.villagerXp = p_35683_;
            this.itemThing = itemThing;
        }

        public MerchantOffer getOffer(Entity p_219688_, RandomSource p_219689_) {
            List<FlavorData> list = FlavorDataReloadListener.INSTANCE.FLAVOR_MAP.values().stream().toList();
            ItemStack itemstack = new ItemStack(this.itemThing);
            for (int i = 0; i < this.itemThing.scoops; i++) {
                FlavorData enchantment = list.get(p_219689_.nextInt(list.size()));
                this.itemThing.setFlavor(itemstack, enchantment, i);
                IceCreamItem.setTopping(itemstack, Toppings.EMPTY);
            }
            int j = 2 + p_219689_.nextInt(this.itemThing.scoops * 7) + 3 * this.itemThing.scoops;

            return new MerchantOffer(new ItemStack(Items.EMERALD, j), ItemStack.EMPTY, itemstack, 4, this.villagerXp, 0.2F);
        }
    }
}
