package com.ninjawulf98.commandspart2;

import com.ninjawulf98.commandspart2.commands.Feed;
import com.ninjawulf98.commandspart2.commands.God;
import com.ninjawulf98.commandspart2.events.PlayerMove;
import org.bukkit.plugin.java.JavaPlugin;

public final class CommandsPart2 extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("god").setExecutor(new God());
        getCommand("feed").setExecutor(new Feed());
        getServer().getPluginManager().registerEvents(new PlayerMove(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
