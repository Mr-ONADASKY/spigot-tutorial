package com.ninjawulf98.holograms;

import org.bukkit.plugin.java.JavaPlugin;

public final class Holograms extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("hologram").setExecutor(new HologramCommand());
    }
}
