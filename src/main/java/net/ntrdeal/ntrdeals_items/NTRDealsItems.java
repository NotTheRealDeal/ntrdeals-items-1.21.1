package net.ntrdeal.ntrdeals_items;

import net.fabricmc.api.ModInitializer;
import net.ntrdeal.ntrdeals_items.attribute.ModAttributes;
import net.ntrdeal.ntrdeals_items.block.ModBlocks;
import net.ntrdeal.ntrdeals_items.command.CommandRegister;
import net.ntrdeal.ntrdeals_items.config.InfusionMaterialUtil;
import net.ntrdeal.ntrdeals_items.config.InfusionUtil;
import net.ntrdeal.ntrdeals_items.item.ModItemGroups;
import net.ntrdeal.ntrdeals_items.item.ModItems;
import net.ntrdeal.ntrdeals_items.variables.ModComponents;
import net.ntrdeal.ntrdeals_items.world.gen.ModWorldGeneration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NTRDealsItems implements ModInitializer {
	public static final String MOD_ID = "ntrdeals-items";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Registering mod: "+MOD_ID);
        InfusionUtil.registerInfusionList();
		InfusionMaterialUtil.registerInfusionMaterialList();
		ModAttributes.registerAttributes();
        ModComponents.registerComponents();
		CommandRegister.register();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModItemGroups.registerItemGroups();
		ModWorldGeneration.generateModWorldGen();
	}
}