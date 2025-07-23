package net.ntrdeal.ntrdeals_items.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.item.ArmorItem;
import net.ntrdeal.ntrdeals_items.block.ModBlocks;
import net.ntrdeal.ntrdeals_items.item.ModItems;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.LUNARITE_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.COSMOLITE_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RAW_LUNARITE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RAW_COSMOLITE_BLOCK);

        /*blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.LUNARITE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.COSMOLITE_BLOCK);*/
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.LUNARITE, Models.GENERATED);
        itemModelGenerator.register(ModItems.COSMOLITE, Models.GENERATED);
        itemModelGenerator.register(ModItems.LUNARITE_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.COSMOLITE_INGOT, Models.GENERATED);

        itemModelGenerator.registerArmor(((ArmorItem) ModItems.LUNARITE_HELMET));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.LUNARITE_CHESTPLATE));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.LUNARITE_LEGGINGS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.LUNARITE_BOOTS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.COSMOLITE_HELMET));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.COSMOLITE_CHESTPLATE));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.COSMOLITE_LEGGINGS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.COSMOLITE_BOOTS));
    }
}
