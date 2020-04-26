package com.ninjawulf98.guimenus.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class GUICommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            Inventory gui = Bukkit.createInventory(player, 9, ChatColor.AQUA + "Custom GUI");

            ItemStack suicide = new ItemStack(Material.TNT);
            ItemStack feed = new ItemStack(Material.BREAD);
            ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);

            ItemMeta suicide_meta = suicide.getItemMeta();
            suicide_meta.setDisplayName(ChatColor.RED + "Suicide");
            ArrayList<String> suicide_lore = new ArrayList<>();
            suicide_lore.add(ChatColor.GOLD + "Kill yourself.");
            suicide_meta.setLore(suicide_lore);
            suicide.setItemMeta(suicide_meta);

            ItemMeta feed_meta = feed.getItemMeta();
            feed_meta.setDisplayName(ChatColor.DARK_GREEN + "Feed");
            ArrayList<String> feed_lore = new ArrayList<>();
            feed_lore.add(ChatColor.GOLD + "Feed yourself.");
            feed_meta.setLore(feed_lore);
            feed.setItemMeta(feed_meta);

            ItemMeta sword_meta = sword.getItemMeta();
            sword_meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Sword");
            ArrayList<String> sword_lore = new ArrayList<>();
            sword_lore.add(ChatColor.GOLD + "Give yourself a sword.");
            sword_meta.setLore(sword_lore);
            sword.setItemMeta(sword_meta);

            ItemStack[] menu_items = {suicide, feed, sword};
            gui.setContents(menu_items);
            player.openInventory(gui);
        }

        return true;
    }
}
