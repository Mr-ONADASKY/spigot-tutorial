package com.ninjawulf98.customconfigplugin;

import com.ninjawulf98.customconfigplugin.commands.Message;
import com.ninjawulf98.customconfigplugin.commands.ReloadCommand;
import com.ninjawulf98.customconfigplugin.files.CustomConfig;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class CustomConfigPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
         File plugin_data_folder = getServer().getPluginManager().getPlugin("CustomConfigPlugin").getDataFolder();
        if(!plugin_data_folder.exists()) {
            plugin_data_folder.mkdirs();
        }

        CustomConfig.setup();
        CustomConfig.get().addDefault("Message", "This is the default message");
        CustomConfig.get().options().copyDefaults(true);
        CustomConfig.save();

        getCommand("message").setExecutor(new Message());
        getCommand("preload").setExecutor(new ReloadCommand());
    }
}
