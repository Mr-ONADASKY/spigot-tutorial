package com.ninjawulf98.boostpad;

import com.ninjawulf98.boostpad.listeners.FallDamageListener;
import com.ninjawulf98.boostpad.listeners.PlayerMoveListener;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class BoostPad extends JavaPlugin {

    public ArrayList<Player> jumping_players = new ArrayList<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("[BoostPad] Starting Up...");

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        //register listeners
        getServer().getPluginManager().registerEvents(new PlayerMoveListener(this), this);
        getServer().getPluginManager().registerEvents(new FallDamageListener(this), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("[BoostPad] Shutting Up...");
    }
}
