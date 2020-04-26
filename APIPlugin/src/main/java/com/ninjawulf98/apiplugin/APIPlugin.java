package com.ninjawulf98.apiplugin;

import org.bukkit.plugin.java.JavaPlugin;

public final class APIPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new JoinEvent(), this);
    }

}
