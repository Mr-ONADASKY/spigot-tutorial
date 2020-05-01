package com.ninjawulf98.cortexlobby;

import com.ninjawulf98.cortexlobby.listeners.GoodWeatherListener;
import com.ninjawulf98.cortexlobby.listeners.PlayerJoinListener;
import com.ninjawulf98.cortexlobby.tasks.KeepDayTask;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

public final class CortexLobby extends JavaPlugin {


    @Override
    public void onEnable() {
        System.out.println("CORTEX LOBBY HAS STARTED UP!");

        //load config
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        //Register listeners
        getServer().getPluginManager().registerEvents(new GoodWeatherListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);

        BukkitTask keepDayTask = new KeepDayTask(this).runTaskTimer(this, 0L, 100L);
    }
}
