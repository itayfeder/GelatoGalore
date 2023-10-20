package com.itayfeder.gelato_galore.data.tags;

import com.itayfeder.gelato_galore.GelatoGalore;
import com.itayfeder.gelato_galore.init.BlockInit;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class GelatoBlockTagsGen extends BlockTagsProvider {
    public GelatoBlockTagsGen(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, GelatoGalore.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(BlockInit.ICE_CREAM_CAULDRON.get());
        this.tag(BlockTags.CAULDRONS).add(BlockInit.ICE_CREAM_CAULDRON.get());

        this.tag(BlockTags.MINEABLE_WITH_AXE).add(BlockInit.VANILLA_VINE.get());
        this.tag(BlockTags.FALL_DAMAGE_RESETTING).add(BlockInit.VANILLA_VINE.get());
        this.tag(BlockTags.FALL_DAMAGE_RESETTING).add(BlockInit.VANILLA_VINE.get());
    }
}
