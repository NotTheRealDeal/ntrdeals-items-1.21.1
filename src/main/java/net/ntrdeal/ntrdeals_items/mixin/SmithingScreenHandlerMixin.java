package net.ntrdeal.ntrdeals_items.mixin;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.SmithingRecipe;
import net.minecraft.recipe.input.SmithingRecipeInput;
import net.minecraft.screen.SmithingScreenHandler;
import net.ntrdeal.ntrdeals_items.variables.Functions;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(SmithingScreenHandler.class)
public abstract class SmithingScreenHandlerMixin {
    @Shadow
    @Nullable
    private RecipeEntry<SmithingRecipe> currentRecipe;

    @Shadow protected abstract SmithingRecipeInput createRecipeInput();

    @ModifyVariable(
            method = "updateResult()V",
            at = @At(
                    value = "STORE",
                    ordinal = 0,
                    target = "Lnet/minecraft/item/ItemStack;itemStack:Lnet/minecraft/item/ItemStack;"
            )
    )
    private ItemStack modifyCraftedItemStack(ItemStack itemStack) {
        if (this.currentRecipe != null && Functions.canInfuse(createRecipeInput(), itemStack)) {
            Functions.infuseStack(itemStack, createRecipeInput().addition().getItem());
        }
        return itemStack;
    }
}
