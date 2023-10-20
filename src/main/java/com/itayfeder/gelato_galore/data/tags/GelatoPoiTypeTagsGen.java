package com.itayfeder.gelato_galore.data.tags;

import com.itayfeder.gelato_galore.GelatoGalore;
import com.itayfeder.gelato_galore.GelatoGaloreTags;
import com.itayfeder.gelato_galore.init.ItemInit;
import com.itayfeder.gelato_galore.init.villager.PoiTypeInit;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.PoiTypeTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.PoiTypeTags;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;

public class GelatoPoiTypeTagsGen extends PoiTypeTagsProvider {
    public GelatoPoiTypeTagsGen(DataGenerator p_126530_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_126530_, GelatoGalore.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        super.addTags();
        this.tag(PoiTypeTags.ACQUIRABLE_JOB_SITE).add(PoiTypeInit.CONFECTIONER_POI.get());

    }
}
