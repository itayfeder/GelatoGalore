package com.itayfeder.gelato_galore.init;

import com.itayfeder.gelato_galore.GelatoGalore;
import com.itayfeder.gelato_galore.blocks.IceCreamCauldronBlock;
import com.itayfeder.gelato_galore.blocks.VanillaVineBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockInit {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, GelatoGalore.MODID);

    public static final RegistryObject<Block> ICE_CREAM_CAULDRON = BLOCKS.register("ice_cream_cauldron", () -> new IceCreamCauldronBlock(BlockBehaviour.Properties.copy(Blocks.CAULDRON)));

    public static final RegistryObject<Block> VANILLA_VINE = BLOCKS.register("vanilla_vine", () -> new VanillaVineBlock(BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT).noCollission().randomTicks().strength(0.2F).sound(SoundType.VINE)));

}
