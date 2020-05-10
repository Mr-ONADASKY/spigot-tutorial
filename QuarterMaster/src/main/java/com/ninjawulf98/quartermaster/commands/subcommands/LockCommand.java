package com.ninjawulf98.quartermaster.commands.subcommands;

import com.ninjawulf98.quartermaster.QuarterMaster;
import com.ninjawulf98.quartermaster.commands.SubCommand;
import com.ninjawulf98.quartermaster.utils.LockMenuSystem;
import com.ninjawulf98.quartermaster.utils.LockUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class LockCommand extends SubCommand {

    @Override
    public String getName() {
        return "lock";
    }

    @Override
    public String getDescription() {
        return "Allows you to lock a block";
    }

    @Override
    public String getSyntax() {
        return "/qm lock";
    }

    @Override
    public void perform(Player p, String[] args) {
        Block target;

        if (p.getTargetBlockExact(5) !=  null){
            target = p.getTargetBlockExact(5);

            for(String lockableBlock: LockUtils.getLockableBlocks()){
                if(target.getType().equals(Material.valueOf(lockableBlock))){
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
        }else if (p.getTargetBlockExact(5) == null){
            p.sendMessage(ChatColor.GRAY + "Look at something nearby");
        }
    }
}
