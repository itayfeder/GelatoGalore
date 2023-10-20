package com.itayfeder.gelato_galore.utils;

import com.itayfeder.gelato_galore.blockentities.IceCreamCauldronBlockEntity;
import com.itayfeder.gelato_galore.blocks.IceCreamCauldronBlock;
import com.itayfeder.gelato_galore.init.BlockInit;
import com.itayfeder.gelato_galore.init.ItemInit;
import com.itayfeder.gelato_galore.items.IceCreamItem;
import com.itayfeder.gelato_galore.items.SyrupItem;
import com.itayfeder.gelato_galore.reload.FlavorData;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.gameevent.GameEvent;

public class CauldronInter {
    public static void register() {
        IceCreamCauldronBlock.ICE_CREAM.put(ItemInit.ICE_CREAM_CONE.get(), BUILD_ICECREAM_ONE);
        IceCreamCauldronBlock.ICE_CREAM.put(ItemInit.ICE_CREAM_ONE.get(), BUILD_ICECREAM_TWO);
        IceCreamCauldronBlock.ICE_CREAM.put(ItemInit.ICE_CREAM_TWO.get(), BUILD_ICECREAM_THREE);

        CauldronInteraction.POWDER_SNOW.put(ItemInit.SYRUP.get(), FILL_CAULDRON);
    }

    static CauldronInteraction BUILD_ICECREAM_ONE = (p_175629_, p_175630_, p_175631_, p_175632_, p_175633_, p_175634_) -> {
        Item item = p_175634_.getItem();
        if (!(p_175634_.is(ItemInit.ICE_CREAM_CONE.get()))) {
            return InteractionResult.PASS;
        } else {
            if (!p_175630_.isClientSide) {
                if (p_175630_.getBlockEntity(p_175631_) instanceof IceCreamCauldronBlockEntity blockEntity) {
                    FlavorData flavor = blockEntity.getFlavor();
                    if (flavor != null) {
                        ItemStack cone = new ItemStack(ItemInit.ICE_CREAM_ONE.get());
                        ((IceCreamItem)ItemInit.ICE_CREAM_ONE.get()).setFlavor(cone, flavor, 0);
//                        IceCreamItem.setTopping(cone, Toppings.EMPTY);
                        p_175634_.shrink(1);
                        p_175632_.addItem(cone);

                        blockEntity.setScoopsLeft(Mth.clamp(blockEntity.getScoopsLeft()-1, 0, IceCreamCauldronBlockEntity.MAX_SCOOPS));
                        blockEntity.update(p_175630_, p_175629_, p_175631_, p_175632_);
                        blockEntity.setChanged();
                        p_175630_.playSound((Player)null, p_175631_, SoundEvents.BUCKET_FILL_POWDER_SNOW, SoundSource.BLOCKS, 1.0F, 1.0F);
                    }

                }

            }

            return InteractionResult.sidedSuccess(p_175630_.isClientSide);
        }
    };

    static CauldronInteraction BUILD_ICECREAM_TWO = (p_175629_, p_175630_, p_175631_, p_175632_, p_175633_, p_175634_) -> {
        Item item = p_175634_.getItem();
        if (!(p_175634_.is(ItemInit.ICE_CREAM_ONE.get()))) {
            return InteractionResult.PASS;
        } else {
            IceCreamItem iceCream = (IceCreamItem) ItemInit.ICE_CREAM_TWO.get();
            if (!p_175630_.isClientSide) {
                if (p_175630_.getBlockEntity(p_175631_) instanceof IceCreamCauldronBlockEntity blockEntity) {
                    FlavorData flavor = blockEntity.getFlavor();
                    if (flavor != null) {
                        ItemStack cone = new ItemStack(ItemInit.ICE_CREAM_TWO.get());
                        iceCream.setFlavor(cone, iceCream.getFlavor(p_175634_, 0), 0);
                        iceCream.setFlavor(cone, flavor, 1);
//                        IceCreamItem.setTopping(cone, Toppings.EMPTY);
                        p_175634_.shrink(1);
                        p_175632_.addItem(cone);

                        blockEntity.setScoopsLeft(Mth.clamp(blockEntity.getScoopsLeft()-1, 0, IceCreamCauldronBlockEntity.MAX_SCOOPS));
                        blockEntity.update(p_175630_, p_175629_, p_175631_, p_175632_);
                        blockEntity.setChanged();
                        p_175630_.playSound((Player)null, p_175631_, SoundEvents.BUCKET_FILL_POWDER_SNOW, SoundSource.BLOCKS, 1.0F, 1.0F);

                    }

                }

            }

            return InteractionResult.sidedSuccess(p_175630_.isClientSide);
        }
    };

