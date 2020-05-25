package com.ninjawulf98.tabautocompletion.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class MurderCommand implements TabExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player){
            Player p = (Player) sender;

            if(args.length > 0){
                Player target = Bukkit.getPlayer(args[0]);

                p.setHealth(0);

                p.sendMessage("You just murdered another human being");
            }

        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        if(args.length == 1) {
            List<String> playerNames = new ArrayList<>();
            Player[] players = new Player[Bukkit.getServer().getOnlinePlayers().size()];
            Bukkit.getServer().getOnlinePlayers().toArray(players);

            for(Player player: players){
                playerNames.add(player.getName());
            }

            return playerNames;
        }else if (args.length == 2){
            List<String> arguments = new ArrayList<>();
            arguments.add("Daddy");
            arguments.add("HamesHarden#1");

            return arguments;
        }

        return null;
    }
}
