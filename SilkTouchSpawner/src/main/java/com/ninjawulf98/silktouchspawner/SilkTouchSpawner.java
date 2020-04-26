package com.ninjawulf98.silktouchspawner;

import com.ninjawulf98.silktouchspawner.listeners.BlockBreakListener;
import com.ninjawulf98.silktouchspawner.listeners.SpawnerBreakListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class SilkTouchSpawner extends JavaPlugin {
	
	@Override
	public void onEnable () {
		// Plugin startup logic
		getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
		getServer().getPluginManager().registerEvents(new SpawnerBreakListener(), this);
	}
}
