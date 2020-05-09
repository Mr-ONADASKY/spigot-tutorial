package com.ninjawulf98.quartermaster.commands;

import com.ninjawulf98.quartermaster.utils.GUIManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ListCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player){
            Player player = (Player) sender;

            GUIManager.openLocksListGUI(player);
        }

        return true;
    }
}
