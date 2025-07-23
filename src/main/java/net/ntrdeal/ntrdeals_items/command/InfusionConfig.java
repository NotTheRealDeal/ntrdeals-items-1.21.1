package net.ntrdeal.ntrdeals_items.command;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.tree.CommandNode;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.command.CommandSource;
import net.minecraft.command.argument.ItemStackArgument;
import net.minecraft.command.argument.ItemStackArgumentType;
import net.minecraft.command.argument.RegistryEntryReferenceArgumentType;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.ntrdeal.ntrdeals_items.config.InfusionMaterialUtil;
import net.ntrdeal.ntrdeals_items.config.InfusionUtil;

public class InfusionConfig {

    public static void register(){
        CommandRegistrationCallback.EVENT.register((dispatcher, access, environment) -> {
            LiteralCommandNode<ServerCommandSource> infusionConfig = CommandManager
                    .literal("infusion_config")
                    .requires(source -> source.hasPermissionLevel(2))
                    .build();

            LiteralCommandNode<ServerCommandSource> material = CommandManager
                    .literal("material")
                    .requires(source -> source.hasPermissionLevel(2))
                    .build();

            LiteralCommandNode<ServerCommandSource> entry = CommandManager
                    .literal("entry")
                    .requires(source -> source.hasPermissionLevel(2))
                    .build();

            LiteralCommandNode<ServerCommandSource> entryAdd = CommandManager
                    .literal("entryAdd")
                    .requires(source -> source.hasPermissionLevel(2))
                    .then(CommandManager.argument("stack", ItemStackArgumentType.itemStack(access))
                            .requires(source -> source.hasPermissionLevel(2))
                            .executes(context -> addFunction(ItemStackArgumentType.getItemStackArgument(context, "stack"), context)))
                    .build();

            LiteralCommandNode<ServerCommandSource> entryRemove = CommandManager
                    .literal("remove")
                    .requires(source -> source.hasPermissionLevel(2))
                    .then(CommandManager.argument("item", ItemStackArgumentType.itemStack(access))
                            .requires(source -> source.hasPermissionLevel(2))
                            .suggests((context, builder) -> CommandSource.suggestMatching(InfusionUtil.INFUSIONS.getNames(), builder))
                            .executes(context -> removeFunction(ItemStackArgumentType.getItemStackArgument(context, "item"), context)))
                    .build();

            LiteralCommandNode<ServerCommandSource> entryEdit = CommandManager
                    .literal("edit")
                    .requires(source -> source.hasPermissionLevel(2))
                    .build();

            CommandNode<ServerCommandSource> entryItem = CommandManager
                    .argument("item", ItemStackArgumentType.itemStack(access))
                    .requires(source -> source.hasPermissionLevel(2))
                    .suggests((context, builder) -> CommandSource.suggestMatching(InfusionUtil.INFUSIONS.getNames(), builder))
                    .build();

            LiteralCommandNode<ServerCommandSource> entryEditRemove = CommandManager
                    .literal("remove")
                    .requires(source -> source.hasPermissionLevel(2))
                    .then(CommandManager.argument("attribute", RegistryEntryReferenceArgumentType.registryEntry(access, RegistryKeys.ATTRIBUTE))
                            .requires(source -> source.hasPermissionLevel(2))
                            .suggests((context, builder) -> CommandSource.suggestMatching(InfusionUtil.getEntry(ItemStackArgumentType.getItemStackArgument(context, "item").getItem()).map.keySet(), builder))
                            .executes(context -> editRemoveFunction(ItemStackArgumentType.getItemStackArgument(context, "item"), RegistryEntryReferenceArgumentType.getEntityAttribute(context, "attribute"), context)))
                    .build();

            LiteralCommandNode<ServerCommandSource> entryEditAdd = CommandManager
                    .literal("add")
                    .requires(source -> source.hasPermissionLevel(2))
                    .then(CommandManager.argument("attribute", RegistryEntryReferenceArgumentType.registryEntry(access, RegistryKeys.ATTRIBUTE))
                            .requires(source -> source.hasPermissionLevel(2))
                    .then(CommandManager.argument("double", DoubleArgumentType.doubleArg())
                            .requires(source -> source.hasPermissionLevel(2))
                            .executes(context -> editAddFunction(ItemStackArgumentType.getItemStackArgument(context, "item"), RegistryEntryReferenceArgumentType.getEntityAttribute(context, "attribute"), DoubleArgumentType.getDouble(context, "double"), context))))
                    .build();

            LiteralCommandNode<ServerCommandSource> entryEditModify = CommandManager
                    .literal("modify")
                    .requires(source -> source.hasPermissionLevel(2))
                    .then(CommandManager.argument("attribute", RegistryEntryReferenceArgumentType.registryEntry(access, RegistryKeys.ATTRIBUTE))
                            .suggests((context, builder) -> CommandSource.suggestMatching(InfusionUtil.getEntry(ItemStackArgumentType.getItemStackArgument(context, "item").getItem()).map.keySet(), builder))
                    .then(CommandManager.argument("double", DoubleArgumentType.doubleArg())
                            .requires(source -> source.hasPermissionLevel(2))
                            .executes(context -> editModifyFunction(ItemStackArgumentType.getItemStackArgument(context, "item"), RegistryEntryReferenceArgumentType.getEntityAttribute(context, "attribute"), DoubleArgumentType.getDouble(context, "double"), context))))
                    .build();

            LiteralCommandNode<ServerCommandSource> entryGet = CommandManager
                    .literal("get")
                    .requires(source -> source.hasPermissionLevel(2))
                    .then(entryItem.createBuilder().executes(context -> getFunction(ItemStackArgumentType.getItemStackArgument(context, "item"), context)))
                    .build();

            LiteralCommandNode<ServerCommandSource> reset = CommandManager
                    .literal("reset")
                    .requires(source -> source.hasPermissionLevel(2))
                    .then(CommandManager.argument("bool", BoolArgumentType.bool())
                            .executes(context -> resetFunction(BoolArgumentType.getBool(context, "bool"), context)))
                    .build();

            LiteralCommandNode<ServerCommandSource> materialAdd = CommandManager
                    .literal("add")
                    .requires(source -> source.hasPermissionLevel(2))
                    .then(CommandManager.argument("material", RegistryEntryReferenceArgumentType.registryEntry(access, RegistryKeys.ARMOR_MATERIAL))
                            .requires(source -> source.hasPermissionLevel(2))
                            .executes(context -> addMaterialFunction(RegistryEntryReferenceArgumentType.getRegistryEntry(context, "material", RegistryKeys.ARMOR_MATERIAL), context)))
                    .build();

            LiteralCommandNode<ServerCommandSource> materialRemove = CommandManager
                    .literal("remove")
                    .requires(source -> source.hasPermissionLevel(2))
                    .then(CommandManager.argument("material", RegistryEntryReferenceArgumentType.registryEntry(access, RegistryKeys.ARMOR_MATERIAL))
                            .suggests((context, builder) -> CommandSource.suggestMatching(InfusionMaterialUtil.INFUSION_MATERIALS.getNames(), builder))
                            .requires(source -> source.hasPermissionLevel(2))
                            .executes(context -> removeMaterialFunction(RegistryEntryReferenceArgumentType.getRegistryEntry(context, "material", RegistryKeys.ARMOR_MATERIAL), context)))
                    .build();





            dispatcher.getRoot().addChild(infusionConfig);
            infusionConfig.addChild(material);
            infusionConfig.addChild(entry);
            infusionConfig.addChild(reset);
            material.addChild(materialAdd);
            material.addChild(materialRemove);
            entry.addChild(entryAdd);
            entry.addChild(entryRemove);
            entry.addChild(entryEdit);
            entry.addChild(entryGet);
            entryEdit.addChild(entryItem);
            entryItem.addChild(entryEditRemove);
            entryItem.addChild(entryEditAdd);
            entryItem.addChild(entryEditModify);

        });
    }

