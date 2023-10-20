package com.itayfeder.gelato_galore.client.renderers;

import com.itayfeder.gelato_galore.GelatoGalore;
import com.itayfeder.gelato_galore.blockentities.IceCreamCauldronBlockEntity;
import com.itayfeder.gelato_galore.blocks.IceCreamCauldronBlock;
import com.itayfeder.gelato_galore.reload.FlavorData;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.awt.*;

public class IceCreamCauldronRenderer<T extends IceCreamCauldronBlockEntity> implements BlockEntityRenderer<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(GelatoGalore.MODID, "ice_cream_cauldron"), "main");

    private final ModelPart layer;

    public IceCreamCauldronRenderer(BlockEntityRendererProvider.Context p_173607_) {
        ModelPart modelpart = p_173607_.bakeLayer(LAYER_LOCATION);
        this.layer = modelpart.getChild("layer");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition layer = partdefinition.addOrReplaceChild("layer", CubeListBuilder.create().texOffs(-10, 2).addBox(2.0F, 0.0F, 2.0F, 12.0F, 0.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 16.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 32, 16);
    }

    @Override
    public void render(T p_112363_, float p_112364_, PoseStack p_112365_, MultiBufferSource p_112366_, int p_112367_, int p_112368_) {
        BlockState blockstate = p_112363_.getBlockState();
        Block block = blockstate.getBlock();
        if (block instanceof IceCreamCauldronBlock) {
            int scoops = p_112363_.getScoopsLeft();
            FlavorData flavor = p_112363_.getFlavor();
            String hexColor = String.format("#%06X", (0xFFFFFF & flavor.color));
            Color color = Color.decode(hexColor);
            p_112365_.pushPose();
            this.layer.setPos(this.layer.x, 14F, this.layer.z);

            for (int i = scoops; i < IceCreamCauldronBlockEntity.MAX_SCOOPS; i++) {
                this.layer.setPos(this.layer.x, this.layer.y-2f, this.layer.z);
            }

            Material material = new Material(TextureAtlas.LOCATION_BLOCKS, new ResourceLocation(GelatoGalore.MODID,"block/" + "ice_cream_block"));
            VertexConsumer vertexconsumer = material.buffer(p_112366_, RenderType::entitySolid);
            this.layer.render(p_112365_, vertexconsumer, p_112367_, p_112368_, color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f, 1f );

            p_112365_.popPose();
        }
    }
}
