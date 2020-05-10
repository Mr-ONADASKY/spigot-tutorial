package com.ninjawulf98.quartermaster.listeners;

import com.ninjawulf98.quartermaster.QuarterMaster;
import com.ninjawulf98.quartermaster.utils.LockMenuSystem;
import com.ninjawulf98.quartermaster.utils.LockUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MenuListeners implements Listener {

    @EventHandler
    public void onMenuClick(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();

        LockMenuSystem lockMenuSystem = QuarterMaster.getPlayerMenuSystem(p);;

        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_AQUA + "Lock chest?")){
            e.setCancelled(true);
            if (e.getCurrentItem() == null) {
                return;
            }

            if(e.getCurrentItem().getType().equals(Material.TOTEM_OF_UNDYING)){
                p.sendMessage("Creating a new lock...");

                LockUtils.createNewLock(p, lockMenuSystem.getLockToCreate());
                p.closeInventory();

            }else if (e.getCurrentItem().getType().equals(Material.BARRIER)){
                p.closeInventory();
            }
        } else if (e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_RED + "Your Locks:")){
            e.setCancelled(true);

            if (e.getCurrentItem().getType().equals(null)) {

                return;
            } else if (e.getCurrentItem().getType().equals(Material.CHEST)) {
                lockMenuSystem.setLockID(lockMenuSystem.getMenu().getItem(e.getSlot()).getItemMeta().getLore().get(7));
                lockMenuSystem.showLockManagerGUI();
            }
        }else if(e.getView().getTitle().equalsIgnoreCase(ChatColor.GOLD + "Lock Manager")) {
            e.setCancelled(true);

            if(e.getCurrentItem().getType().equals(Material.BARRIER)){
                lockMenuSystem.showLocksListGUI();
            }else if(e.getCurrentItem().getType().equals(Material.WITHER_ROSE)){
                lockMenuSystem.showConfirmDeleteMenu();
            } else if(e.getCurrentItem().getType().equals(Material.ARMOR_STAND)){
                lockMenuSystem.showAccessManagerMenu();
            }
        }else if (e.getView().getTitle().equalsIgnoreCase(ChatColor.RED + "Confirm: Delete lock?")) {
            e.setCancelled(true);

            if(e.getCurrentItem().getType().equals(Material.BARRIER)) {
                lockMenuSystem.showLocksListGUI();
            }else if(e.getCurrentItem().getType().equals(Material.EMERALD)){
                LockUtils.deleteLock(lockMenuSystem.getLockID());

                p.sendMessage(ChatColor.GREEN + "Your lock (" + ChatColor.GOLD + lockMenuSystem.getLockID() + ChatColor.GREEN + ") has been deleted");

                lockMenuSystem.showLocksListGUI();
            }
        }else if(e.getView().getTitle().equalsIgnoreCase(ChatColor.GREEN + "Access Manager")) {
            e.setCancelled(true);

            if(e.getCurrentItem().getType().equals(Material.BARRIER)){
                lockMenuSystem.showLockManagerGUI();
            } else if(e.getCurrentItem().getType().equals(Material.PLAYER_HEAD)){
                lockMenuSystem.showPlayersWithAccessMenu();
            } else if (e.getCurrentItem().getType().equals(Material.ENDER_EYE)) {
                lockMenuSystem.showPlayersToAddMenu();
            } else if(e.getCurrentItem().getType().equals(Material.REDSTONE_BLOCK)) {
                lockMenuSystem.showPlayersToRemoveMenu();
            }
        }else if(e.getView().getTitle().equalsIgnoreCase(ChatColor.GREEN + "Choose a Player to Add:")) {
            e.setCancelled(true);

            if (e.getCurrentItem().getType().equals(Material.PLAYER_HEAD)) {
                lockMenuSystem.showConfirmAddPlayerMenu();
                lockMenuSystem.setPlayerToAdd(Bukkit.getPlayer(e.getCurrentItem().getItemMeta().getDisplayName()));
            } else if (e.getCurrentItem().getType().equals(Material.BARRIER)) {
                lockMenuSystem.showAccessManagerMenu();
            }

        }else if(e.getView().getTitle().equalsIgnoreCase(ChatColor.YELLOW + "Players with Access to Lock")){
            e.setCancelled(true);

            if (e.getCurrentItem().getType().equals(Material.BARRIER)){
                lockMenuSystem.showAccessManagerMenu();
            }
        }else if(e.getView().getTitle().equalsIgnoreCase(ChatColor.YELLOW  + "Choose a player to remove")){
            e.setCancelled(true);

            if (e.getCurrentItem().getType().equals(Material.PLAYER_HEAD)){
                lockMenuSystem.showConfirmRemovePlayerMenu();

                lockMenuSystem.setPlayerToRemove(Bukkit.getPlayer(e.getCurrentItem().getItemMeta().getDisplayName()));
            }else if (e.getCurrentItem().getType().equals(Material.BARRIER)){
                lockMenuSystem.showAccessManagerMenu();
            }
        }else if(e.getView().getTitle().equalsIgnoreCase(ChatColor.GREEN + "Confirm: Add Player")) {
            e.setCancelled(true);

            if (e.getCurrentItem().getType().equals(Material.BARRIER)){
                lockMenuSystem.showAccessManagerMenu();
            }else if(e.getCurrentItem().getType().equals(Material.EMERALD)){
                LockUtils.addPlayerToLock(lockMenuSystem.getLockID(), lockMenuSystem.getPlayerToAdd());

                p.sendMessage(ChatColor.GREEN + "Added " + ChatColor.YELLOW + lockMenuSystem.getPlayerToAdd().getName() + " to your lock.");
                lockMenuSystem.getPlayerToAdd().sendMessage(ChatColor.YELLOW + p.getName() + ChatColor.GREEN + "has just granted you access to one of their locks");

                lockMenuSystem.showAccessManagerMenu();
            }
        }else if(e.getView().getTitle().equalsIgnoreCase(ChatColor.RED + "Confirm: Remove Player")) {
            e.setCancelled(true);

            if (e.getCurrentItem().getType().equals(Material.BARRIER)){
                lockMenuSystem.showAccessManagerMenu();
            }else if(e.getCurrentItem().getType().equals(Material.EMERALD)){
                LockUtils.removePlayerFromLock(lockMenuSystem.getLockID(), lockMenuSystem.getPlayerToRemove());

                p.sendMessage(ChatColor.GRAY + "Removed " + ChatColor.YELLOW + lockMenuSystem.getPlayerToRemove().getName() + " from your lock.");

                lockMenuSystem.showAccessManagerMenu();
            }
        }
    }
}
