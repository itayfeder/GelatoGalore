package com.itayfeder.gelato_galore.client.renderers;

import com.itayfeder.gelato_galore.GelatoGalore;
import com.itayfeder.gelato_galore.init.ItemInit;
import com.itayfeder.gelato_galore.items.IceCreamItem;
import com.itayfeder.gelato_galore.reload.FlavorData;
import com.itayfeder.gelato_galore.toppings.Topping;
import com.itayfeder.gelato_galore.toppings.Toppings;
import com.itayfeder.gelato_galore.utils.ScoopPattern;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IceCreamItemRenderer extends BlockEntityWithoutLevelRenderer {
    public static final Map<Integer, String[]> MODEL_FILES = new HashMap<Integer, String[]>() {{
        put(1, new String[] {""});
        put(2, new String[] {"bottom_", "top_"});
        put(3, new String[] {"right_", "left_", "top_"});
    }};

    public IceCreamItemRenderer() {
        super(null, null);
    }

    @Override
    public void renderByItem(ItemStack p_108830_, ItemTransforms.TransformType transform, PoseStack matrixStack, MultiBufferSource p_108833_, int light, int overlay) {
        if (p_108830_.getItem() instanceof IceCreamItem) {
            ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
            int scoops = ((IceCreamItem) p_108830_.getItem()).scoops;

            matrixStack.pushPose();
            BakedModel model = itemRenderer.getItemModelShaper().getModelManager().getModel(new ResourceLocation(GelatoGalore.MODID, String.format("item/%d_scoop/base", scoops)));
            RenderType rendertype = ItemBlockRenderTypes.getRenderType(p_108830_, true);
            VertexConsumer vertexconsumer = ItemRenderer.getFoilBufferDirect(p_108833_, rendertype, true, p_108830_.hasFoil());
            itemRenderer.renderModelLists(model, p_108830_, light, overlay, matrixStack, vertexconsumer);

            matrixStack.scale(1.001f, 1.001f, 1.002f);
            matrixStack.translate(0, 0, -0.001f);

            for (int i = 0; i < scoops; i++) {
                FlavorData flavor = ((IceCreamItem) p_108830_.getItem()).getFlavor(p_108830_, i);
                if (flavor != null && flavor.patternId != 0) {
                    ScoopPattern pattern = ScoopPattern.byId(flavor.patternId);

                    model = itemRenderer.getItemModelShaper().getModelManager().getModel(new ResourceLocation(GelatoGalore.MODID,
                            String.format("item/%d_scoop/", scoops) + MODEL_FILES.get(scoops)[i] + pattern.getSerializedName()));
                    rendertype = ItemBlockRenderTypes.getRenderType(p_108830_, true);
                    vertexconsumer = ItemRenderer.getFoilBufferDirect(p_108833_, rendertype, true, p_108830_.hasFoil());

                    renderModelLists(model, p_108830_, light, overlay, matrixStack, vertexconsumer, flavor.patternColor);

                }
            }

            Topping topping = IceCreamItem.getTopping(p_108830_);
            if (!topping.location.equals(Toppings.EMPTY.location)) {
                model = itemRenderer.getItemModelShaper().getModelManager().getModel(new ResourceLocation(GelatoGalore.MODID,
                        String.format("item/%d_scoop/toppings/", scoops) + topping.location.getPath()));
                rendertype = ItemBlockRenderTypes.getRenderType(p_108830_, true);
                vertexconsumer = ItemRenderer.getFoilBufferDirect(p_108833_, rendertype, true, p_108830_.hasFoil());

                renderModelLists(model, p_108830_, light, overlay, matrixStack, vertexconsumer, topping.getColor());
            }

            matrixStack.popPose();
        }

    }

    public void renderModelLists(BakedModel p_115190_, ItemStack p_115191_, int p_115192_, int p_115193_, PoseStack p_115194_, VertexConsumer p_115195_, int overlayColor) {
        RandomSource randomsource = RandomSource.create();
        long i = 42L;

        for (Direction direction : Direction.values()) {
            randomsource.setSeed(42L);
            this.renderQuadList(p_115194_, p_115195_, p_115190_.getQuads((BlockState) null, direction, randomsource), p_115191_, p_115192_, p_115193_, overlayColor);
        }

        randomsource.setSeed(42L);
        this.renderQuadList(p_115194_, p_115195_, p_115190_.getQuads((BlockState) null, (Direction) null, randomsource), p_115191_, p_115192_, p_115193_, overlayColor);
    }

    public void renderQuadList(PoseStack p_115163_, VertexConsumer p_115164_, List<BakedQuad> p_115165_, ItemStack p_115166_, int p_115167_, int p_115168_, int overlayColor) {
        boolean flag = !p_115166_.isEmpty();
        PoseStack.Pose posestack$pose = p_115163_.last();

        for (BakedQuad bakedquad : p_115165_) {
            float f = 1;
            float f1 = 1;
            float f2 = 1;
            if (bakedquad.getTintIndex() <= 0) {
                String hexColor = String.format("#%06X", (0xFFFFFF & overlayColor));
                Color color = Color.decode(hexColor);

                f = color.getRed() / 255f;
                f1 = color.getGreen() / 255f;
                f2 = color.getBlue() / 255f;
            }

            p_115164_.putBulkData(posestack$pose, bakedquad, f, f1, f2, 1.0F, p_115167_, p_115168_, true);
        }

    }
}
