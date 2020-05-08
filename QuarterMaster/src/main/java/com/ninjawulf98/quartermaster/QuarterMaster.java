package com.ninjawulf98.quartermaster;

import com.ninjawulf98.quartermaster.commands.LockCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class QuarterMaster extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("lock").setExecutor(new LockCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
