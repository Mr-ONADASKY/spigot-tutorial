package com.ninjawulf98.commandarguments.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SendWord implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player){
            Player player = (Player) sender;

             if(args.length > 0) {
                 if(args[0].equalsIgnoreCase("garbage")){
                     player.sendMessage("You are garbage");

                 }else if(args.length > 1 && args[1].equalsIgnoreCase("tree")){
                    player.sendMessage("You are a tree");
                 } else{
                     player.sendMessage(args[0]);

                 }
            } else {
                 player.sendMessage("You need to give the command an argument");
                 player.sendMessage("/sendWord [word]");
            }

        } else {
            System.out.println("You need to be a player to execute this command!");
        }

        return false;
    }
}
