package net.ntrdeal.ntrdeals_items.variables;

import net.minecraft.component.ComponentType;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.text.TextCodecs;
import net.minecraft.util.Identifier;
import net.ntrdeal.ntrdeals_items.NTRDealsItems;

public class ModComponents {
    public static final ComponentType<AttributeModifiersComponent> INFUSE_COMPONENTS =
            Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(NTRDealsItems.MOD_ID, BigVariableClass.INFUSE_COMPONENTS_KEY),
            ComponentType.<AttributeModifiersComponent>builder().codec(AttributeModifiersComponent.CODEC).build());

    public static final ComponentType<Text> ARMOR_TRIM_MATERIAL =
            Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(NTRDealsItems.MOD_ID, BigVariableClass.ARMOR_TRIM_MATERIAL_KEY),
            ComponentType.<Text>builder().codec(TextCodecs.STRINGIFIED_CODEC).build());

    public static void registerComponents(){
        NTRDealsItems.LOGGER.info("Components are registering for: "+NTRDealsItems.MOD_ID);
    }
}
