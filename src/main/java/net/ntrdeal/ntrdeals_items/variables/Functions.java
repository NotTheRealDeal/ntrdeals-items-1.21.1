package net.ntrdeal.ntrdeals_items.variables;

import net.minecraft.component.ComponentChanges;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.input.SmithingRecipeInput;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.random.Random;
import net.ntrdeal.ntrdeals_items.NTRDealsItems;
import net.ntrdeal.ntrdeals_items.config.InfusionMaterialUtil;
import net.ntrdeal.ntrdeals_items.config.InfusionUtil;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class Functions {

    private static final Map<EquipmentSlot, AttributeModifierSlot> SLOT_MAP = Map.of(
            EquipmentSlot.HEAD, AttributeModifierSlot.HEAD,
            EquipmentSlot.CHEST, AttributeModifierSlot.CHEST,
            EquipmentSlot.LEGS, AttributeModifierSlot.LEGS,
            EquipmentSlot.FEET, AttributeModifierSlot.FEET
    );

    public static ItemStack refreshInfusion(Object object){
        if (object instanceof ItemStack stack){
            if (canInfuse(stack) instanceof ItemStack){
                infuseStack(stack, Objects.requireNonNull(stack.get(DataComponentTypes.TRIM)).getMaterial().value().ingredient().value());
                return stack;
            } else if (stack.contains(ModComponents.INFUSE_COMPONENTS)) {
                stack.remove(ModComponents.INFUSE_COMPONENTS);
                return stack;
            } else return null;
        } else return null;
    }

    public static boolean canInfuse(SmithingRecipeInput recipe, ItemStack stack){
        if (recipe.base().getItem() instanceof ArmorItem base){
            if (InfusionMaterialUtil.containsMaterial(base.getMaterial())){
                if (InfusionUtil.containsItem(recipe.addition().getItem())){
                    return true;
                } else if (recipe.base().contains(ModComponents.INFUSE_COMPONENTS)){
                    stack.remove(ModComponents.INFUSE_COMPONENTS);
                    return false;
                }
            }
        }
        return false;
    }

    @Nullable
    public static ItemStack canInfuse(ItemStack stack){
        if (stack.contains(DataComponentTypes.TRIM) && stack.getItem() instanceof ArmorItem armorItem && InfusionMaterialUtil.containsMaterial(armorItem.getMaterial())){
            return stack;
        }
        return null;
    }

    public static void infuseStack(ItemStack stack, Item addition) {
        if (stack.getItem() instanceof ArmorItem item && InfusionUtil.getMap(addition) instanceof Map<RegistryEntry<EntityAttribute>, Double> attributes){
            System.out.println(attributes);
            AttributeModifiersComponent.Builder builder = AttributeModifiersComponent.builder();
            for (RegistryEntry<EntityAttribute> entry: attributes.keySet()){
                builder.add(entry, new EntityAttributeModifier(Identifier.of(NTRDealsItems.MOD_ID+ UUID.randomUUID()),
                        attributes.get(entry),
                        EntityAttributeModifier.Operation.ADD_VALUE), SLOT_MAP.get(item.getSlotType()));
            }
            if (stack.getOrDefault(DataComponentTypes.ATTRIBUTE_MODIFIERS, AttributeModifiersComponent.DEFAULT).modifiers().isEmpty()) {
                stack.applyChanges(ComponentChanges.builder().add(DataComponentTypes.ATTRIBUTE_MODIFIERS, AttributeModifiersComponent.builder()
                        .add(EntityAttributes.GENERIC_ARMOR, new EntityAttributeModifier(Identifier.of(NTRDealsItems.MOD_ID+ Random.create().nextBetween(1,99999999)), item.getProtection(), EntityAttributeModifier.Operation.ADD_VALUE), SLOT_MAP.get(item.getSlotType()))
                        .add(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, new EntityAttributeModifier(Identifier.of(NTRDealsItems.MOD_ID+ Random.create().nextBetween(1,99999999)), item.getToughness(), EntityAttributeModifier.Operation.ADD_VALUE), SLOT_MAP.get(item.getSlotType()))
                        .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, new EntityAttributeModifier(Identifier.of(NTRDealsItems.MOD_ID+ Random.create().nextBetween(1,99999999)), item.getMaterial().value().knockbackResistance(), EntityAttributeModifier.Operation.ADD_VALUE), SLOT_MAP.get(item.getSlotType()))
                        .build()).build());
            }
            stack.applyChanges(ComponentChanges.builder().add(ModComponents.INFUSE_COMPONENTS, builder.build()).build());
        }
    }

    public static float normalizeMovement(float scale){
        return scale<=1.0F ? scale : (((scale-1)*0.5F)+1);
    }
}
