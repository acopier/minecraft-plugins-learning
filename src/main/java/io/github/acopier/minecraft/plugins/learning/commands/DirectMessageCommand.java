package io.github.acopier.minecraft.plugins.learning.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.concurrent.CompletableFuture;

public class DirectMessageCommand {
  public static LiteralArgumentBuilder<CommandSourceStack> createCommand() {
    return Commands.literal("dm").then(Commands.argument("receiver", StringArgumentType.word()).suggests(DirectMessageCommand::suggestOnlinePlayers) // ✅ auto-complete
        .then(Commands.argument("message", StringArgumentType.greedyString()).executes(DirectMessageCommand::commandLogic)));
  }

  // Auto-complete for the "receiver" argument
  private static CompletableFuture<Suggestions> suggestOnlinePlayers(CommandContext<CommandSourceStack> ctx, SuggestionsBuilder builder) {
    String prefix = builder.getRemaining().toLowerCase();
    for (Player player : Bukkit.getOnlinePlayers()) {
      if (player.getName().toLowerCase().startsWith(prefix)) {
        builder.suggest(player.getName());
      }
    }
    return builder.buildFuture();
  }

  public static int commandLogic(CommandContext<CommandSourceStack> ctx) {
    String receiverName = StringArgumentType.getString(ctx, "receiver");
    String message = StringArgumentType.getString(ctx, "message");
    Entity executor = ctx.getSource().getExecutor();
    Server server = Bukkit.getServer();

    if (!(executor instanceof Player sender)) {
      ctx.getSource().getSender().sendMessage("Only players can use /dm!");
      return Command.SINGLE_SUCCESS;
    }

    // Case-insensitive search
    Player target = Bukkit.getPlayerExact(receiverName);
    if (target == null) {
      for (Player p : Bukkit.getOnlinePlayers()) {
        if (p.getName().equalsIgnoreCase(receiverName)) {
          target = p;
          break;
        }
      }
    }

    if (target == null) {
      sender.sendMessage("Player not found: " + receiverName);
      return Command.SINGLE_SUCCESS;
    }

    server.broadcast(Component.text("[SURVEILLANCE] " + sender.getName() + " used /dm " + target.getName() + " " + message).color(TextColor.color(0xAAAAAA)), Server.BROADCAST_CHANNEL_ADMINISTRATIVE);
    sender.sendMessage(Component.text("[DM] you -> " + target.getName() + ": " + message));
    target.sendMessage(Component.text("[DM] " + sender.getName() + " -> you: " + message));

    return Command.SINGLE_SUCCESS;
  }
}
