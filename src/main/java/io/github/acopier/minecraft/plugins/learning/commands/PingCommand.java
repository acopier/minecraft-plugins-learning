package io.github.acopier.minecraft.plugins.learning.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class PingCommand {
  public static LiteralArgumentBuilder<CommandSourceStack> createCommand() {
    return Commands.literal("ping").executes(PingCommand::commandLogic);
  }

  public static int commandLogic(CommandContext<CommandSourceStack> ctx) {
    Entity executor = ctx.getSource().getExecutor();
    Server server = Bukkit.getServer();

    if (!(executor instanceof Player sender)) {
      ctx.getSource().getSender().sendMessage("Only players can use /ping!");
      return Command.SINGLE_SUCCESS;
    }
    server.broadcast(Component.text("[SURVEILLANCE] " + sender.getName() + " used /ping").color(TextColor.color(0xAAAAAA)), Server.BROADCAST_CHANNEL_ADMINISTRATIVE);
    sender.sendMessage("Ping: " + sender.getPing());

    return Command.SINGLE_SUCCESS;
  }
}