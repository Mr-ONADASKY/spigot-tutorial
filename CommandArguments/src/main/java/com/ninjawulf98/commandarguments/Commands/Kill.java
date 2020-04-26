package com.ninjawulf98.commandarguments.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Kill implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(args.length == 0) {
                player.sendMessage("You have just killed yourself");
                player.setHealth(0);
            } else {
                Player target = Bukkit.getPlayerExact(args[0]);
                if(target instanceof Player) {
                    target.sendMessage("You just got killed");
                    target.setHealth(0);
                    player.sendMessage("You just killed a man named " + target.getName());
                } else {
                    player.sendMessage("You need to kill a real player");
                }
            }
        }

        return false;
    }
}
