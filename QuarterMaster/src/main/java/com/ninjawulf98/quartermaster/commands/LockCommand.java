package com.ninjawulf98.quartermaster.commands;

import com.ninjawulf98.quartermaster.QuarterMaster;
import com.ninjawulf98.quartermaster.utils.GUIManager;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LockCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;
            Block target;

            if (player.getTargetBlockExact(5) !=  null){
                target = player.getTargetBlockExact(5);
                if(target.getType().equals(Material.CHEST)){
                    player.sendMessage("Would you like to lock this chest?");
                    GUIManager.openAskGUI(player);
                    QuarterMaster.Locks_being_created.put(player, target);
                }
            }
        }

        return true;
    }
}
