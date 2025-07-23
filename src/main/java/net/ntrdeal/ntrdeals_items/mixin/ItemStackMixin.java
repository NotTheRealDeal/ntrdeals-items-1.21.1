package net.ntrdeal.ntrdeals_items.mixin;

import net.minecraft.component.ComponentType;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.item.ItemStack;
import net.ntrdeal.ntrdeals_items.variables.ModComponents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {

    @Redirect(method = "applyAttributeModifier", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getOrDefault(Lnet/minecraft/component/ComponentType;Ljava/lang/Object;)Ljava/lang/Object;"))
    private Object Injected(ItemStack instance, ComponentType componentType, Object o){
        AttributeModifiersComponent attribute_modifiers = instance.getOrDefault(DataComponentTypes.ATTRIBUTE_MODIFIERS, AttributeModifiersComponent.DEFAULT);
        AttributeModifiersComponent infuse_components = instance.getOrDefault(ModComponents.INFUSE_COMPONENTS, AttributeModifiersComponent.DEFAULT);
        AttributeModifiersComponent.Builder builder = AttributeModifiersComponent.builder();
        if (!attribute_modifiers.modifiers().isEmpty()) for (AttributeModifiersComponent.Entry entry : attribute_modifiers.modifiers()) builder.add(entry.attribute(), entry.modifier(), entry.slot());
        if (!infuse_components.modifiers().isEmpty()) for (AttributeModifiersComponent.Entry entry : infuse_components.modifiers()) builder.add(entry.attribute(), entry.modifier(), entry.slot());
        return builder.build();
    }

    @Redirect(method = "applyAttributeModifiers", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getOrDefault(Lnet/minecraft/component/ComponentType;Ljava/lang/Object;)Ljava/lang/Object;"))
    private Object Injected2(ItemStack instance, ComponentType componentType, Object o){
        AttributeModifiersComponent attribute_modifiers = instance.getOrDefault(DataComponentTypes.ATTRIBUTE_MODIFIERS, AttributeModifiersComponent.DEFAULT);
        AttributeModifiersComponent infuse_components = instance.getOrDefault(ModComponents.INFUSE_COMPONENTS, AttributeModifiersComponent.DEFAULT);
        AttributeModifiersComponent.Builder builder = AttributeModifiersComponent.builder();
        if (!attribute_modifiers.modifiers().isEmpty()) for (AttributeModifiersComponent.Entry entry : attribute_modifiers.modifiers()) builder.add(entry.attribute(), entry.modifier(), entry.slot());
        if (!infuse_components.modifiers().isEmpty()) for (AttributeModifiersComponent.Entry entry : infuse_components.modifiers()) builder.add(entry.attribute(), entry.modifier(), entry.slot());
        return builder.build();
    }
}
