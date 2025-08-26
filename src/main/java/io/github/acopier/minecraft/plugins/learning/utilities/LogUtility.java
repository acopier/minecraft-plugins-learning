package io.github.acopier.minecraft.plugins.learning.utilities;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.Server;

public class LogUtility {
  public static void surveillance(String text) {
    Server server = Bukkit.getServer();
    server.broadcast(Component.text(String.format("[SURVEILLANCE] | %s", text)).color(TextColor.color(0xAAAAAA)), Server.BROADCAST_CHANNEL_ADMINISTRATIVE);
  }
}