package com.ninjawulf98.flyplugin.commands;

import com.ninjawulf98.flyplugin.FlyPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class FlyCommand implements CommandExecutor {

    private FlyPlugin plugin;
    private ArrayList<Player> list_of_flying_players = new ArrayList<>();

    public FlyCommand(FlyPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(args.length == 0){
                if(player.hasPermission("flyplugin.fly")) {
                    flyMethod(player);
                }
            } else if(args.length == 1) {
                if(player.hasPermission("flyplugin.fly.others")) {
                    Player target = Bukkit.getPlayer(args[0]);
                    flyMethod(target);
                }else {
                    player.sendMessage( ChatColor.RED + "You do not have permission to make other players fly");
                }
            } else {
                return false;
            }
        }

        return true;
    }

    private void flyMethod(Player player){
        if(list_of_flying_players.contains(player)) {
            list_of_flying_players.remove(player);
            player.setAllowFlight(false);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("off-message")));
        }else if(!list_of_flying_players.contains(player)){
            list_of_flying_players.add(player);
            player.setAllowFlight(true);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("on-message")));
        }
    }
}
