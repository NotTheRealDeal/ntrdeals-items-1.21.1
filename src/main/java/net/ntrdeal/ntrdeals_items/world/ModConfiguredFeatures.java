package net.ntrdeal.ntrdeals_items.world;

import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.ntrdeal.ntrdeals_items.NTRDealsItems;
import net.ntrdeal.ntrdeals_items.block.ModBlockTags;
import net.ntrdeal.ntrdeals_items.block.ModBlocks;

import java.util.List;

public class ModConfiguredFeatures {

    public static final RegistryKey<ConfiguredFeature<?, ?>> LUNARITE_METEOR_KEY = registerKey("lunarite_meteor");
    public static final RegistryKey<ConfiguredFeature<?, ?>> COSMOLITE_METEOR_KEY = registerKey("cosmolite_meteor");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        register(context, LUNARITE_METEOR_KEY, Feature.GEODE, new GeodeFeatureConfig(
                new GeodeLayerConfig(
                        BlockStateProvider.of(Blocks.AIR),
                        BlockStateProvider.of(Blocks.END_STONE),
                        BlockStateProvider.of(ModBlocks.LUNARITE_ORE),
                        BlockStateProvider.of(Blocks.END_STONE_BRICKS),
                        BlockStateProvider.of(Blocks.PURPUR_BLOCK),
                        List.of(ModBlocks.DRIED_CHORUS_FLOWER.getDefaultState()),
                        ModBlockTags.GEODE_CANNOT_REPLACE,
                        BlockTags.GEODE_INVALID_BLOCKS),
                new GeodeLayerThicknessConfig(1.7, 2.2, 3.2, 4.2),
                new GeodeCrackConfig(0.95, 2.0, 2),
                0.05,
                0.0355,
                false,
                UniformIntProvider.create(4, 6),
                UniformIntProvider.create(3, 4),
                UniformIntProvider.create(1, 2),
                -16,
                16,
                0.05,
                1
        ));
        register(context, COSMOLITE_METEOR_KEY, Feature.GEODE, new GeodeFeatureConfig(
                new GeodeLayerConfig(
                        BlockStateProvider.of(Blocks.LAVA),
                        BlockStateProvider.of(Blocks.BLACKSTONE),
                        BlockStateProvider.of(ModBlocks.COSMOLITE_ORE),
                        BlockStateProvider.of(Blocks.MAGMA_BLOCK),
                        BlockStateProvider.of(Blocks.SMOOTH_BASALT),
                        List.of(Blocks.LAVA.getDefaultState()),
                        ModBlockTags.GEODE_CANNOT_REPLACE,
                        BlockTags.GEODE_INVALID_BLOCKS),
                new GeodeLayerThicknessConfig(1.7, 2.2, 3.2, 4.2),
                new GeodeCrackConfig(0, 2.0, 2),
                0.35,
                0.0115,
                false,
                UniformIntProvider.create(4, 6),
                UniformIntProvider.create(3, 4),
                UniformIntProvider.create(1, 2),
                -16,
                16,
                0.05,
                1
        ));
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name){
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(NTRDealsItems.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration){
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
