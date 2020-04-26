package com.ninjawulf98.randomtp;

import com.ninjawulf98.randomtp.commands.RandomTPCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class RandomTP extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("rtp").setExecutor(new RandomTPCommand());

       new TeleportUtils(this);

        getConfig().options().copyDefaults();
        saveDefaultConfig();
    }
}
