package com.ninjawulf98.quartermaster.commands;

import com.ninjawulf98.quartermaster.QuarterMaster;
import com.ninjawulf98.quartermaster.utils.LockMenuSystem;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ListCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player){
            Player p = (Player) sender;

            LockMenuSystem lockMenuSystem = QuarterMaster.getPlayerMenuSystem(p);

            lockMenuSystem.showLocksListGUI();
        }

        return true;
    }
}
