package com.itayfeder.gelato_galore.init.villager;

import com.itayfeder.gelato_galore.GelatoGalore;
import com.itayfeder.gelato_galore.init.BlockInit;
import com.itayfeder.gelato_galore.mixin.PoiTypeMixin;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class PoiTypeInit {
    public static final DeferredRegister<PoiType> POI_TYPES = DeferredRegister.create(ForgeRegistries.POI_TYPES, GelatoGalore.MODID);

    public static final RegistryObject<PoiType> CONFECTIONER_POI = POI_TYPES.register("confectioner", () -> new PoiType(PoiTypeMixin.invokeGetBlockStates(BlockInit.ICE_CREAM_CAULDRON.get()), 1, 1));

}
