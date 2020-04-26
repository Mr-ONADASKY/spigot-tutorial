package com.ninjawulf98.commandtutorial;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class CommandTutorial extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equals("die")){
            if(sender instanceof Player) {
                Player player = (Player) sender;
                player.sendMessage("Bye!");
                player.setHealth(0);
            } else {
                System.out.println(("You need to be a player to run this command!"));
            }

        }

        if(command.getName().equals("heal")){
            if(sender instanceof Player) {
                Player player = (Player) sender;
                if(player.getHealth() == 20.0){
                    player.sendMessage("You already have the maximum health available!");
                }else {
                    player.sendMessage("Here you go!");
                    player.setHealth(player.getHealth() + 1);
                }
            } else {
                System.out.println(("You need to be a player to run this command!"));
            }
        }

        return false;
    }
}
