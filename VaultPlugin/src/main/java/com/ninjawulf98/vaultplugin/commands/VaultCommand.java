package com.ninjawulf98.vaultplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class VaultCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player){
            Player player = (Player) sender;

            Inventory vault = Bukkit.createInventory(player, 9, "Your vault");

            ItemStack item1 = new ItemStack(Material.CLAY_BALL, 2);

            item1.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK, 1000);
            ItemMeta meta = item1.getItemMeta();
            meta.setDisplayName(ChatColor.AQUA + "Item Name");
            meta.setUnbreakable(true);
            ArrayList<String> lore = new ArrayList<>();
            lore.add(ChatColor.GOLD + "some lore");
            lore.add(ChatColor.DARK_PURPLE + "some more lore");
            meta.setLore(lore);
            item1.setItemMeta(meta);

            vault.addItem(item1);

            player.openInventory(vault);

//            vault.setItem(2, item1);
//
//            vault.addItem(item1);
//
//            vault.clear();
//
//            ItemStack item2 = new ItemStack(Material.BEEF, 3);
//            ItemStack[] items = {item1, item2};
//
//            vault.setContents(items);
//
//            ItemStack[] items2 = vault.getContents();
//
//            player.openInventory(vault);

        } else {
            System.out.println("You need to be a player to execute this command.");
        }

        return true;
    }
}
