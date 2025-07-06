package net.ntrdeal.ntrdeals_items.variables;

import net.minecraft.component.ComponentChanges;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.*;
import net.minecraft.recipe.input.SmithingRecipeInput;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.random.Random;
import net.ntrdeal.ntrdeals_items.NTRDealsItems;
import net.ntrdeal.ntrdeals_items.item.ModArmorMaterials;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Functions {

    private static final List<RegistryEntry<ArmorMaterial>> MATERIALS = List.of(
            ModArmorMaterials.COSMOLITE,
            ModArmorMaterials.LUNARITE
    );

    private static final Map<Item, Map<RegistryEntry<EntityAttribute>, Double>> ATTRIBUTES = Map.of(
            Items.REDSTONE, Map.of(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.025d, EntityAttributes.GENERIC_STEP_HEIGHT, 0.5d),
            Items.LAPIS_LAZULI, Map.of(EntityAttributes.GENERIC_SCALE, -0.25d, EntityAttributes.GENERIC_MAX_HEALTH, -5d),
            Items.COPPER_INGOT, Map.of(EntityAttributes.GENERIC_SCALE, 0.25d, EntityAttributes.GENERIC_MAX_HEALTH, 5d),
            Items.DIAMOND, Map.of(EntityAttributes.GENERIC_ARMOR, 2d),
            Items.IRON_INGOT, Map.of(EntityAttributes.GENERIC_ATTACK_DAMAGE, 1d, EntityAttributes.GENERIC_ARMOR, -1.5d),
            Items.QUARTZ, Map.of(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 0.5d, EntityAttributes.GENERIC_FALL_DAMAGE_MULTIPLIER, -0.25d),
            Items.NETHERITE_INGOT, Map.of(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 0.075d, EntityAttributes.GENERIC_BURNING_TIME, -0.25d),
            Items.GOLD_INGOT, Map.of(EntityAttributes.PLAYER_BLOCK_BREAK_SPEED, 0.25d, EntityAttributes.GENERIC_ATTACK_SPEED, 0.6d, EntityAttributes.GENERIC_ATTACK_DAMAGE, -2d),
            Items.EMERALD, Map.of(EntityAttributes.GENERIC_WATER_MOVEMENT_EFFICIENCY, 0.25d, EntityAttributes.PLAYER_SUBMERGED_MINING_SPEED, 0.2d, EntityAttributes.GENERIC_OXYGEN_BONUS, 0.75d),
            Items.AMETHYST_SHARD, Map.of(EntityAttributes.GENERIC_GRAVITY, -0.01d, EntityAttributes.GENERIC_FALL_DAMAGE_MULTIPLIER, -0.125d, EntityAttributes.PLAYER_SNEAKING_SPEED, 0.1125d)
    );

    private static final Map<EquipmentSlot, AttributeModifierSlot> SLOT_MAP = Map.of(
            EquipmentSlot.HEAD, AttributeModifierSlot.HEAD,
            EquipmentSlot.CHEST, AttributeModifierSlot.CHEST,
            EquipmentSlot.LEGS, AttributeModifierSlot.LEGS,
            EquipmentSlot.FEET, AttributeModifierSlot.FEET
    );

    public static boolean canInfuse(SmithingRecipeInput recipe, ItemStack stack){
        if (recipe.base().getItem() instanceof ArmorItem base){
            if (MATERIALS.contains(base.getMaterial())){
                if (ATTRIBUTES.containsKey(recipe.addition().getItem())){
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
        if (stack.contains(DataComponentTypes.TRIM) && stack.getItem() instanceof ArmorItem armorItem && MATERIALS.contains(armorItem.getMaterial())){
            return stack;
        }
        return null;
    }

    public static void infuseStack(ItemStack stack, Item addition) {
        if (stack.getItem() instanceof ArmorItem item){
            Map<RegistryEntry<EntityAttribute>, Double> attributes = ATTRIBUTES.get(addition);
            AttributeModifiersComponent.Builder builder = AttributeModifiersComponent.builder();
            for (RegistryEntry<EntityAttribute> entry: attributes.keySet()){
                builder.add(entry, new EntityAttributeModifier(Identifier.of(NTRDealsItems.MOD_ID+Random.create().nextBetween(1,99999999)),
                        attributes.get(entry),
                        EntityAttributeModifier.Operation.ADD_VALUE), SLOT_MAP.get(item.getSlotType()));
            }
            if (stack.getOrDefault(DataComponentTypes.ATTRIBUTE_MODIFIERS, stack.getItem().getAttributeModifiers()).modifiers().isEmpty()) {
                stack.applyChanges(ComponentChanges.builder().add(DataComponentTypes.ATTRIBUTE_MODIFIERS, AttributeModifiersComponent.builder()
                        .add(EntityAttributes.GENERIC_ARMOR, new EntityAttributeModifier(Identifier.of(NTRDealsItems.MOD_ID+ Random.create().nextBetween(1,99999999)), item.getProtection(), EntityAttributeModifier.Operation.ADD_VALUE), SLOT_MAP.get(item.getSlotType()))
                        .add(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, new EntityAttributeModifier(Identifier.of(NTRDealsItems.MOD_ID+ Random.create().nextBetween(1,99999999)), item.getToughness(), EntityAttributeModifier.Operation.ADD_VALUE), SLOT_MAP.get(item.getSlotType()))
                        .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, new EntityAttributeModifier(Identifier.of(NTRDealsItems.MOD_ID+ Random.create().nextBetween(1,99999999)), item.getMaterial().value().knockbackResistance(), EntityAttributeModifier.Operation.ADD_VALUE), SLOT_MAP.get(item.getSlotType()))
                        .build()).build());
            }
            stack.applyChanges(ComponentChanges.builder().add(ModComponents.INFUSE_COMPONENTS, builder.build()).add(ModComponents.ARMOR_TRIM_MATERIAL, addition.getName()).build());
        }
    }

    public static boolean containsLapis(ItemStack stack){
        if (stack.contains(ModComponents.ARMOR_TRIM_MATERIAL)) return Objects.requireNonNull(stack.get(ModComponents.ARMOR_TRIM_MATERIAL)).contains(Items.LAPIS_LAZULI.getName());
        else return false;
    }

    public static boolean is4Lapis(List<ItemStack> list){
        for (ItemStack stack : list) if (!containsLapis(stack)) return false;
        return true;
    }
}