    private static int editAddFunction(ItemStackArgument stack, RegistryEntry<EntityAttribute> attribute, Double value, CommandContext<ServerCommandSource> context){
        Item item = stack.getItem();
        if (InfusionUtil.editEntryAdd(item, attribute, value)){
            context.getSource().sendFeedback(() -> Text.literal("Successfully added %s to the %s infusion!".formatted(attribute.getIdAsString(), item.toString())).withColor(5635925), false);
            return 1;
        } else {
            context.getSource().sendError(Text.literal("Failed to edit!"));
            return 0;
        }
    }

    private static int editRemoveFunction(ItemStackArgument stack, RegistryEntry<EntityAttribute> attribute, CommandContext<ServerCommandSource> context){
        Item item = stack.getItem();
        if (InfusionUtil.editEntryRemove(item, attribute)){
            context.getSource().sendFeedback(() -> Text.literal("Successfully removed %s from the %s infusion!".formatted(attribute.getIdAsString(), item.toString())).withColor(5635925), false);
            return 1;
        } else {
            context.getSource().sendError(Text.literal("Failed to edit!"));
            return 0;
        }
    }

    private static int editModifyFunction(ItemStackArgument stack, RegistryEntry<EntityAttribute> attribute, Double value, CommandContext<ServerCommandSource> context){
        Item item = stack.getItem();
        if (InfusionUtil.editEntryModify(item, attribute, value)){
            context.getSource().sendFeedback(() -> Text.literal("Successfully modified the value for %s!".formatted(attribute.getIdAsString())).withColor(5635925), false);
            return 1;
        } else {
            context.getSource().sendError(Text.literal("Failed to edit!"));
            return 0;
        }
    }

