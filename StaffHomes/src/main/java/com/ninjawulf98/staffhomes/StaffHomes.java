package com.ninjawulf98.staffhomes;

import org.bukkit.plugin.java.JavaPlugin;

public final class StaffHomes extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        getCommand("staffhome").setExecutor(new StaffHomeCommand(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
