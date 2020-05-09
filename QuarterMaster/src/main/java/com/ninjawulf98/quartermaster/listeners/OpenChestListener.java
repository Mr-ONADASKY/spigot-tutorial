package com.ninjawulf98.quartermaster.listeners;

import com.ninjawulf98.quartermaster.utils.LockUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class OpenChestListener implements Listener {

    @EventHandler
    public void openChestListener(PlayerInteractEvent event) {
        if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
            Block block = event.getClickedBlock();

            if(block.getType().equals(Material.CHEST)){
                if(LockUtils.isCurrentlyLocked(block)){
                    if( LockUtils.getWhoLocked(block) == event.getPlayer()){
                        event.getPlayer().sendMessage("You own this chest");
                    }else if (LockUtils.getWhoLocked(block) != event.getPlayer()){
                        event.setCancelled(true);
                        event.getPlayer().sendMessage(ChatColor.DARK_RED + "The chest is locked by " + LockUtils.getWhoLocked(block).getName());
                    }
                }
            }
        }
    }
}
