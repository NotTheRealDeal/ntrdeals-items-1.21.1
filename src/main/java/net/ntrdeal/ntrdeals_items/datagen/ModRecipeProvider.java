package net.ntrdeal.ntrdeals_items.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.ntrdeal.ntrdeals_items.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.LUNARITE_INGOT)
                        .input(ModItems.LUNARITE, 2)
                        .criterion(hasItem(ModItems.LUNARITE), conditionsFromItem(ModItems.LUNARITE))
                        .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.COSMOLITE_INGOT)
                        .input(ModItems.COSMOLITE, 2)
                        .input(ModItems.LUNARITE_INGOT, 1)
                        .criterion(hasItem(ModItems.COSMOLITE), conditionsFromItem(ModItems.COSMOLITE))
                        .offerTo(exporter);

        /*ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.LUNARITE_BLOCK)
                        .input(ModItems.LUNARITE_INGOT, 9)
                        .criterion(hasItem(ModItems.LUNARITE_INGOT), conditionsFromItem(ModItems.LUNARITE_INGOT))
                        .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.COSMOLITE_BLOCK)
                        .input(ModItems.COSMOLITE_INGOT, 9)
                        .criterion(hasItem(ModItems.COSMOLITE_INGOT), conditionsFromItem(ModItems.COSMOLITE_INGOT))
                        .offerTo(exporter);*/
        
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.LUNARITE_HELMET)
                .pattern("LLL")
                .pattern("L L")
                .input('L', ModItems.LUNARITE_INGOT)
                .criterion(hasItem(ModItems.LUNARITE_INGOT), conditionsFromItem(ModItems.LUNARITE_INGOT))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.LUNARITE_CHESTPLATE)
                .pattern("L L")
                .pattern("LLL")
                .pattern("LLL")
                .input('L', ModItems.LUNARITE_INGOT)
                .criterion(hasItem(ModItems.LUNARITE_INGOT), conditionsFromItem(ModItems.LUNARITE_INGOT))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.LUNARITE_LEGGINGS)
                .pattern("LLL")
                .pattern("L L")
                .pattern("L L")
                .input('L', ModItems.LUNARITE_INGOT)
                .criterion(hasItem(ModItems.LUNARITE_INGOT), conditionsFromItem(ModItems.LUNARITE_INGOT))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.LUNARITE_BOOTS)
                .pattern("L L")
                .pattern("L L")
                .input('L', ModItems.LUNARITE_INGOT)
                .criterion(hasItem(ModItems.LUNARITE_INGOT), conditionsFromItem(ModItems.LUNARITE_INGOT))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.COSMOLITE_HELMET)
                .pattern("CCC")
                .pattern("C C")
                .input('C', ModItems.COSMOLITE_INGOT)
                .criterion(hasItem(ModItems.COSMOLITE_INGOT), conditionsFromItem(ModItems.COSMOLITE_INGOT))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.COSMOLITE_CHESTPLATE)
                .pattern("C C")
                .pattern("CCC")
                .pattern("CCC")
                .input('C', ModItems.COSMOLITE_INGOT)
                .criterion(hasItem(ModItems.COSMOLITE_INGOT), conditionsFromItem(ModItems.COSMOLITE_INGOT))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.COSMOLITE_LEGGINGS)
                .pattern("CCC")
                .pattern("C C")
                .pattern("C C")
                .input('C', ModItems.COSMOLITE_INGOT)
                .criterion(hasItem(ModItems.COSMOLITE_INGOT), conditionsFromItem(ModItems.COSMOLITE_INGOT))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.COSMOLITE_BOOTS)
                .pattern("C C")
                .pattern("C C")
                .input('C', ModItems.COSMOLITE_INGOT)
                .criterion(hasItem(ModItems.COSMOLITE_INGOT), conditionsFromItem(ModItems.COSMOLITE_INGOT))
                .offerTo(exporter);
    }
}
