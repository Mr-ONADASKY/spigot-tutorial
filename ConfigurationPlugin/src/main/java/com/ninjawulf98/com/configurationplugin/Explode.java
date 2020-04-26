package com.ninjawulf98.com.configurationplugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Explode implements CommandExecutor {

    ConfigurationPlugin plugin = ConfigurationPlugin.getPlugin(ConfigurationPlugin.class);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        String food = plugin.getConfig().getString("Food");

        Player player = (Player) sender;
        player.sendMessage(food);

        return false;
    }
}
