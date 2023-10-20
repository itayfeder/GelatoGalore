package com.itayfeder.gelato_galore.blocks;

import com.itayfeder.gelato_galore.blockentities.IceCreamCauldronBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractCauldronBlock;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.Map;

public class IceCreamCauldronBlock extends AbstractCauldronBlock implements EntityBlock {
    public static final Map<Item, CauldronInteraction> ICE_CREAM = CauldronInteraction.newInteractionMap();

    public IceCreamCauldronBlock(BlockBehaviour.Properties p_153498_) {
        super(p_153498_, ICE_CREAM);
    }

    protected double getContentHeight(BlockState p_153500_) {
        return 0.9375D;
    }

    public boolean isFull(BlockState p_153511_) {
        return true;
    }

    public void entityInside(BlockState p_153506_, Level p_153507_, BlockPos p_153508_, Entity p_153509_) {
        if (this.isEntityInsideContent(p_153506_, p_153508_, p_153509_)) {
            //p_153509_.lavaHurt();
        }

    }

    @Override
    public Item asItem() {
        return Items.CAULDRON;
    }

    public int getAnalogOutputSignal(BlockState p_153502_, Level p_153503_, BlockPos p_153504_) {
        BlockEntity entity = p_153503_.getBlockEntity(p_153504_);
        if (entity instanceof IceCreamCauldronBlockEntity iceCauldronBE) {
            return 2 * iceCauldronBE.getScoopsLeft();
        }
        return 0;
    }

    public boolean triggerEvent(BlockState p_49226_, Level p_49227_, BlockPos p_49228_, int p_49229_, int p_49230_) {
        super.triggerEvent(p_49226_, p_49227_, p_49228_, p_49229_, p_49230_);
        BlockEntity blockentity = p_49227_.getBlockEntity(p_49228_);
        return blockentity == null ? false : blockentity.triggerEvent(p_49229_, p_49230_);
    }

    @Nullable
    public MenuProvider getMenuProvider(BlockState p_49234_, Level p_49235_, BlockPos p_49236_) {
        BlockEntity blockentity = p_49235_.getBlockEntity(p_49236_);
        return blockentity instanceof MenuProvider ? (MenuProvider)blockentity : null;
    }


    public BlockEntity newBlockEntity(BlockPos p_153277_, BlockState p_153278_) {
        return new IceCreamCauldronBlockEntity(p_153277_, p_153278_);
    }
}
