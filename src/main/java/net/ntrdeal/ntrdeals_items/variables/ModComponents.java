package net.ntrdeal.ntrdeals_items.variables;

import net.minecraft.component.ComponentType;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.text.TextCodecs;
import net.minecraft.util.Identifier;
import net.ntrdeal.ntrdeals_items.NTRDealsItems;

import java.util.function.UnaryOperator;

public class ModComponents {

    public static final ComponentType<AttributeModifiersComponent> INFUSE_COMPONENTS = register("infuse_components", builder ->
            builder.codec(AttributeModifiersComponent.CODEC));

    public static final ComponentType<Text> ARMOR_TRIM_MATERIAL = register("armor_trim_material", builder ->
            builder.codec(TextCodecs.STRINGIFIED_CODEC));

    private static <T>ComponentType<T> register(String name, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(NTRDealsItems.MOD_ID, name),
                builderOperator.apply(ComponentType.builder()).build());
    }

    public static void registerComponents(){
        NTRDealsItems.LOGGER.info("Components are registering for: "+NTRDealsItems.MOD_ID);
    }
}
