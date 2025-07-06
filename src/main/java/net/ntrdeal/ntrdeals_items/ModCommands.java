package net.ntrdeal.ntrdeals_items;

import com.mojang.brigadier.exceptions.CommandExceptionType;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Colors;
import net.minecraft.util.Formatting;
import net.ntrdeal.ntrdeals_items.variables.Functions;

public class ModCommands {

    private static final SimpleCommandExceptionType FAILED_REFRESH = new SimpleCommandExceptionType(Text.translatable("commands.ntrdeals-items.failed.refresh"));
    private static final DynamicCommandExceptionType SUCCEED_REFRESH = new DynamicCommandExceptionType(
            itemName -> Text.stringifiedTranslatable("commands.ntrdeals-items.succeed.refresh", itemName).withColor(5635925)
    );


    public static void register(){
        NTRDealsItems.LOGGER.info("Registering commands for: "+NTRDealsItems.MOD_ID);

        CommandRegistrationCallback.EVENT.register((dispatcher, access, environment) -> {
            LiteralCommandNode<ServerCommandSource> refreshInfusion = CommandManager
                    .literal("refresh_infusion")
                    .executes(context -> {
                        if (Functions.canInfuse(context.getSource().getPlayer().getMainHandStack()) instanceof ItemStack stack){
                            Functions.infuseStack(stack, stack.get(DataComponentTypes.TRIM).getMaterial().value().ingredient().value());
                            throw SUCCEED_REFRESH.create(stack.getName());
                        } else {
                            throw FAILED_REFRESH.create();
                        }
                    })
                    .build();

            dispatcher.getRoot().addChild(refreshInfusion);
        });
    }
}
