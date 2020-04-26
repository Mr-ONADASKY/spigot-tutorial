package com.ninjawulf98.guimenus;

import com.ninjawulf98.guimenus.commands.GUICommand;
import com.ninjawulf98.guimenus.events.ClickEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class GUIMenus extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("gui").setExecutor(new GUICommand());

        getServer().getPluginManager().registerEvents(new ClickEvent(), this);
    }

}
