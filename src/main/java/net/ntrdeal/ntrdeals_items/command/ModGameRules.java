package net.ntrdeal.ntrdeals_items.command;

import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.GameRules;
import net.ntrdeal.ntrdeals_items.NTRDealsItems;

public class ModGameRules {

    public static final GameRules.Key<GameRules.IntRule> COMBAT_TICKS = GameRuleRegistry.register("combat_ticks", GameRules.Category.UPDATES, GameRuleFactory.createIntRule(100));
    public static final GameRules.Key<GameRules.IntRule> REGEN_TICKS = GameRuleRegistry.register("regen_ticks", GameRules.Category.UPDATES, GameRuleFactory.createIntRule(80, 1));


    public static void register(){
        NTRDealsItems.LOGGER.info("Registering game rules!");
    }
}
