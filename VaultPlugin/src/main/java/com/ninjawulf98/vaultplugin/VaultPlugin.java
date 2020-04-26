package com.ninjawulf98.vaultplugin;

import com.ninjawulf98.vaultplugin.commands.VaultCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class VaultPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("The vault plugin has started up.");
        getCommand("vault").setExecutor(new VaultCommand());

    }

}
