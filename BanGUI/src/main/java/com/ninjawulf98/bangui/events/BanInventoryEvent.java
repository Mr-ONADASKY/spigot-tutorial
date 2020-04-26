package com.ninjawulf98.bangui.events;

import com.ninjawulf98.bangui.BanGUI;
import org.bukkit.BanList;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class BanInventoryEvent implements Listener {

    BanGUI plugin;

    public BanInventoryEvent(BanGUI plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onMenuClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();

        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.BLUE + "Player List")) {
            if(e.getCurrentItem().getType() == Material.PLAYER_HEAD){
                Player whoToBan = player.getServer().getPlayer(e.getCurrentItem().getItemMeta().getDisplayName());

                plugin.openConfirmMenu(player, whoToBan);
            }

            e.setCancelled(true);
        }else if(e.getView().getTitle().equalsIgnoreCase("Confirm ban")){
            switch (e.getCurrentItem().getType()) {
                case WOODEN_AXE:
                    String name = e.getClickedInventory().getItem(4).getItemMeta().getDisplayName();

                    player.getServer().getBanList(BanList.Type.NAME).addBan(name, "Because I said so", null, player.getDisplayName()).save();
                    player.getServer().getPlayer(name).kickPlayer("Because I said so");

                    player.sendMessage("Banned player " + name);
                    break;

                case BARRIER:
                    plugin.openGUI(player);
                    break;
            }

            e.setCancelled(true);
        }
    }
}