    static CauldronInteraction BUILD_ICECREAM_THREE = (p_175629_, p_175630_, p_175631_, p_175632_, p_175633_, p_175634_) -> {
        Item item = p_175634_.getItem();
        if (!(p_175634_.is(ItemInit.ICE_CREAM_TWO.get()))) {
            return InteractionResult.PASS;
        } else {
            IceCreamItem iceCream = (IceCreamItem) ItemInit.ICE_CREAM_THREE.get();
            if (!p_175630_.isClientSide) {
                if (p_175630_.getBlockEntity(p_175631_) instanceof IceCreamCauldronBlockEntity blockEntity) {
                    FlavorData flavor = blockEntity.getFlavor();
                    if (flavor != null) {
                        ItemStack cone = new ItemStack(ItemInit.ICE_CREAM_THREE.get());
                        iceCream.setFlavor(cone, iceCream.getFlavor(p_175634_, 0), 0);
                        iceCream.setFlavor(cone, iceCream.getFlavor(p_175634_, 1), 1);
                        iceCream.setFlavor(cone, flavor, 2);
//                        IceCreamItem.setTopping(cone, Toppings.EMPTY);
                        p_175634_.shrink(1);
                        p_175632_.addItem(cone);

                        blockEntity.setScoopsLeft(Mth.clamp(blockEntity.getScoopsLeft()-1, 0, IceCreamCauldronBlockEntity.MAX_SCOOPS));
                        blockEntity.update(p_175630_, p_175629_, p_175631_, p_175632_);
                        blockEntity.setChanged();
                        p_175630_.playSound((Player)null, p_175631_, SoundEvents.BUCKET_FILL_POWDER_SNOW, SoundSource.BLOCKS, 1.0F, 1.0F);

                    }

                }

            }

            return InteractionResult.sidedSuccess(p_175630_.isClientSide);
        }
    };

    static CauldronInteraction FILL_CAULDRON = (p_175629_, p_175630_, p_175631_, p_175632_, p_175633_, p_175634_) -> {
        if (!p_175630_.isClientSide) {
            Item item = p_175634_.getItem();
            if (!((Player)p_175632_).getAbilities().instabuild) {
                p_175634_.shrink(1);
            }
            p_175632_.awardStat(Stats.FILL_CAULDRON);
            p_175632_.awardStat(Stats.ITEM_USED.get(item));
            p_175630_.setBlockAndUpdate(p_175631_, BlockInit.ICE_CREAM_CAULDRON.get().defaultBlockState());
            p_175630_.playSound((Player)null, p_175631_, SoundEvents.BUCKET_FILL_POWDER_SNOW, SoundSource.BLOCKS, 1.0F, 1.0F);
            p_175630_.gameEvent((Entity) null, GameEvent.FLUID_PLACE, p_175631_);


            if (p_175630_.getBlockEntity(p_175631_) instanceof IceCreamCauldronBlockEntity blockEntity) {
                blockEntity.setScoopsLeft(IceCreamCauldronBlockEntity.MAX_SCOOPS);
                blockEntity.setFlavor(SyrupItem.getFilledFlavor(p_175634_));
                blockEntity.update(p_175630_, p_175629_, p_175631_, p_175632_);
                blockEntity.setChanged();
            }
        }

        return InteractionResult.sidedSuccess(p_175630_.isClientSide);
    };
}
