package net.ntrdeal.ntrdeals_items.world.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.HashSet;
import java.util.Set;

public class MeteorFeature extends Feature<MeteorFeatureConfig> {
    public MeteorFeature(Codec<MeteorFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<MeteorFeatureConfig> context) {
        BlockPos pos = context.getOrigin();
        MeteorFeatureConfig config = context.getConfig();
        int radius = config.radius();
        float secondaryOrePercentage = config.secondaryPercentage();
        BlockState primaryBlock = config.primaryBlock();
        BlockState secondaryBlock = config.secondaryBlock();

        int centerX = pos.getX();
        int centerY = pos.getY();
        int centerZ = pos.getZ();

        Set<BlockPos> hemispherePositions = new HashSet<>();
        for (int x = centerX - radius; x <= centerX + radius; x++) {
            for (int z = centerZ - radius; z <= centerZ + radius; z++) {
                for (int y = centerY-radius; y<=centerY+radius; y++) {
                    double distSq = (x - centerX) * (x - centerX) + (y - centerY) * (y - centerY) + (z - centerZ) * (z - centerZ);
                    if (distSq <= radius * radius) {
                        hemispherePositions.add(new BlockPos(x, y, z));
                    }
                }
            }
        }

        StructureWorldAccess world = context.getWorld();
        for (BlockPos blockPos : hemispherePositions) {
            boolean isSurface = false;
            for (Direction direction : Direction.values()) {
                BlockPos adjacentPos = blockPos.offset(direction);
                if (!hemispherePositions.contains(adjacentPos)) {
                    isSurface = true;
                    break;
                }
            }

            if (isSurface) {
                world.setBlockState(blockPos, primaryBlock, 2);
            } else {
                if (Random.create().nextFloat() <= secondaryOrePercentage) {
                    world.setBlockState(blockPos, secondaryBlock, 2);
                } else {
                    world.setBlockState(blockPos, primaryBlock, 2);
                }
            }
        }
        return true;
    }
}