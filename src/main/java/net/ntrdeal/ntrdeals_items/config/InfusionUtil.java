package net.ntrdeal.ntrdeals_items.config;

import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.ntrdeal.ntrdeals_items.NTRDealsItems;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class InfusionUtil {
    public static final File CONFIG = new File("config");
    public static final File INFUSION_FILE = new File("config/infusionEntries.json");
    public static final InfusionList INFUSIONS = new InfusionList(INFUSION_FILE);

    private static final Map<String, Map<String, Double>> ATTRIBUTES = Map.of(
            Items.REDSTONE.toString(), Map.of(EntityAttributes.GENERIC_MOVEMENT_SPEED.getIdAsString(), 0.025d, EntityAttributes.GENERIC_STEP_HEIGHT.getIdAsString(), 0.5d),
            Items.LAPIS_LAZULI.toString(), Map.of(EntityAttributes.GENERIC_SCALE.getIdAsString(), -0.25d, EntityAttributes.GENERIC_MAX_HEALTH.getIdAsString(), -5d),
            Items.COPPER_INGOT.toString(), Map.of(EntityAttributes.GENERIC_SCALE.getIdAsString(), 0.25d, EntityAttributes.GENERIC_MAX_HEALTH.getIdAsString(), 5d),
            Items.DIAMOND.toString(), Map.of(EntityAttributes.GENERIC_ARMOR.getIdAsString(), 2d),
            Items.IRON_INGOT.toString(), Map.of(EntityAttributes.GENERIC_ATTACK_DAMAGE.getIdAsString(), 1d, EntityAttributes.GENERIC_ARMOR.getIdAsString(), -1.5d),
            Items.QUARTZ.toString(), Map.of(EntityAttributes.GENERIC_ATTACK_KNOCKBACK.getIdAsString(), 0.5d, EntityAttributes.GENERIC_FALL_DAMAGE_MULTIPLIER.getIdAsString(), -0.25d),
            Items.NETHERITE_INGOT.toString(), Map.of(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE.getIdAsString(), 0.075d, EntityAttributes.GENERIC_BURNING_TIME.getIdAsString(), -0.25d),
            Items.GOLD_INGOT.toString(), Map.of(EntityAttributes.PLAYER_BLOCK_BREAK_SPEED.getIdAsString(), 0.25d, EntityAttributes.GENERIC_ATTACK_SPEED.getIdAsString(), 0.6d, EntityAttributes.GENERIC_ATTACK_DAMAGE.getIdAsString(), -2d),
            Items.EMERALD.toString(), Map.of(EntityAttributes.GENERIC_WATER_MOVEMENT_EFFICIENCY.getIdAsString(), 0.25d, EntityAttributes.PLAYER_SUBMERGED_MINING_SPEED.getIdAsString(), 0.2d, EntityAttributes.GENERIC_OXYGEN_BONUS.getIdAsString(), 0.75d),
            Items.AMETHYST_SHARD.toString(), Map.of(EntityAttributes.GENERIC_GRAVITY.getIdAsString(), -0.01d, EntityAttributes.GENERIC_FALL_DAMAGE_MULTIPLIER.getIdAsString(), -0.125d, EntityAttributes.PLAYER_SNEAKING_SPEED.getIdAsString(), 0.1125d)
    );

    public static void registerInfusionEntry() throws IOException{
        CONFIG.mkdir();
        if (INFUSION_FILE.createNewFile()){
            for (String key : ATTRIBUTES.keySet()){
                Map<String, Double> attributeMap = new java.util.HashMap<>(Map.of());
                for (String attribute : ATTRIBUTES.get(key).keySet()){
                    attributeMap.put(attribute, ATTRIBUTES.get(key).get(attribute));
                }
                INFUSIONS.add(new InfusionEntry(key, attributeMap));
            }
            NTRDealsItems.LOGGER.info("Infusion json made!");
        } else INFUSIONS.load();
    }

    public static Map<RegistryEntry<EntityAttribute>, Double> getMap(Map<String, Double> map){
        Map<RegistryEntry<EntityAttribute>, Double> processedMap = new java.util.HashMap<>(Map.of());
        for (String index : map.keySet()){
            processedMap.put(Registries.ATTRIBUTE.getEntry(Registries.ATTRIBUTE.get(Identifier.of(index))), map.get(index));
        }
        return processedMap;
    }

}
