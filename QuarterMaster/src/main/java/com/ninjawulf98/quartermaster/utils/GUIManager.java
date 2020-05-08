package com.ninjawulf98.quartermaster.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GUIManager {
    private static Inventory askGUI;

    public static void openAskGUI(Player player){
        askGUI = Bukkit.createInventory(player, 9, ChatColor.DARK_AQUA + "Lock chest?");
        ItemStack yes = new ItemStack(Material.TOTEM_OF_UNDYING, 1);
        ItemMeta yes_meta = yes.getItemMeta();
        yes_meta.setDisplayName(ChatColor.GREEN + "Yes");
        yes.setItemMeta(yes_meta);

        ItemStack no = new ItemStack(Material.BARRIER, 1);
        ItemMeta no_meta = no.getItemMeta();
        no_meta.setDisplayName(ChatColor.RED + "No");
        no.setItemMeta(no_meta);

        askGUI.setItem(3, yes);
        askGUI.setItem(5, no);

        for(int i = 0; i < 9; i++){
            if(askGUI.getItem(i) == null){
                askGUI.setItem(i, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
            }
        }

        player.openInventory(askGUI);
    }
}
