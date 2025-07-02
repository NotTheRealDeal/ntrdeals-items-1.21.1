package net.ntrdeal.ntrdeals_items.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;
import net.ntrdeal.ntrdeals_items.block.ModBlocks;
import net.ntrdeal.ntrdeals_items.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class EnglishProvider extends FabricLanguageProvider {
    public EnglishProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup wrapperLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add(ModItems.COSMOLITE, "Cosmolite");
        translationBuilder.add(ModItems.LUNARITE, "Lunarite");
        translationBuilder.add(ModItems.COSMOLITE_INGOT, "Cosmolite Ingot");
        translationBuilder.add(ModItems.LUNARITE_INGOT, "Lunarite Ingot");

        translationBuilder.add(ModItems.LUNARITE_HELMET, "Lunarite Helmet");
        translationBuilder.add(ModItems.LUNARITE_CHESTPLATE, "Lunarite Chestplate");
        translationBuilder.add(ModItems.LUNARITE_LEGGINGS, "Lunarite Leggings");
        translationBuilder.add(ModItems.LUNARITE_BOOTS, "Lunarite Boots");

        translationBuilder.add(ModItems.COSMOLITE_HELMET, "Cosmolite Helmet");
        translationBuilder.add(ModItems.COSMOLITE_CHESTPLATE, "Cosmolite Chestplate");
        translationBuilder.add(ModItems.COSMOLITE_LEGGINGS, "Cosmolite Leggings");
        translationBuilder.add(ModItems.COSMOLITE_BOOTS, "Cosmolite Boots");

        translationBuilder.add(ModBlocks.LUNARITE_ORE, "Lunarite Ore");
        translationBuilder.add(ModBlocks.COSMOLITE_ORE, "Cosmolite Ore");

        translationBuilder.add("itemgroup.ntrdeals_items.ntrdeal_items_items", "NTRDeal's Items");
    }
}
