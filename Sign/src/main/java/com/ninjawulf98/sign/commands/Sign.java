package com.ninjawulf98.sign.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Sign implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player){
            Player player = (Player) sender;

            if (args.length != 2) {
                player.sendMessage("You need to specify a line and a word");
                player.sendMessage("Like this: /sign 3 bacon");
            } else if(args.length == 2) {
                player.getWorld().getBlockAt(player.getLocation()).setType(Material.OAK_SIGN);

                org.bukkit.block.Sign sign = (org.bukkit.block.Sign) player.getWorld().getBlockAt(player.getLocation()).getState();

                int line = Integer.parseInt(args[0]) - 1;
                String word = args[1];
                sign.setLine(line, word);
                sign.update();
            }
        }

        return true;
    }
}
