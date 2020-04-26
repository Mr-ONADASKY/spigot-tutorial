package com.ninjawulf98.teleportbow.commands;

import com.ninjawulf98.teleportbow.TeleportBow;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeleportBowCommand implements CommandExecutor {

    TeleportBow plugin;

    public TeleportBowCommand(TeleportBow plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("tpbow.spawnbow")) {
                plugin.givePlayerBow(player);
            }
        } else {
            System.out.println("You are not a player. You can't do this");
        }

        return true;
    }
}
