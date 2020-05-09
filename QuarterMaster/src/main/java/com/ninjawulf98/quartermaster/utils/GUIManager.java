package com.ninjawulf98.quartermaster.utils;

import com.mongodb.DocumentToDBRefTransformer;
import com.ninjawulf98.quartermaster.QuarterMaster;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.function.Consumer;

public class GUIManager {
    private static Inventory askGUI;
    private static Inventory locksListGUI;
    private static  Inventory lockManagerGUI;

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
            lore.add(document.getObjectId("_id").toString());

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

    public static void openLockManagerGUI(Player p, Document lock) {
        lockManagerGUI = Bukkit.createInventory(p, 9, ChatColor.GOLD + "Lock Manager");


        ItemStack manage_access = new ItemStack(Material.ARMOR_STAND, 1);
        ItemMeta access_meta = manage_access.getItemMeta();
        access_meta.setDisplayName(ChatColor.YELLOW + "Access Manager");
        ArrayList<String> access_lore = new ArrayList<>();
        access_lore.add(ChatColor.GREEN + "Manage who has access to this lock");
        access_meta.setLore(access_lore);
        manage_access.setItemMeta(access_meta);

        ItemStack delete_lock = new ItemStack(Material.WITHER_ROSE, 1);
        ItemMeta delete_meta = delete_lock.getItemMeta();
        delete_meta.setDisplayName(ChatColor.DARK_RED + "Delete Lock");
        ArrayList<String> delete_lore = new ArrayList<>();
        delete_lore.add(ChatColor.GREEN + "Deleting the lock will ");
        delete_lore.add(ChatColor.GREEN + "make the block totally unprotected.");
        delete_meta.setLore(delete_lore);
        delete_lock.setItemMeta(delete_meta);

        ItemStack totem = new ItemStack(Material.TOTEM_OF_UNDYING, 1);
        ItemMeta totem_meta = totem.getItemMeta();
        totem_meta.setDisplayName(ChatColor.GOLD + "Lock Manager");
        totem.setItemMeta(totem_meta);

        ItemStack lock_info = new ItemStack(Material.WRITABLE_BOOK, 1);
        ItemMeta info_meta = lock_info.getItemMeta();
        info_meta.setDisplayName(ChatColor.GREEN + "Lock Information");
        ArrayList<String> info_lore = new ArrayList<>();

        info_lore.add(ChatColor.GOLD + "-------------");
        info_lore.add(ChatColor.YELLOW + "Location:");

        Document location = (Document) lock.get("location");

        info_lore.add(ChatColor.AQUA + "  x: " + ChatColor.GREEN + location.getInteger("x"));
        info_lore.add(ChatColor.AQUA + "  y: " + ChatColor.GREEN + location.getInteger("y"));
        info_lore.add(ChatColor.AQUA + "  z: " + ChatColor.GREEN + location.getInteger("z"));
        info_lore.add("Date Created: " + lock.getDate("creation-date").toString());
        info_lore.add(ChatColor.GOLD + "-------------");

        info_meta.setLore(info_lore);
        lock_info.setItemMeta(info_meta);

        ItemStack close_menu = new ItemStack(Material.BARRIER, 1);
        ItemMeta close_meta = close_menu.getItemMeta();
        close_meta.setDisplayName(ChatColor.DARK_RED + "Close");
        ArrayList<String> close_lore = new ArrayList<>();
        close_lore.add(ChatColor.GREEN + "Go back the locks list");
        close_meta.setLore(close_lore);
        close_menu.setItemMeta(close_meta);

        //set the slots for the options
        lockManagerGUI.setItem(0, manage_access);
        lockManagerGUI.setItem(1, delete_lock);
        lockManagerGUI.setItem(4, totem);
        lockManagerGUI.setItem(7, lock_info);
        lockManagerGUI.setItem(8, close_menu);

        p.openInventory(lockManagerGUI);
    }

    public static Inventory getLocksListGUI() {
        return locksListGUI;
    }
}
