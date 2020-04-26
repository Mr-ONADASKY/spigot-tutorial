package com.ninjawulf98.packettitle;

import org.bukkit.plugin.java.JavaPlugin;

public final class PacketTitle extends JavaPlugin {
	
	@Override
	public void onEnable () {
		// Plugin startup logic
		getCommand("title").setExecutor(new com.ninjawulf98.packettitle.commands.PacketTitle());
	}
	
	@Override
	public void onDisable () {
		// Plugin shutdown logic
	}
}
