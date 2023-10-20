package com.itayfeder.gelato_galore;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

public class GelatoGaloreConfig {

    public static void register() {
        registerServerConfigs();
        registerCommonConfigs();
        registerClientConfigs();
    }

    private static void registerClientConfigs() {
        ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();
        ClientConfig.registerClientConfig(CLIENT_BUILDER);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, CLIENT_BUILDER.build());
    }

    private static void registerCommonConfigs() {
        ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
        CommonConfig.registerCommonConfig(COMMON_BUILDER);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, COMMON_BUILDER.build());
    }

    private static void registerServerConfigs() {
        ForgeConfigSpec.Builder SERVER_BUILDER = new ForgeConfigSpec.Builder();
        ServerConfig.registerServerConfig(SERVER_BUILDER);
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, SERVER_BUILDER.build());
    }

    public static class ClientConfig {
        public static void registerClientConfig(ForgeConfigSpec.Builder CLIENT_BUILDER) {

        }
    }

    public static class CommonConfig {
        public static void registerCommonConfig(ForgeConfigSpec.Builder COMMON_BUILDER) {

        }
    }

    public static class ServerConfig {
        public static ForgeConfigSpec.BooleanValue ICECREAM_EFFECT;

        public static void registerServerConfig(ForgeConfigSpec.Builder SERVER_BUILDER) {
            SERVER_BUILDER.push("items");

            SERVER_BUILDER.push("ice_cream");
            ICECREAM_EFFECT = SERVER_BUILDER
                    .comment("Does eating ice cream apply effects")
                    .define("applyEffect", true);
            SERVER_BUILDER.pop();

            SERVER_BUILDER.pop();

        }
    }
}
