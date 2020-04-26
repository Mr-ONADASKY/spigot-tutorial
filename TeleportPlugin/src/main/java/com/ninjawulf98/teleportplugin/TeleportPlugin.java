package com.ninjawulf98.teleportplugin;

import com.ninjawulf98.teleportplugin.commands.Teleport;
import com.ninjawulf98.teleportplugin.commands.TeleportAll;
import org.bukkit.plugin.java.JavaPlugin;

public final class TeleportPlugin extends JavaPlugin {
	
	@Override
	public void onEnable () {
		// Plugin startup logic
		this.getCommand("tp").setExecutor(new Teleport());
		this.getCommand("tpall").setExecutor(new TeleportAll());
	}
}
