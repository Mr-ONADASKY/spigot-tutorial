package com.ninjawulf98.randomtp.commands;

import com.ninjawulf98.randomtp.TeleportUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Random;

public class RandomTPCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;

            if(args.length == 0){
                player.teleport(TeleportUtils.generateLocation(player));

                int x = player.getLocation().getBlockX();
                int y = player.getLocation().getBlockY();
                int z = player.getLocation().getBlockZ();


                player.sendMessage(ChatColor.RED + "Teleported to Random Location!!!");
                player.sendMessage(ChatColor.AQUA + "New Coordinates: " + ChatColor.LIGHT_PURPLE + x + " " + y + " " + z);

            } else if(args.length == 1) {
                if (player.hasPermission(("rtp.others"))) {
                    Player target = Bukkit.getPlayer(args[0]);
                    target.teleport(TeleportUtils.generateLocation(target));

                    int x = target.getLocation().getBlockX();
                    int y = target.getLocation().getBlockY();
                    int z = target.getLocation().getBlockZ();

                    target.sendMessage(ChatColor.GREEN + player.getDisplayName() + ChatColor.GOLD + " just Random Teleported you");
                    target.sendMessage(ChatColor.AQUA + "New Coordinates: " + ChatColor.LIGHT_PURPLE + x + " " + y + " " + z);

                    player.sendMessage(ChatColor.RED + "Player successfully teleported to: " + ChatColor.LIGHT_PURPLE + x + " " + y + " " + z);
                }
            }
        }

        return true;
    }
}
