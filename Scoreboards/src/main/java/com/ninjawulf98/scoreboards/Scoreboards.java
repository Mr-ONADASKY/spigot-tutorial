package com.ninjawulf98.scoreboards;

import com.ninjawulf98.scoreboards.commands.OpenScoreboardCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class Scoreboards extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("sb").setExecutor(new OpenScoreboardCommand());
    }
}
