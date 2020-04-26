package com.ninjawulf98.entities;

import org.bukkit.plugin.java.JavaPlugin;

public final class Entities extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new MoveEvent(), this);
    }
}
