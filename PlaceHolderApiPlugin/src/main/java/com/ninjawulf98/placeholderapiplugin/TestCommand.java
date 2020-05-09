package com.ninjawulf98.placeholderapiplugin;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TestCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player){
            Player player = (Player) sender;
            String replaced = PlaceholderAPI.setPlaceholders(player, "%player_name%");
            Bukkit.broadcastMessage(ChatColor.GOLD + replaced + " has just ran the command");

            PlaceHolderApiPlugin.getInstance().vanished.add(player);

            if(args.length == 0) {
                PlaceHolderApiPlugin.getInstance().vanished.add(player);

                return true;
            }

            if(args[0].equals("amivanished")) {
                player.sendMessage((PlaceholderAPI.setPlaceholders(player, "%spigottutorial_isvanished%")));
            }
        }

        return true;
    }
}
