package com.ninjawulf98.com.configurationplugin;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class ConfigurationPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        getCommand("Explode").setExecutor(new Explode());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equals("printMessageFromConfig")){
            Player player = (Player) sender;
            String food = getConfig().getString("Food");
            int number = getConfig().getInt("Number");
            boolean bool = getConfig().getBoolean("Boolean");
            String thirdItem = getConfig().getStringList("List").get(2);
            player.sendMessage(ChatColor.DARK_AQUA + "The food in the config.yml is " + ChatColor.RED + food + " " + number + " " + bool);
            player.sendMessage(ChatColor.DARK_AQUA + "The third item in the config.yml is " + ChatColor.RED + thirdItem);
        }else if (command.getName().equals("setFood")) {
            getConfig().set("Food", "Apple");
            saveConfig();
        }

        return false;
    }
}
