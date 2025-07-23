package net.ntrdeal.ntrdeals_items.block;

import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.ntrdeal.ntrdeals_items.NTRDealsItems;

public class ModBlockTags {

    public static final TagKey<Block> GEODE_CANNOT_REPLACE = register("geode_cannot_replace");

    private static TagKey<Block> register(String id) {
        return TagKey.of(RegistryKeys.BLOCK, Identifier.of(NTRDealsItems.MOD_ID, id));
    }
}
