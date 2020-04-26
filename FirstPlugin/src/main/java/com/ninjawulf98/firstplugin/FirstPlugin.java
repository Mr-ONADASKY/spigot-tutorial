package com.ninjawulf98.firstplugin;

import org.bukkit.plugin.java.JavaPlugin;

public final class FirstPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Initializing ninjawulf98's first plugin");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Exiting ninjawulf98's first plugin");
    }
}
