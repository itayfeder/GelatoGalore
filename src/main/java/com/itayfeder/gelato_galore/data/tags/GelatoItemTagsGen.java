package com.itayfeder.gelato_galore.data.tags;

import com.itayfeder.gelato_galore.GelatoGalore;
import com.itayfeder.gelato_galore.GelatoGaloreTags;
import com.itayfeder.gelato_galore.init.ItemInit;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;

public class GelatoItemTagsGen extends ItemTagsProvider {
    public GelatoItemTagsGen(DataGenerator p_126530_, BlockTagsProvider p_126531_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_126530_, p_126531_, GelatoGalore.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        super.addTags();
        this.tag(GelatoGaloreTags.ItemTags.CHOCOLATE_INGREDIENT).add(Items.COCOA_BEANS);
        this.tag(GelatoGaloreTags.ItemTags.SWEET_BERRY_INGREDIENT).add(Items.SWEET_BERRIES);
        this.tag(GelatoGaloreTags.ItemTags.HONEY_INGREDIENT).add(Items.HONEY_BOTTLE);
        this.tag(GelatoGaloreTags.ItemTags.CARAMEL_INGREDIENT).add(ItemInit.CARAMEL.get());
        this.tag(GelatoGaloreTags.ItemTags.VANILLA_INGREDIENT).add(ItemInit.DRIED_VANILLA_PODS.get())
                .addOptional(new ResourceLocation("neapolitan", "dried_vanilla_pods"));
        this.tag(GelatoGaloreTags.ItemTags.MELON_INGREDIENT).add(Items.MELON_SLICE);

        this.tag(GelatoGaloreTags.ItemTags.FORGE_MILK).add(Items.MILK_BUCKET);

    }
}
