package com.itayfeder.gelato_galore.data;

import com.itayfeder.gelato_galore.data.advancements.GelatoGaloreAdvancements;
import net.minecraft.advancements.Advancement;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.function.Consumer;

public class GelatoAdvancementGen extends AdvancementProvider {
    public GelatoAdvancementGen(DataGenerator generatorIn, ExistingFileHelper fileHelperIn) {
        super(generatorIn, fileHelperIn);
    }

    @Override
    protected void registerAdvancements(Consumer<Advancement> consumer, ExistingFileHelper fileHelper) {
        GelatoGaloreAdvancements.INSTANCE.accept(consumer);
    }
}
