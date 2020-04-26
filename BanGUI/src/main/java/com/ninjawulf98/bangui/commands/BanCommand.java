package com.ninjawulf98.bangui.commands;

import com.ninjawulf98.bangui.BanGUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class BanCommand implements CommandExecutor {

    BanGUI plugin;

    public BanCommand(BanGUI plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            plugin.openGUI(player);
        }

        return true;
    }
}
