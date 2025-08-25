package io.github.acopier.minecraft.plugins.learning;

import io.github.acopier.minecraft.plugins.learning.commands.DirectMessageCommand;
import io.github.acopier.minecraft.plugins.learning.commands.PingCommand;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.bukkit.plugin.java.JavaPlugin;

public final class Learning extends JavaPlugin {

  @Override
  public void onEnable() {
    this.getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, commands -> {
      commands.registrar().register(DirectMessageCommand.createCommand().build());
      commands.registrar().register(PingCommand.createCommand().build());
    });

  }

  @Override
  public void onDisable() {
    // Plugin shutdown logic
  }
}
