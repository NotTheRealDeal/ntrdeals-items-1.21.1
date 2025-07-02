package net.ntrdeal.ntrdeals_items.world;

import net.minecraft.block.Blocks;
import net.minecraft.registry.*;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.ntrdeal.ntrdeals_items.NTRDealsItems;
import net.ntrdeal.ntrdeals_items.block.ModBlocks;
import net.ntrdeal.ntrdeals_items.world.feature.MeteorFeatureConfig;
import net.ntrdeal.ntrdeals_items.world.feature.ModFeature;

public class ModConfiguredFeatures {

    public static final RegistryKey<ConfiguredFeature<?, ?>> LUNARITE_METEOR_KEY = registerKey("lunarite_meteor");
    public static final RegistryKey<ConfiguredFeature<?, ?>> COSMOLITE_METEOR_KEY = registerKey("cosmolite_meteor");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        register(context, LUNARITE_METEOR_KEY, ModFeature.METEOR, new MeteorFeatureConfig(4, 0.135f, Blocks.END_STONE.getDefaultState(), ModBlocks.LUNARITE_ORE.getDefaultState()));
        register(context, COSMOLITE_METEOR_KEY, ModFeature.METEOR, new MeteorFeatureConfig(2, 0.75f, Blocks.OBSIDIAN.getDefaultState(), ModBlocks.COSMOLITE_ORE.getDefaultState()));
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name){
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(NTRDealsItems.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration){
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }

    private static <C extends FeatureConfig, F extends Feature<C>> F registerFeature(String name, F feature) {
        return Registry.register(Registries.FEATURE, Identifier.of(NTRDealsItems.MOD_ID, name), feature);
    }
}
