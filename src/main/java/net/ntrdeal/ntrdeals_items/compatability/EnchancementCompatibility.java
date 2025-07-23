package net.ntrdeal.ntrdeals_items.compatability;

import moriyashiine.enchancement.common.init.ModDamageTypes;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.damage.DamageSource;

public class EnchancementCompatibility {
    public static boolean ENCHANCEMENT = FabricLoader.getInstance().isModLoaded("enchancement");

    public static boolean isBrimstone(DamageSource source){
        if (ENCHANCEMENT){
            return source.isOf(ModDamageTypes.BRIMSTONE);
        } else return false;
    }
}
