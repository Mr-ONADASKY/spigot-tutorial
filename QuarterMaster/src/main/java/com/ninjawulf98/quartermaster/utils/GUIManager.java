package com.ninjawulf98.quartermaster.utils;

import com.mongodb.DocumentToDBRefTransformer;
import com.ninjawulf98.quartermaster.QuarterMaster;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.function.Consumer;

public class GUIManager {
    private static Inventory askGUI;
    private static Inventory locksListGUI;

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

    public static void openLocksListGUI(Player p) {
        locksListGUI = Bukkit.createInventory(p, 54, ChatColor.DARK_RED + "Your locks:");

        String uuid  = p.getUniqueId().toString();
        Document filter = new Document("uuid", uuid);
        QuarterMaster.getDatabaseCollection().find(filter).forEach((Consumer <Document>) document -> {

            ItemStack lock = new ItemStack(Material.CHEST, 1);
            ItemMeta lock_meta = lock.getItemMeta();
            lock_meta.setDisplayName(ChatColor.GREEN + "Chest Lock");

            ArrayList<String> lore = new ArrayList<>();
            lore.add(ChatColor.GOLD + "-------------");
            lore.add(ChatColor.YELLOW + "Location:");

            Document location = (Document) document.get("location");

            lore.add(ChatColor.AQUA + "x: " + ChatColor.GREEN + location.getInteger("x"));
            lore.add(ChatColor.AQUA + "y: " + ChatColor.GREEN + location.getInteger("y"));
            lore.add(ChatColor.AQUA + "z: " + ChatColor.GREEN + location.getInteger("z"));
            lore.add("Date Created: " + document.getDate("creation-date").toString());
            lore.add(ChatColor.GOLD + "-------------");

            lock_meta.setLore(lore);
            lock.setItemMeta(lock_meta);
            locksListGUI.addItem(lock);
        });

        for(int i = 0; i < locksListGUI.getSize(); i++){
            if(locksListGUI.getItem(i) == null){
                locksListGUI.setItem(i, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
            }
        }


        p.openInventory(locksListGUI);
    }
}
