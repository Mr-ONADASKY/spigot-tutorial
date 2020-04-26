package com.ninjawulf98.guimenus.events;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class ClickEvent implements Listener {

    @EventHandler
    public void clickEvent(InventoryClickEvent e) {

        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.AQUA + "Custom GUI")) {

            Player player = (Player) e.getWhoClicked();

            switch (e.getCurrentItem().getType())
            {
                case TNT:
                    player.closeInventory();
                    player.setHealth(0.0);
                    player.sendMessage("You just killed yourself");
                    break;

                case BREAD:
                    player.closeInventory();
                    player.setFoodLevel(20);
                    player.sendMessage("Here, eat!");
                    break;

                case DIAMOND_SWORD:
                    player.closeInventory();
                    player.getInventory().addItem(new ItemStack(Material.DIAMOND_SWORD));
                    player.sendMessage("Here you go");
                    break;
            }
            e.setCancelled(true);
        }
    }
}