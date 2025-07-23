package net.ntrdeal.ntrdeals_items.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.ntrdeal.ntrdeals_items.attribute.ModAttributes;
import net.ntrdeal.ntrdeals_items.block.ModBlocks;
import net.ntrdeal.ntrdeals_items.item.ModItemGroups;
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
        translationBuilder.add(ModBlocks.RAW_LUNARITE_BLOCK, "Block of Raw Lunarite");
        translationBuilder.add(ModBlocks.RAW_COSMOLITE_BLOCK, "Block of Raw Cosmolite");
        translationBuilder.add(ModBlocks.DRIED_CHORUS_FLOWER, "Dried Chorus Flower");

        translationBuilder.add(ModAttributes.MOVEMENT_SCALE, "Movement Scale");
        translationBuilder.add(ModAttributes.ARMOR_PENETRATION, "Armor Penetration");
        translationBuilder.add(ModAttributes.PASSIVE_REGENERATION, "Passive Regeneration");
        translationBuilder.add(ModAttributes.APPETITE, "Appetite");
        translationBuilder.add(ModAttributes.SCALE, "Scale");
        translationBuilder.add(ModAttributes.SHIELD_FRAGILITY, "Shield Fragility");
        translationBuilder.add(ModAttributes.RANGED_ATTACK_MULTIPLIER, "Ranged Attack Multiplier");
        translationBuilder.add(ModAttributes.BANE_OF_ADOLESCENCE, "Bane of Adolescence");
        translationBuilder.add(ModAttributes.CHARGE_TIME, "Charge Time");

        translationBuilder.add(Registries.ITEM_GROUP.getKey(ModItemGroups.NTRDEALS_ITEMS_GROUP).get(), "NTRDeal's Items");

        translationBuilder.add("ntrdeals-items.advancements.adventure.extremely_dedicated.title", "Extreme Dedication");
        translationBuilder.add("ntrdeals-items.advancements.adventure.extremely_dedicated.description", "Trim a full cosmolite set with netherite silence");
    }
}
