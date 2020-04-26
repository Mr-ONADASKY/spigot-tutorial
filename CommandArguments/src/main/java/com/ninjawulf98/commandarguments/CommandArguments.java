package com.ninjawulf98.commandarguments;

import com.ninjawulf98.commandarguments.Commands.Kill;
import com.ninjawulf98.commandarguments.Commands.SendWord;
import org.bukkit.plugin.java.JavaPlugin;

public final class CommandArguments extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("sendWord").setExecutor(new SendWord());
        getCommand("kill").setExecutor(new Kill());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
