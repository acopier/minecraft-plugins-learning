package io.github.acopier.minecraft.plugins.learning.utilities;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.entity.Player;

public class Checking {
  public static Object getPlayer(CommandContext<CommandSourceStack> ctx, String commandName) {
    if (!(ctx.getSource().getExecutor() instanceof Player sender)) {
      ctx.getSource().getSender().sendMessage(String.format("Only players can use /%s!", commandName));
      return Command.SINGLE_SUCCESS;
    }
    return sender;
  }
}