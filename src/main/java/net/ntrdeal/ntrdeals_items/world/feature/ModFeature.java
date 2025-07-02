package net.ntrdeal.ntrdeals_items.world.feature;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.Feature;
import net.ntrdeal.ntrdeals_items.NTRDealsItems;

public class ModFeature {

    public static Feature<MeteorFeatureConfig> METEOR = Registry.register(Registries.FEATURE, Identifier.of(NTRDealsItems.MOD_ID, "meteor"), new MeteorFeature(MeteorFeatureConfig.CODEC));

    public static Feature<MeteorFeatureConfig> registerModFeature(){
        NTRDealsItems.LOGGER.info("registering Mod Feature for: "+NTRDealsItems.MOD_ID);
        return METEOR;
    }
}
