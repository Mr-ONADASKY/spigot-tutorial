package com.ninjawulf98.commandmanager;

import com.ninjawulf98.commandmanager.commands.PrankManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class CommandManager extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("prank").setExecutor(new PrankManager());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
