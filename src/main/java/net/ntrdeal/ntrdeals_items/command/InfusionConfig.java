package net.ntrdeal.ntrdeals_items.command;

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
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.ntrdeal.ntrdeals_items.config.InfusionEntry;
import net.ntrdeal.ntrdeals_items.config.InfusionUtil;

import java.util.Map;
import java.util.Objects;

public class InfusionConfig {

    public static void register(){
        CommandRegistrationCallback.EVENT.register((dispatcher, access, environment) -> {
            LiteralCommandNode<ServerCommandSource> infusionConfig = CommandManager
                    .literal("infusion_config")
                    .requires(source -> source.hasPermissionLevel(2))
                    .build();

            LiteralCommandNode<ServerCommandSource> add = CommandManager
                    .literal("add")
                    .requires(source -> source.hasPermissionLevel(2))
                    .then(CommandManager.argument("stack", ItemStackArgumentType.itemStack(access))
                            .requires(source -> source.hasPermissionLevel(2))
                            .executes(context -> addFunction(ItemStackArgumentType.getItemStackArgument(context, "stack"), context)))
                    .build();

            LiteralCommandNode<ServerCommandSource> remove = CommandManager
                    .literal("remove")
                    .requires(source -> source.hasPermissionLevel(2))
                    .then(CommandManager.argument("item", ItemStackArgumentType.itemStack(access))
                            .requires(source -> source.hasPermissionLevel(2))
                            .suggests((context, builder) -> CommandSource.suggestMatching(InfusionUtil.INFUSIONS.getNames(), builder))
                            .executes(context -> removeFunction(ItemStackArgumentType.getItemStackArgument(context, "item"), context)))
                    .build();

            LiteralCommandNode<ServerCommandSource> edit = CommandManager
                    .literal("edit")
                    .requires(source -> source.hasPermissionLevel(2))
                    .build();

            CommandNode<ServerCommandSource> item = CommandManager
                    .argument("item", ItemStackArgumentType.itemStack(access))
                    .requires(source -> source.hasPermissionLevel(2))
                    .suggests((context, builder) -> CommandSource.suggestMatching(InfusionUtil.INFUSIONS.getNames(), builder))
                    .build();

            LiteralCommandNode<ServerCommandSource> editRemove = CommandManager
                    .literal("remove")
                    .requires(source -> source.hasPermissionLevel(2))
                    .then(CommandManager.argument("attribute", RegistryEntryReferenceArgumentType.registryEntry(access, RegistryKeys.ATTRIBUTE))
                            .requires(source -> source.hasPermissionLevel(2))
                            .suggests((context, builder) -> CommandSource.suggestMatching(InfusionUtil.INFUSIONS.get(ItemStackArgumentType.getItemStackArgument(context, "item").getItem().toString()).map.keySet(), builder))
                            .executes(context -> editRemoveFunction(ItemStackArgumentType.getItemStackArgument(context, "item"), RegistryEntryReferenceArgumentType.getEntityAttribute(context, "attribute"), context)))
                    .build();

            LiteralCommandNode<ServerCommandSource> editAdd = CommandManager
                    .literal("add")
                    .requires(source -> source.hasPermissionLevel(2))
                    .then(CommandManager.argument("attribute", RegistryEntryReferenceArgumentType.registryEntry(access, RegistryKeys.ATTRIBUTE))
                            .requires(source -> source.hasPermissionLevel(2))
                    .then(CommandManager.argument("double", DoubleArgumentType.doubleArg())
                            .requires(source -> source.hasPermissionLevel(2))
                            .executes(context -> editAddFunction(ItemStackArgumentType.getItemStackArgument(context, "item"), RegistryEntryReferenceArgumentType.getEntityAttribute(context, "attribute"), DoubleArgumentType.getDouble(context, "double"), context))))
                    .build();



            dispatcher.getRoot().addChild(infusionConfig);
            infusionConfig.addChild(add);
            infusionConfig.addChild(remove);
            infusionConfig.addChild(edit);
            edit.addChild(item);
            item.addChild(editRemove);
            item.addChild(editAdd);

        });
    }

    private static int editAddFunction(ItemStackArgument stack, RegistryEntry<EntityAttribute> attribute, Double value, CommandContext<ServerCommandSource> context){
        String item = stack.getItem().toString();
        if (InfusionUtil.INFUSIONS.containsString(item) && !Objects.requireNonNull(InfusionUtil.INFUSIONS.get(item)).map.containsKey(attribute.getIdAsString())){
            InfusionEntry entry = InfusionUtil.INFUSIONS.get(item);
            entry.map.put(attribute.getIdAsString(), value);
            context.getSource().sendFeedback(() -> Text.literal("Successfully added %s to the %s infusion!".formatted(attribute.getIdAsString(), item)).withColor(5635925), false);
            return 1;
        } else {
            context.getSource().sendError(Text.literal("Failed to edit!"));
            return 0;
        }
    }

    private static int editRemoveFunction(ItemStackArgument stack, RegistryEntry<EntityAttribute> attribute, CommandContext<ServerCommandSource> context){
        String item = stack.getItem().toString();
        if (InfusionUtil.INFUSIONS.containsString(item) && Objects.requireNonNull(InfusionUtil.INFUSIONS.get(item)).map.containsKey(attribute.getIdAsString())){
            InfusionEntry entry = InfusionUtil.INFUSIONS.get(item);
            entry.map.remove(attribute.getIdAsString());
            context.getSource().sendFeedback(() -> Text.literal("Successfully removed %s from the %s infusion!".formatted(attribute.getIdAsString(), item)).withColor(5635925), false);
            return 1;
        } else {
            context.getSource().sendError(Text.literal("Failed to edit!"));
            return 0;
        }
    }

    private static int removeFunction(ItemStackArgument stack, CommandContext<ServerCommandSource> context){
        String item = stack.getItem().toString();
        if (InfusionUtil.INFUSIONS.containsString(item)){
            InfusionUtil.INFUSIONS.remove(item);
            context.getSource().sendFeedback(() -> Text.literal("Successfully removed the %s infusion!".formatted(item)).withColor(5635925), false);
            return 1;
        } else {
            context.getSource().sendError(Text.literal("Failed to remove infusion!"));
            return 0;
        }
    }

    private static int addFunction(ItemStackArgument stack, CommandContext<ServerCommandSource> context){
        String item = stack.getItem().toString();
        if (!InfusionUtil.INFUSIONS.containsString(item)){
            InfusionUtil.INFUSIONS.add(new InfusionEntry(item, Map.of()));
            context.getSource().sendFeedback(()-> Text.literal("Successfully added the %s infusion!".formatted(item)).withColor(5635925), false);
            return 1;
        } else {
            context.getSource().sendError(Text.literal("Failed to add infusion!"));
            return 0;
        }
    }

}
