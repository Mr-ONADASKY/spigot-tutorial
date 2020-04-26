package com.ninjawulf98.inventoryguiplugin;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MenuHandler implements Listener {

    @EventHandler
    public void onMenuClick(InventoryClickEvent e) {
        if(e.getView().getTitle().equalsIgnoreCase("How should I kill him?")) {
            e.setCancelled(true);
            Player player = (Player) e.getWhoClicked();
            if(e.getCurrentItem() == null){
                return;
            }

            if(e.getCurrentItem().getType().equals(Material.CHIPPED_ANVIL)) {
                player.sendMessage("You want to slice someone");
                player.closeInventory();
            } else if(e.getCurrentItem().getType().equals(Material.CYAN_CARPET)) {
                player.sendMessage("You want to explode him!");
                player.closeInventory();
            }
        }
    }
}
