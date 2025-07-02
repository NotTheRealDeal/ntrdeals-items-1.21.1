package net.ntrdeal.ntrdeals_items.item;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.ntrdeal.ntrdeals_items.NTRDealsItems;

public class ModItems {

    public static final Item LUNARITE = registerItem("lunarite", new Item(new Item.Settings()));
    public static final Item COSMOLITE = registerItem("cosmolite", new Item(new Item.Settings().fireproof().rarity(Rarity.EPIC)));

    public static final Item LUNARITE_INGOT = registerItem("lunarite_ingot", new Item(new Item.Settings()));
    public static final Item COSMOLITE_INGOT = registerItem("cosmolite_ingot", new Item(new Item.Settings().fireproof().rarity(Rarity.EPIC)));

    public static final Item LUNARITE_HELMET = registerItem("lunarite_helmet", new ArmorItem(ModArmorMaterials.LUNARITE, ArmorItem.Type.HELMET, new Item.Settings()
            .maxDamage(ArmorItem.Type.HELMET.getMaxDamage(15))));
    public static final Item LUNARITE_CHESTPLATE = registerItem("lunarite_chestplate", new ArmorItem(ModArmorMaterials.LUNARITE, ArmorItem.Type.CHESTPLATE, new Item.Settings()
            .maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(15))));
    public static final Item LUNARITE_LEGGINGS = registerItem("lunarite_leggings", new ArmorItem(ModArmorMaterials.LUNARITE, ArmorItem.Type.LEGGINGS, new Item.Settings()
            .maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(15))));
    public static final Item LUNARITE_BOOTS = registerItem("lunarite_boots", new ArmorItem(ModArmorMaterials.LUNARITE, ArmorItem.Type.BOOTS, new Item.Settings()
            .maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(15))));

    public static final Item COSMOLITE_HELMET = registerItem("cosmolite_helmet", new ArmorItem(ModArmorMaterials.COSMOLITE, ArmorItem.Type.HELMET, new Item.Settings()
            .maxDamage(ArmorItem.Type.HELMET.getMaxDamage(15)).rarity(Rarity.EPIC)));
    public static final Item COSMOLITE_CHESTPLATE = registerItem("cosmolite_chestplate", new ArmorItem(ModArmorMaterials.COSMOLITE, ArmorItem.Type.CHESTPLATE, new Item.Settings()
            .maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(15)).rarity(Rarity.EPIC)));
    public static final Item COSMOLITE_LEGGINGS = registerItem("cosmolite_leggings", new ArmorItem(ModArmorMaterials.COSMOLITE, ArmorItem.Type.LEGGINGS, new Item.Settings()
            .maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(15)).rarity(Rarity.EPIC)));
    public static final Item COSMOLITE_BOOTS = registerItem("cosmolite_boots", new ArmorItem(ModArmorMaterials.COSMOLITE, ArmorItem.Type.BOOTS, new Item.Settings()
            .maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(15)).rarity(Rarity.EPIC)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(NTRDealsItems.MOD_ID, name), item);
    }

    public static void registerModItems(){
        NTRDealsItems.LOGGER.info("Registering Mod Items for "+NTRDealsItems.MOD_ID);
    }
}
