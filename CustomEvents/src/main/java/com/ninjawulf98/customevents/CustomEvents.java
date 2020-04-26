package com.ninjawulf98.customevents;

import com.ninjawulf98.customevents.listeners.GameListeners;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class CustomEvents extends JavaPlugin {
	
	@Override
	public void onEnable () {
		// Plugin startup logic
		Bukkit.getServer().getPluginManager().registerEvents(new GameListeners(), this);
		getCommand("gameover").setExecutor(new GameOverCommand());
	}
}
