package com.ninjawulf98.tabautocompletion;

import com.ninjawulf98.tabautocompletion.commands.MurderCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class TabAutoCompletion extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("murder").setExecutor(new MurderCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