    private static int removeFunction(ItemStackArgument stack, CommandContext<ServerCommandSource> context){
        Item item = stack.getItem();
        if (InfusionUtil.removeEntry(item)){
            context.getSource().sendFeedback(() -> Text.literal("Successfully removed the %s infusion!".formatted(item.toString())).withColor(5635925), false);
            return 1;
        } else {
            context.getSource().sendError(Text.literal("Failed to remove infusion!"));
            return 0;
        }
    }

    private static int addFunction(ItemStackArgument stack, CommandContext<ServerCommandSource> context){
        Item item = stack.getItem();
        if (InfusionUtil.makeEntry(item)){
            context.getSource().sendFeedback(()-> Text.literal("Successfully added the %s infusion!".formatted(item.toString())).withColor(5635925), false);
            return 1;
        } else {
            context.getSource().sendError(Text.literal("Failed to add infusion!"));
            return 0;
        }
    }

    private static int getFunction(ItemStackArgument stack, CommandContext<ServerCommandSource> context){
        Item item = stack.getItem();
        if (InfusionUtil.containsItem(item)){
            context.getSource().sendFeedback(() -> Text.literal("%s has the following infusion: %s".formatted(item.toString(), InfusionUtil.getEntry(item).map)), false);
            return 1;
        } else {
            context.getSource().sendError(Text.literal("Could not get!"));
            return 0;
        }
    }

    private static int resetFunction(boolean bool, CommandContext<ServerCommandSource> context){
        if (bool){
            context.getSource().sendFeedback(() -> Text.literal("Reset complete!").withColor(5635925), false);
            InfusionUtil.reset();
            InfusionMaterialUtil.reset();
            return 1;
        } else {
            context.getSource().sendError(Text.literal("Reset incomplete!"));
            return 0;
        }
    }

    private static int addMaterialFunction(RegistryEntry<ArmorMaterial> material, CommandContext<ServerCommandSource> context){
        if (InfusionMaterialUtil.makeEntry(material)){
            context.getSource().sendFeedback(() -> Text.literal("Successfully added the %s material!".formatted(material.getIdAsString())).withColor(5635925), false);
            return 1;
        } else {
            context.getSource().sendError(Text.literal("Failed to add material!"));
            return 0;
        }
    }

    private static int removeMaterialFunction(RegistryEntry<ArmorMaterial> material, CommandContext<ServerCommandSource> context){
        if (InfusionMaterialUtil.removeEntry(material)){
            context.getSource().sendFeedback(() -> Text.literal("Successfully removed the %s material!".formatted(material.getIdAsString())).withColor(5635925), false);
            return 1;
        } else {
            context.getSource().sendError(Text.literal("Failed to remove material!"));
            return 0;
        }
    }
}
