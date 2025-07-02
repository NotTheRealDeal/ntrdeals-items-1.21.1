package net.ntrdeal.ntrdeals_items.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.ntrdeal.ntrdeals_items.NTRDealsItems;
import net.ntrdeal.ntrdeals_items.block.ModBlocks;

public class ModItemGroups {
    public static final ItemGroup NTRDEALS_ITEMS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(NTRDealsItems.MOD_ID, "ntrdeals_items_items"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.COSMOLITE)).displayName(Text.translatable("itemgroup.ntrdeals_items.ntrdeal_items_items"))
                    .entries((displayContext, entries) ->{
                        entries.add(ModItems.LUNARITE);
                        entries.add(ModItems.COSMOLITE);
                        entries.add(ModItems.LUNARITE_INGOT);
                        entries.add(ModItems.COSMOLITE_INGOT);
                        entries.add(ModItems.LUNARITE_HELMET);
                        entries.add(ModItems.LUNARITE_CHESTPLATE);
                        entries.add(ModItems.LUNARITE_LEGGINGS);
                        entries.add(ModItems.LUNARITE_BOOTS);
                        entries.add(ModItems.COSMOLITE_HELMET);
                        entries.add(ModItems.COSMOLITE_CHESTPLATE);
                        entries.add(ModItems.COSMOLITE_LEGGINGS);
                        entries.add(ModItems.COSMOLITE_BOOTS);
                        entries.add(ModBlocks.LUNARITE_ORE);
                        entries.add(ModBlocks.COSMOLITE_ORE);
                        /*entries.add(ModBlocks.LUNARITE_BLOCK);
                        entries.add(ModBlocks.COSMOLITE_BLOCK);*/
                            })
                    .build());

    public static void registerItemGroups(){
        NTRDealsItems.LOGGER.info("Registering Item Groups for "+NTRDealsItems.MOD_ID);
    }
}
