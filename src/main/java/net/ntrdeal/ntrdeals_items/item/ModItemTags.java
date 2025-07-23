package net.ntrdeal.ntrdeals_items.item;

import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.ntrdeal.ntrdeals_items.NTRDealsItems;

public class ModItemTags {
    public static final TagKey<Item> COSMOLITE_ARMOR = register("cosmolite_armor");

    private static TagKey<Item> register(String id) {
        return TagKey.of(RegistryKeys.ITEM, Identifier.of(NTRDealsItems.MOD_ID, id));
    }
}
