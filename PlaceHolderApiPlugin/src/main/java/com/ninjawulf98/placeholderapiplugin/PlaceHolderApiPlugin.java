package com.ninjawulf98.placeholderapiplugin;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class PlaceHolderApiPlugin extends JavaPlugin {

    ArrayList<Player> vanished = new ArrayList<>();
    private static PlaceHolderApiPlugin instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        getCommand("test").setExecutor(new TestCommand());

        new SpigotExpansion().register();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        instance = null;
    }

    public static PlaceHolderApiPlugin getInstance(){
        return instance;
    }
}
