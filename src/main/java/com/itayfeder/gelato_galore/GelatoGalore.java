package com.itayfeder.gelato_galore;

import com.itayfeder.gelato_galore.data.GelatoAdvancementGen;
import com.itayfeder.gelato_galore.data.GelatoRecipesGen;
import com.itayfeder.gelato_galore.data.loot_tables.GelatoLootTableGen;
import com.itayfeder.gelato_galore.data.tags.GelatoBlockTagsGen;
import com.itayfeder.gelato_galore.data.tags.GelatoItemTagsGen;
import com.itayfeder.gelato_galore.data.tags.GelatoPoiTypeTagsGen;
import com.itayfeder.gelato_galore.init.BlockEntityTypeInit;
import com.itayfeder.gelato_galore.init.BlockInit;
import com.itayfeder.gelato_galore.init.ItemInit;
import com.itayfeder.gelato_galore.init.RecipeInit;
import com.itayfeder.gelato_galore.init.villager.PoiTypeInit;
import com.itayfeder.gelato_galore.init.villager.ProfessionInit;
import com.itayfeder.gelato_galore.mixin.PoiTypeMixin;
import com.itayfeder.gelato_galore.network.SyncCauldronMessage;
import com.itayfeder.gelato_galore.toppings.Toppings;
import com.itayfeder.gelato_galore.utils.CauldronInter;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Mod(GelatoGalore.MODID)
public class GelatoGalore
{
    public static final String MODID = "gelato_galore";
    private static final Logger LOGGER = LogUtils.getLogger();

    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel PACKET_HANDLER = NetworkRegistry.newSimpleChannel(new ResourceLocation(MODID, MODID), () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);
    private static int messageID = 0;

    public GelatoGalore()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);

        ItemInit.ITEMS.register(modEventBus);
        RecipeInit.RECIPE_SERIALIZERS.register(modEventBus);
        BlockEntityTypeInit.BLOCK_ENTITY_TYPES.register(modEventBus);
        BlockInit.BLOCKS.register(modEventBus);

        ProfessionInit.VILLAGER_PROFESSIONS.register(modEventBus);
        PoiTypeInit.POI_TYPES.register(modEventBus);

        GelatoGaloreConfig.register();

        Toppings.Init();

        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::serverSetup);
        modEventBus.addListener(this::dataSetup);
    }

    private void serverSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ProfessionInit.fillTradeData();
            PoiTypeMixin.invokeGetBlockStates(BlockInit.ICE_CREAM_CAULDRON.get()).forEach((state) -> PoiTypeMixin.getTypeByState().put(state, Registry.POINT_OF_INTEREST_TYPE.getHolder(ResourceKey.create(Registry.POINT_OF_INTEREST_TYPE_REGISTRY, new ResourceLocation(MODID, "ice_cream_cauldron"))).get()));
        });
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        GelatoGalore.addMessage(SyncCauldronMessage.class, SyncCauldronMessage::toBytes, SyncCauldronMessage::new, SyncCauldronMessage::handle);

        CauldronInter.register();
    }

    private void dataSetup(GatherDataEvent event) {
        DataGenerator dataGenerator = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        boolean includeServer = event.includeServer();

        GelatoBlockTagsGen blockTags = new GelatoBlockTagsGen(dataGenerator, existingFileHelper);
        dataGenerator.addProvider(includeServer, blockTags);
        dataGenerator.addProvider(includeServer, new GelatoItemTagsGen(dataGenerator, blockTags, existingFileHelper));
        dataGenerator.addProvider(includeServer, new GelatoPoiTypeTagsGen(dataGenerator, existingFileHelper));

        dataGenerator.addProvider(includeServer, new GelatoRecipesGen(dataGenerator));
        dataGenerator.addProvider(includeServer, new GelatoLootTableGen(dataGenerator));

        dataGenerator.addProvider(includeServer, new GelatoAdvancementGen(dataGenerator, existingFileHelper));

    }

    public static <T> void addMessage(Class<T> messageType, BiConsumer<T, FriendlyByteBuf> encoder, Function<FriendlyByteBuf, T> decoder,
                                      BiConsumer<T, Supplier<NetworkEvent.Context>> messageConsumer) {
        PACKET_HANDLER.registerMessage(messageID, messageType, encoder, decoder, messageConsumer);
        System.out.println("REGISTERED MESSAGE: " + messageType.getName());
        messageID++;
    }

}
