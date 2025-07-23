package net.ntrdeal.ntrdeals_items.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.gen.GenerationStep;
import net.ntrdeal.ntrdeals_items.world.ModPlacedFeatures;

public class ModMineralGeneration {
    public static void generateMinerals(){
        BiomeModifications.addFeature(
                BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.LOCAL_MODIFICATIONS,
                ModPlacedFeatures.LUNARITE_METEOR_PLACED_KEY);
        BiomeModifications.addFeature(
                BiomeSelectors.foundInTheNether(),
                GenerationStep.Feature.LOCAL_MODIFICATIONS,
                ModPlacedFeatures.COSMOLITE_METEOR_PLACED_KEY);
    }
}
