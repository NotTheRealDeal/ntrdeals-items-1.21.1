package net.ntrdeal.ntrdeals_items.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.ntrdeal.ntrdeals_items.block.ModBlockTags;
import net.ntrdeal.ntrdeals_items.block.ModBlocks;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.LUNARITE_ORE)
                .add(ModBlocks.COSMOLITE_ORE)
                .add(ModBlocks.RAW_LUNARITE_BLOCK)
                .add(ModBlocks.RAW_COSMOLITE_BLOCK)
                .add(ModBlocks.DRIED_CHORUS_FLOWER);

        getOrCreateTagBuilder(ModBlockTags.GEODE_CANNOT_REPLACE)
                .add(Blocks.BEDROCK).add(Blocks.CHEST).add(Blocks.END_PORTAL_FRAME).add(Blocks.REINFORCED_DEEPSLATE).add(Blocks.SPAWNER).add(Blocks.TRIAL_SPAWNER).add(Blocks.VAULT);

        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.LUNARITE_ORE)
                .add(ModBlocks.RAW_LUNARITE_BLOCK);

        getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.COSMOLITE_ORE)
                .add(ModBlocks.RAW_COSMOLITE_BLOCK);
        getOrCreateTagBuilder(BlockTags.INCORRECT_FOR_DIAMOND_TOOL)
                .add(ModBlocks.COSMOLITE_ORE)
                .add(ModBlocks.RAW_COSMOLITE_BLOCK);
    }
}
