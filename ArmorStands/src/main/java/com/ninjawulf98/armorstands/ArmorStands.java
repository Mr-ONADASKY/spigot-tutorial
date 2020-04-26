package com.ninjawulf98.armorstands;

import org.bukkit.plugin.java.JavaPlugin;

public final class ArmorStands extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("armorstand").setExecutor(new ArmorStandCommand());
    }
}
