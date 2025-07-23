package net.ntrdeal.ntrdeals_items.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.block.MapColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.ntrdeal.ntrdeals_items.NTRDealsItems;

public class ModBlocks {

    public static final Block LUNARITE_ORE = registerBlock("lunarite_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(5,7), AbstractBlock.Settings.create()
                    .strength(3.0F, 9.0F)
                    .requiresTool()
                    .mapColor(MapColor.PALE_YELLOW)), true);

    public static final Block COSMOLITE_ORE = registerBlock("cosmolite_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(7,9), AbstractBlock.Settings.create()
                    .strength(50.0F, 1200.0F)
                    .requiresTool()
                    .mapColor(MapColor.BLACK)),
                new Item.Settings().fireproof().rarity(Rarity.EPIC));

    public static final Block RAW_LUNARITE_BLOCK = registerBlock("raw_lunarite_block",
            new Block(AbstractBlock.Settings.create()
                    .strength(3.0F, 9.0F)
                    .requiresTool()
                    .mapColor(MapColor.PALE_YELLOW)), true);

    public static final Block RAW_COSMOLITE_BLOCK = registerBlock("raw_cosmolite_block",
            new Block(AbstractBlock.Settings.create()
                    .strength(50.0F, 1200.0F)
                    .requiresTool()
                    .mapColor(MapColor.BLUE)),
                new Item.Settings().fireproof().rarity(Rarity.EPIC));

    public static final Block DRIED_CHORUS_FLOWER = registerBlock("dried_chorus_flower",
            new Block(AbstractBlock.Settings.create()
                    .requiresTool()
                    .strength(2.0F, 6.0F)
                    .mapColor(MapColor.GRAY)
                    .nonOpaque()), true);

    /*public static final Block LUNARITE_BLOCK = registerBlock("lunarite_block",
            new Block(AbstractBlock.Settings.create().strength(5.0F, 6.0F)), true);

    public static final Block COSMOLITE_BLOCK = registerBlock("cosmolite_block",
            new Block(AbstractBlock.Settings.create().strength(50.0F, 1200.0F)), new Item.Settings().rarity(Rarity.EPIC));*/

    private static Block registerBlock(String name, Block block, Boolean blockItem){
        if (blockItem){
            registerBlockItem(name, block);
            return Registry.register(Registries.BLOCK, Identifier.of(NTRDealsItems.MOD_ID, name), block);
        } else return Registry.register(Registries.BLOCK, Identifier.of(NTRDealsItems.MOD_ID, name), block);
    }

    private static Block registerBlock(String name, Block block, Item.Settings settings){
        registerBlockItem(name, block, settings);
        return Registry.register(Registries.BLOCK, Identifier.of(NTRDealsItems.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block){
        Registry.register(Registries.ITEM, Identifier.of(NTRDealsItems.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    private static void registerBlockItem(String name, Block block, Item.Settings settings){
        Registry.register(Registries.ITEM, Identifier.of(NTRDealsItems.MOD_ID, name),
                new BlockItem(block, settings));
    }

    public static void registerModBlocks(){
        NTRDealsItems.LOGGER.info("Registering ModBlocks For: "+NTRDealsItems.MOD_ID);
    }
}
