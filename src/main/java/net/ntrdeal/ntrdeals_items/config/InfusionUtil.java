package net.ntrdeal.ntrdeals_items.config;

import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.ntrdeal.ntrdeals_items.NTRDealsItems;
import net.ntrdeal.ntrdeals_items.attribute.ModAttributes;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class InfusionUtil {
    public static final File CONFIG = new File("config");
    public static final File NTRDEALS_CONFIG = new File("config/ntrdeals-config");
    public static final File INFUSION_FILE = new File("config/ntrdeals-config/infusion-entries.json");
    public static final InfusionList INFUSIONS = new InfusionList(INFUSION_FILE);

    private static final Map<Item, Map<RegistryEntry<EntityAttribute>, Double>> ATTRIBUTES = Map.of(
            Items.REDSTONE, Map.of(ModAttributes.MOVEMENT_SCALE, 0.125, EntityAttributes.GENERIC_STEP_HEIGHT, 0.25d),
            Items.LAPIS_LAZULI, Map.of(ModAttributes.SCALE, -0.25d, EntityAttributes.GENERIC_MAX_HEALTH, -5d),
            Items.COPPER_INGOT, Map.of(EntityAttributes.GENERIC_SCALE, 0.25d, EntityAttributes.GENERIC_MAX_HEALTH, 5d),
            Items.DIAMOND, Map.of(EntityAttributes.GENERIC_ARMOR, 2d, ModAttributes.SHIELD_FRAGILITY, -0.15d),
            Items.IRON_INGOT, Map.of(ModAttributes.ARMOR_PENETRATION, 2d, EntityAttributes.GENERIC_ARMOR, -1.5d, ModAttributes.SHIELD_FRAGILITY, 0.15d),
            Items.QUARTZ, Map.of(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 0.5d, EntityAttributes.GENERIC_FALL_DAMAGE_MULTIPLIER, -0.25d),
            Items.NETHERITE_INGOT, Map.of(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 0.1d, EntityAttributes.GENERIC_BURNING_TIME, -0.25d, EntityAttributes.GENERIC_ARMOR_TOUGHNESS, 2.0d, ModAttributes.BANE_OF_ADOLESCENCE, 2.0d),
            Items.GOLD_INGOT, Map.of(EntityAttributes.PLAYER_BLOCK_BREAK_SPEED, 0.25d, EntityAttributes.GENERIC_ATTACK_SPEED, 0.6d, EntityAttributes.GENERIC_ATTACK_DAMAGE, -2d),
            Items.EMERALD, Map.of(EntityAttributes.GENERIC_WATER_MOVEMENT_EFFICIENCY, 0.25d, EntityAttributes.PLAYER_SUBMERGED_MINING_SPEED, 0.2d, EntityAttributes.GENERIC_OXYGEN_BONUS, 0.75d),
            Items.AMETHYST_SHARD, Map.of(ModAttributes.PASSIVE_REGENERATION, 1.0d, ModAttributes.APPETITE, -0.15d)
    );

    public static void registerInfusionList(){
        try {
            if (CONFIG.mkdir()) NTRDealsItems.LOGGER.info("Config directory made!");
            if (NTRDEALS_CONFIG.mkdir()) NTRDealsItems.LOGGER.info("NTRDeals Config directory made!");
            if (INFUSION_FILE.createNewFile()){
                for (Item item : ATTRIBUTES.keySet()){
                    makeEntry(item);
                    for (RegistryEntry<EntityAttribute> attribute : ATTRIBUTES.get(item).keySet()){
                        editEntryAdd(item, attribute, ATTRIBUTES.get(item).get(attribute));
                    }
                }
                NTRDealsItems.LOGGER.info("Infusion json made!");
            } load();
        } catch (IOException throwable){
            NTRDealsItems.LOGGER.warn(String.valueOf(throwable));
        }
    }

    public static boolean makeEntry(Item item){
        if (getEntry(item) == null){
            INFUSIONS.add(new InfusionEntry(item.toString(), Map.of()));
            load();
            return true;
        } else if (getEntry(item) instanceof InfusionEntry) {
            return false;
        } else return false;
    }

    public static boolean removeEntry(Item item){
        if (getEntry(item) instanceof InfusionEntry entry){
            INFUSIONS.remove(entry);
            load();
            return true;
        } return false;
    }

    public static boolean editEntryAdd(Item item, RegistryEntry<EntityAttribute> attribute, Double value){
        if (getEntry(item) instanceof InfusionEntry entry && !entry.map.containsKey(attribute.getIdAsString())){
            INFUSIONS.remove(entry);
            entry.map.put(attribute.getIdAsString(), value);
            INFUSIONS.add(entry);
            return true;
        } else return false;
    }

    public static boolean editEntryModify(Item item, RegistryEntry<EntityAttribute> attribute, Double value){
        if (getEntry(item) instanceof InfusionEntry entry && entry.map.containsKey(attribute.getIdAsString())){
            INFUSIONS.remove(entry);
            entry.map.put(attribute.getIdAsString(), value);
            INFUSIONS.add(entry);
            return true;
        } else return false;
    }

    public static boolean editEntryRemove(Item item, RegistryEntry<EntityAttribute> attribute){
        if (getEntry(item) instanceof InfusionEntry entry && entry.map.containsKey(attribute.getIdAsString())){
            INFUSIONS.remove(entry);
            entry.map.remove(attribute.getIdAsString());
            INFUSIONS.add(entry);
            return true;
        } else return false;
    }

    public static boolean containsItem(Item item){
        return getEntry(item) instanceof InfusionEntry;
    }

    public static Map<RegistryEntry<EntityAttribute>, Double> getMap(Item item){
        if (getEntry(item) instanceof InfusionEntry entry){
            Map<RegistryEntry<EntityAttribute>, Double> map = new java.util.HashMap<>(Map.of());
            for (String key : entry.map.keySet()){
                System.out.println(key);
                map.put(Registries.ATTRIBUTE.getEntry(Registries.ATTRIBUTE.get(Identifier.of(key))), entry.map.get(key));
            }
            return map;
        } else return null;
    }

    public static InfusionEntry getEntry(Item item){
        return INFUSIONS.get(item.toString());
    }

    private static void load() {
        try {
            INFUSIONS.load();
        } catch (IOException throwable) {
            NTRDealsItems.LOGGER.warn(String.valueOf(throwable));
        }
    }

    public static void reset() {
        try {
            if (INFUSION_FILE.exists()){
                if (INFUSION_FILE.delete()){
                    NTRDealsItems.LOGGER.info("Infusion json deleted!");
                    if (INFUSION_FILE.createNewFile()){
                        load();
                        for (Item item : ATTRIBUTES.keySet()){
                            makeEntry(item);
                            for (RegistryEntry<EntityAttribute> attribute : ATTRIBUTES.get(item).keySet()){
                                editEntryAdd(item, attribute, ATTRIBUTES.get(item).get(attribute));
                            }
                        }
                        NTRDealsItems.LOGGER.info("Infusion json reset!");
                    }
                } load();
            }
        } catch (IOException throwable){
            NTRDealsItems.LOGGER.warn(String.valueOf(throwable));
        }
    }
}
