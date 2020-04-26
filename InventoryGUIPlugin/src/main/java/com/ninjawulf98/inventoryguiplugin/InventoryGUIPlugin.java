package com.ninjawulf98.inventoryguiplugin;

import org.bukkit.plugin.java.JavaPlugin;

public final class InventoryGUIPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("gui").setExecutor(new GUICommand());
        getServer().getPluginManager().registerEvents(new MenuHandler(), this);
    }
}
