package net.ntrdeal.ntrdeals_items.attribute;

import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.ntrdeal.ntrdeals_items.NTRDealsItems;

public class ModAttributes {

    public static final RegistryEntry<EntityAttribute> MOVEMENT_SCALE = register("generic.movement_scale", new ClampedEntityAttribute(
            "attribute.ntrdeals-items.generic.movement_scale",
            1.0,
            0.0,
            1024.0).setTracked(true).setCategory(EntityAttribute.Category.POSITIVE));

    public static final RegistryEntry<EntityAttribute> ARMOR_PENETRATION = register("generic.armor_penetration", new ClampedEntityAttribute(
            "attribute.ntrdeals-items.generic.armor_penetration",
            0.0,
            0.0,
            1024.0).setTracked(true).setCategory(EntityAttribute.Category.POSITIVE));

    public static final RegistryEntry<EntityAttribute> PASSIVE_REGENERATION = register("generic.passive_regeneration", new ClampedEntityAttribute(
            "attribute.ntrdeals-items.generic.passive_regeneration",
            0.0,
            0.0,
            1024.0).setTracked(true).setCategory(EntityAttribute.Category.POSITIVE));

    public static final RegistryEntry<EntityAttribute> APPETITE = register("player.appetite", new ClampedEntityAttribute(
            "attribute.ntrdeals-items.player.appetite",
            1.0,
            0.25,
            1024.0).setTracked(true).setCategory(EntityAttribute.Category.NEGATIVE));

    public static final RegistryEntry<EntityAttribute> SHIELD_FRAGILITY = register("player.shield_fragility", new ClampedEntityAttribute(
            "attribute.ntrdeals-items.player.shield_fragility",
            1.0,
            0.0,
            1024.0).setTracked(true).setCategory(EntityAttribute.Category.NEGATIVE));

    public static final RegistryEntry<EntityAttribute> SCALE = register("generic.scale", new ClampedEntityAttribute(
            "attribute.ntrdeals-items.generic.scale",
            0.0,
            -0.75,
            0.0).setTracked(true).setCategory(EntityAttribute.Category.NEUTRAL));

    public static final RegistryEntry<EntityAttribute> RANGED_ATTACK_MULTIPLIER = register("generic.ranged_attack_multiplier", new ClampedEntityAttribute(
            "attribute.ntrdeals-items.generic.ranged_attack_multiplier",
            1.0,
            0.0,
            1024.0).setTracked(true).setCategory(EntityAttribute.Category.POSITIVE));

    public static final RegistryEntry<EntityAttribute> BANE_OF_ADOLESCENCE = register("generic.bane_of_adolescence", new ClampedEntityAttribute(
            "attribute.ntrdeals-items.generic.bane_of_adolescence",
            0.0,
            0.0,
            1024.0).setTracked(true).setCategory(EntityAttribute.Category.POSITIVE));

    public static final RegistryEntry<EntityAttribute> CHARGE_TIME = register("generic.charge_time", new ClampedEntityAttribute(
            "attribute.ntrdeals-items.generic.charge_time",
            1.0,
            0.0,
            1024.0).setTracked(true).setCategory(EntityAttribute.Category.NEGATIVE));

    private static RegistryEntry<EntityAttribute> register(String id, EntityAttribute attribute) {
        return Registry.registerReference(Registries.ATTRIBUTE, Identifier.of(NTRDealsItems.MOD_ID, id), attribute);
    }

    public static void registerAttributes(){
        NTRDealsItems.LOGGER.info("Registering mod attributes!");
    }
}
