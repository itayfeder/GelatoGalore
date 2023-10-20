package com.itayfeder.gelato_galore.init;

import com.itayfeder.gelato_galore.GelatoGalore;
import com.itayfeder.gelato_galore.blockentities.IceCreamCauldronBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntityTypeInit {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES,
            GelatoGalore.MODID);

    public static final RegistryObject<BlockEntityType<IceCreamCauldronBlockEntity>> ICE_CREAM_CAULDRON = BLOCK_ENTITY_TYPES.register("ice_cream_cauldron",
            () -> BlockEntityType.Builder.of(IceCreamCauldronBlockEntity::new, BlockInit.ICE_CREAM_CAULDRON.get()).build(null));
}
