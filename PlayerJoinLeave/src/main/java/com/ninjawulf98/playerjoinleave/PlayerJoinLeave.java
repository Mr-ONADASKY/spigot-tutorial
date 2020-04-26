package com.ninjawulf98.playerjoinleave;

import com.ninjawulf98.playerjoinleave.events.PlayerJoin;
import com.ninjawulf98.playerjoinleave.events.PlayerLeave;
import org.bukkit.plugin.java.JavaPlugin;

public final class PlayerJoinLeave extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        getServer().getPluginManager().registerEvents(new PlayerLeave(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
