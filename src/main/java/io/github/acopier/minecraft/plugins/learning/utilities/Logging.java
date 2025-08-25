package io.github.acopier.minecraft.plugins.learning.utilities;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.Server;

public class Logging {
  public static void surveillance(String text) {
    Server server = Bukkit.getServer();
    server.broadcast(Component.text("[SURVEILLANCE] " + text).color(TextColor.color(0xAAAAAA)), Server.BROADCAST_CHANNEL_ADMINISTRATIVE);
  }
}