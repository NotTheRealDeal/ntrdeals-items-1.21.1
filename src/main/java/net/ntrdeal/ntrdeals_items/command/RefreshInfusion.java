package net.ntrdeal.ntrdeals_items.command;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.tree.ArgumentCommandNode;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.command.CommandSource;
import net.minecraft.command.EntitySelector;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.ntrdeal.ntrdeals_items.variables.Functions;

import java.util.List;
import java.util.Objects;

public class RefreshInfusion {
    public static void register(){
        CommandRegistrationCallback.EVENT.register((dispatcher, access, environment) -> {
            LiteralCommandNode<ServerCommandSource> refreshInfusion = CommandManager.literal("refresh_infusion")
                    .build();

            ArgumentCommandNode<ServerCommandSource, String> refreshType = CommandManager
                    .argument("type", StringArgumentType.string())
                    .suggests((context, builder) -> CommandSource.suggestMatching(List.of("full", "armor", "single"), builder))
                    .executes(context -> refresh(context, false))
                    .build();

            ArgumentCommandNode<ServerCommandSource, EntitySelector> playerArgument = CommandManager
                    .argument("player", EntityArgumentType.player())
                    .requires(source -> source.hasPermissionLevel(2))
                    .executes(context -> refresh(context, true))
                    .build();

            dispatcher.getRoot().addChild(refreshInfusion);
            refreshInfusion.addChild(refreshType);
            refreshType.addChild(playerArgument);
        });
    }

    private static int refresh(CommandContext<ServerCommandSource> context, Boolean selector) throws CommandSyntaxException {
            String type = StringArgumentType.getString(context, "type");
            ServerPlayerEntity player = selector ? EntityArgumentType.getPlayer(context, "player") : context.getSource().getPlayer();
        switch (type) {
            case "full" -> {
                List<String> refreshedItems = new java.util.ArrayList<>(List.of());
                for (int index = 0; index <= Objects.requireNonNull(player).getInventory().size(); index++) {
                    if (Functions.refreshInfusion(player.getInventory().getStack(index)) instanceof ItemStack stack){
                        refreshedItems.add(stack.getItem().toString());
                    }
                }
                if (!refreshedItems.isEmpty()) {
                    context.getSource().sendFeedback(() -> Text.literal("Successfully refreshed items: %s!".formatted(refreshedItems)).withColor(5635925), false);
                    return 1;
                } else {
                    context.getSource().sendError(Text.literal("Could not find any item's to refresh!"));
                    return 0;
                }
            }
            case "armor" -> {
                List<String> refreshedItems = new java.util.ArrayList<>(List.of());
                for (ItemStack stack : Objects.requireNonNull(player).getAllArmorItems()) {
                    if (Functions.refreshInfusion(stack) instanceof ItemStack){
                        refreshedItems.add(stack.getItem().toString());
                    }
                }
                if (!refreshedItems.isEmpty()) {
                    context.getSource().sendFeedback(() -> Text.literal("Successfully refreshed %s!".formatted(refreshedItems)).withColor(5635925), false);
                    return 1;
                } else {
                    context.getSource().sendError(Text.literal("Could not refresh any armor!"));
                    return 0;
                }
            }
            case "single" -> {
                if (Functions.refreshInfusion(Objects.requireNonNull(player).getMainHandStack()) instanceof ItemStack stack){
                    context.getSource().sendFeedback(() -> Text.literal("Successfully refreshed %s!".formatted(stack.getItem().toString())).withColor(5635925), false);
                    return 1;
                } else {
                    context.getSource().sendError(Text.literal("Could not refresh held item!"));
                    return 0;
                }
            }
            case null, default -> {
                context.getSource().sendError(Text.literal("Error with command type!"));
                return 0;
            }
        }
    }
}
