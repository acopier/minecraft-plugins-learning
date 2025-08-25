package io.github.acopier.minecraft.plugins.learning.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class PingCommand {
  public static LiteralArgumentBuilder<CommandSourceStack> createCommand() {
    return Commands.literal("ping").executes(PingCommand::commandLogic);
  }

  public static int commandLogic(CommandContext<CommandSourceStack> ctx) {
    Entity executor = ctx.getSource().getExecutor();

    if (!(executor instanceof Player sender)) {
      return Command.SINGLE_SUCCESS;
    }

    sender.sendMessage("Ping: " + sender.getPing());

    return Command.SINGLE_SUCCESS;
  }
}