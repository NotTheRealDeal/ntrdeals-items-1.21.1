package net.ntrdeal.ntrdeals_items.world.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.world.gen.feature.FeatureConfig;

public record MeteorFeatureConfig(int radius, float secondaryPercentage, BlockState primaryBlock, BlockState secondaryBlock) implements FeatureConfig {
    public static final Codec<MeteorFeatureConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.fieldOf("radius").forGetter(MeteorFeatureConfig::radius),
            Codec.FLOAT.fieldOf("secondaryPercentage").forGetter(MeteorFeatureConfig::secondaryPercentage),
            BlockState.CODEC.fieldOf("primaryBlock").forGetter(MeteorFeatureConfig::primaryBlock),
            BlockState.CODEC.fieldOf("secondaryBlock").forGetter(MeteorFeatureConfig::secondaryBlock)
    ).apply(instance, MeteorFeatureConfig::new));
}