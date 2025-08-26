package io.github.acopier.minecraft.plugins.learning.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import io.github.acopier.minecraft.plugins.learning.utilities.CheckUtility;
import io.github.acopier.minecraft.plugins.learning.utilities.LogUtility;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import org.bukkit.entity.Player;

public class PingCommand {
  static String commandName = "ping";

  public static LiteralArgumentBuilder<CommandSourceStack> createCommand() {
    return Commands.literal(commandName).executes(PingCommand::commandLogic);
  }

  public static int commandLogic(CommandContext<CommandSourceStack> ctx) {
    Player sender = (Player) CheckUtility.getPlayer(ctx, commandName);

    LogUtility.surveillance(String.format("%s used /%s", sender.getName(), commandName));
    sender.sendMessage("Ping: " + sender.getPing());

    return Command.SINGLE_SUCCESS;
  }
}