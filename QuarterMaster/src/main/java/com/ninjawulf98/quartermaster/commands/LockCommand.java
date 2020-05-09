package com.ninjawulf98.quartermaster.commands;

import com.ninjawulf98.quartermaster.QuarterMaster;
import com.ninjawulf98.quartermaster.utils.LockMenuSystem;
import com.ninjawulf98.quartermaster.utils.LockUtils;
import org.bukkit.ChatColor;
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
            Player p = (Player) sender;
            Block target;

            if (p.getTargetBlockExact(5) !=  null){
                target = p.getTargetBlockExact(5);
                if(target.getType().equals(Material.CHEST)){
                    if(LockUtils.isCurrentlyLocked(target)){
                        if(LockUtils.getWhoLocked(target).equals(p)){
                            p.sendMessage(ChatColor.BLUE + "You already own this chest. It's locked. ");
                        }else {
                            p.sendMessage(ChatColor.DARK_RED + "That chest is already locked by " + ChatColor.GRAY + LockUtils.getWhoLocked(target).getName());
                        }
                    }else {
                        p.sendMessage("Would you like to lock this chest?");

                        LockMenuSystem lockMenuSystem = QuarterMaster.getPlayerMenuSystem(p);
                        lockMenuSystem.setLockToCreate(target);

                        lockMenuSystem.showAskGUI();
                    }

                }
            }
        }

        return true;
    }
}
