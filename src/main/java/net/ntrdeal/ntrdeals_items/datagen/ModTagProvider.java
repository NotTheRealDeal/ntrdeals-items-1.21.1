package net.ntrdeal.ntrdeals_items.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.ntrdeal.ntrdeals_items.item.ModItemTags;
import net.ntrdeal.ntrdeals_items.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(ItemTags.HEAD_ARMOR)
                .add(ModItems.COSMOLITE_HELMET);
        getOrCreateTagBuilder(ItemTags.CHEST_ARMOR)
                .add(ModItems.COSMOLITE_CHESTPLATE);
        getOrCreateTagBuilder(ItemTags.LEG_ARMOR)
                .add(ModItems.COSMOLITE_LEGGINGS);
        getOrCreateTagBuilder(ItemTags.FOOT_ARMOR)
                .add(ModItems.COSMOLITE_BOOTS);

        getOrCreateTagBuilder(ModItemTags.COSMOLITE_ARMOR)
                .add(ModItems.COSMOLITE_HELMET)
                .add(ModItems.COSMOLITE_CHESTPLATE)
                .add(ModItems.COSMOLITE_LEGGINGS)
                .add(ModItems.COSMOLITE_BOOTS);

        getOrCreateTagBuilder(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.LUNARITE_HELMET)
                .add(ModItems.LUNARITE_CHESTPLATE)
                .add(ModItems.LUNARITE_LEGGINGS)
                .add(ModItems.LUNARITE_BOOTS)
                .add(ModItems.COSMOLITE_HELMET)
                .add(ModItems.COSMOLITE_CHESTPLATE)
                .add(ModItems.COSMOLITE_LEGGINGS)
                .add(ModItems.COSMOLITE_BOOTS);
    }
}
