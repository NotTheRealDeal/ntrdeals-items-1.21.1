package net.ntrdeal.ntrdeals_items.mixin;

import net.minecraft.component.ComponentMap;
import net.minecraft.component.ComponentType;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.item.Item;
import net.ntrdeal.ntrdeals_items.variables.ModComponents;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.Settings.class)
public abstract class ItemSettingsMixin {
    @Shadow @Nullable private ComponentMap.@Nullable Builder components;

    @Shadow protected abstract ComponentMap getComponents();

    @Shadow public abstract <T> Item.Settings component(ComponentType<T> type, T value);

    @Inject(method = "attributeModifiers", at = @At("HEAD"), cancellable = true)
    private void Injected(AttributeModifiersComponent attributeModifiersComponent, CallbackInfoReturnable<Item.Settings> cir){
        assert this.components != null;
        if (this.getComponents().get(ModComponents.INFUSE_COMPONENTS) instanceof AttributeModifiersComponent attributeModifiersComponent1){
            AttributeModifiersComponent.Builder builder = AttributeModifiersComponent.builder();
            for (AttributeModifiersComponent.Entry entry : attributeModifiersComponent.modifiers()) builder.add(entry.attribute(), entry.modifier(), entry.slot());
            for (AttributeModifiersComponent.Entry entry : attributeModifiersComponent1.modifiers()) builder.add(entry.attribute(), entry.modifier(), entry.slot());
            cir.setReturnValue(this.component(DataComponentTypes.ATTRIBUTE_MODIFIERS, builder.build()));
        }
    }
}
