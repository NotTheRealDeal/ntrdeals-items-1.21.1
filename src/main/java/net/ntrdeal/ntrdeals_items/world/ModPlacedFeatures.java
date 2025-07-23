package net.ntrdeal.ntrdeals_items.world;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.placementmodifier.*;
import net.ntrdeal.ntrdeals_items.NTRDealsItems;

import java.util.List;

public class ModPlacedFeatures {

    public static final RegistryKey<PlacedFeature> LUNARITE_METEOR_PLACED_KEY = registerKey("lunarite_meteor_placed");
    public static final RegistryKey<PlacedFeature> COSMOLITE_METEOR_PLACED_KEY = registerKey("cosmolite_meteor_placed");

    public static void bootstrap(Registerable<PlacedFeature> context){
        var configuredFeatures = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        register(context, LUNARITE_METEOR_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.LUNARITE_METEOR_KEY),
                RarityFilterPlacementModifier.of(85),
                SquarePlacementModifier.of(),
                HeightRangePlacementModifier.uniform(YOffset.aboveBottom(64), YOffset.aboveBottom(94)));

        register(context, COSMOLITE_METEOR_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.COSMOLITE_METEOR_KEY),
                RarityFilterPlacementModifier.of(45),
                SquarePlacementModifier.of(),
                BiomePlacementModifier.of(),
                HeightRangePlacementModifier.uniform(YOffset.aboveBottom(14), YOffset.aboveBottom(44)));
    }

    public static RegistryKey<PlacedFeature> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(NTRDealsItems.MOD_ID, name));
    }

    private static void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key, RegistryEntry<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers){
        context.register(key, new PlacedFeature(configuration, modifiers));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key,
                                                                                   RegistryEntry<ConfiguredFeature<?, ?>> configuration,
                                                                                   PlacementModifier... modifiers){
        register(context, key, configuration, List.of(modifiers));
    }
}
