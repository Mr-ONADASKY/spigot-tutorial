package com.ninjawulf98.flyplugin;

import com.ninjawulf98.flyplugin.commands.FlyCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class FlyPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("fly").setExecutor(new FlyCommand(this));
        getConfig().options().copyDefaults();
        saveDefaultConfig();
    }
    
}
