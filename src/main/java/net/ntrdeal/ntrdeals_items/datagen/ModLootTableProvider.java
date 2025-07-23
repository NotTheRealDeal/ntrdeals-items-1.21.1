package net.ntrdeal.ntrdeals_items.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemConvertible;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.registry.RegistryWrapper;
import net.ntrdeal.ntrdeals_items.block.ModBlocks;
import net.ntrdeal.ntrdeals_items.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.LUNARITE_ORE, oreDrops(ModBlocks.LUNARITE_ORE, ModItems.LUNARITE));
        addDrop(ModBlocks.COSMOLITE_ORE, oreDrops(ModBlocks.COSMOLITE_ORE, ModItems.COSMOLITE));
        addDrop(ModBlocks.RAW_LUNARITE_BLOCK);
        addDrop(ModBlocks.RAW_COSMOLITE_BLOCK);
        addSilkTouchDrop(ModBlocks.DRIED_CHORUS_FLOWER, Blocks.CHORUS_FLOWER);
    }

    private void addSilkTouchDrop(Block block, ItemConvertible drop){
        addDrop(block, dropsWithSilkTouch(block, addSurvivesExplosionCondition(block, ItemEntry.builder(drop))));
    }
}
