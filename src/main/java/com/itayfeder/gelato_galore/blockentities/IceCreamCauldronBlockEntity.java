package com.itayfeder.gelato_galore.blockentities;

import com.itayfeder.gelato_galore.GelatoGalore;
import com.itayfeder.gelato_galore.init.BlockEntityTypeInit;
import com.itayfeder.gelato_galore.reload.FlavorData;
import com.itayfeder.gelato_galore.reload.FlavorDataReloadListener;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.network.PacketDistributor;

import javax.annotation.Nullable;

public class IceCreamCauldronBlockEntity extends BlockEntity {
    private FlavorData flavor;
    private int scoopsLeft;
    public static final int MAX_SCOOPS = 5;

    public IceCreamCauldronBlockEntity(BlockPos p_155229_, BlockState p_155230_) {
        super(BlockEntityTypeInit.ICE_CREAM_CAULDRON.get(), p_155229_, p_155230_);
        this.scoopsLeft = MAX_SCOOPS;
        this.flavor = FlavorDataReloadListener.getSidedMap().values().stream().toList().get(0);
    }

    @Override
    public void load(CompoundTag p_155245_) {
        super.load(p_155245_);
        FlavorData flavor = FlavorDataReloadListener.getSidedMap().get(ResourceLocation.tryParse(p_155245_.getString("Flavor")));
        setFlavor(flavor != null ? flavor : FlavorDataReloadListener.getSidedMap().values().stream().toList().get(0));
        setScoopsLeft(p_155245_.getInt("ScoopsLeft"));

    }


    @Override
    protected void saveAdditional(CompoundTag p_187471_) {
        super.saveAdditional(p_187471_);
        p_187471_.putString("Flavor", flavor.id.toString());
        p_187471_.putInt("ScoopsLeft", scoopsLeft);
    }

    protected CompoundTag write(CompoundTag p_187471_) {
        p_187471_.putString("Flavor", flavor.id.toString());
        p_187471_.putInt("ScoopsLeft", scoopsLeft);
        return p_187471_;
    }

    public FlavorData getFlavor() {
        return flavor;
    }

    public void setFlavor(FlavorData flavor) {
        this.flavor = flavor;
    }

    public void setFlavor(ResourceLocation flavorId) {
        this.flavor = FlavorDataReloadListener.getSidedMap().get(flavorId);
    }

    public int getScoopsLeft() {
        return scoopsLeft;
    }

    public void setScoopsLeft(int scoopsLeft) {
        this.scoopsLeft = scoopsLeft;
    }

    public void update(Level level, BlockState state, BlockPos pos, Player player) {
        if (!level.isClientSide) {
           //  GelatoGalore.PACKET_HANDLER.send(PacketDistributor.ALL.noArg(), new SyncCauldronMessage(this.flavor.id, this.scoopsLeft, worldPosition));
            level.sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 3);
            if (scoopsLeft <= 0) {
                player.awardStat(Stats.USE_CAULDRON);
                level.setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());
                level.playSound((Player)null, pos, SoundEvents.BUCKET_EMPTY_POWDER_SNOW, SoundSource.BLOCKS, 1.0F, 1.0F);
                level.gameEvent((Entity) null, GameEvent.FLUID_PICKUP, pos);
            }
        }
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag() {
        return this.write(new CompoundTag());
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        this.load(pkt.getTag());
    }

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        this.load(tag);
    }

}
