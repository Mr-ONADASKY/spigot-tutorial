package com.ninjawulf98.bangui;

import com.ninjawulf98.bangui.commands.BanCommand;
import com.ninjawulf98.bangui.events.BanInventoryEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class BanGUI extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
    getCommand("bangui").setExecutor(new BanCommand(this));

    getServer().getPluginManager().registerEvents(new BanInventoryEvent(this), this);
    }

    public  void openGUI (Player player) {
        ArrayList<Player> player_list = new ArrayList<>(player.getServer().getOnlinePlayers());

        Inventory bangui = Bukkit.createInventory(player, 45, ChatColor.BLUE + "Player List");
        //For every player, add their name and head to the gui

        for (Player current_player : player_list) {
            ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD, 1);
            SkullMeta meta = (SkullMeta) playerHead.getItemMeta();
            meta.setDisplayName(current_player.getDisplayName());
            ArrayList<String> lore = new ArrayList<>();
            lore.add(ChatColor.GOLD + "Player Health: " + current_player.getHealth());
            lore.add(ChatColor.GOLD + "XP: " + current_player.getExp());
            meta.setLore(lore);
            meta.setOwningPlayer(current_player);
            playerHead.setItemMeta(meta);

            bangui.addItem(playerHead);
        }

        player.openInventory(bangui);
    }

    public void openConfirmMenu(Player player, Player PlayerToBan) {

        Inventory banPlayerMenu = Bukkit.createInventory(player, 9, "Confirm ban");

        //Ban option
        ItemStack ban = new ItemStack(Material.WOODEN_AXE, 1);
        ItemMeta ban_meta = ban.getItemMeta();
        ban_meta.setDisplayName(ChatColor.GREEN + "Ban");
        ban.setItemMeta(ban_meta);
        banPlayerMenu.setItem(0, ban);

        // Add player
        ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta meta = (SkullMeta) playerHead.getItemMeta();
        meta.setDisplayName(PlayerToBan.getDisplayName());
        meta.setOwningPlayer(PlayerToBan);
        playerHead.setItemMeta(meta);
        banPlayerMenu.setItem(4, playerHead);

        // Cancel option
        ItemStack cancel = new ItemStack(Material.BARRIER, 1);
        ItemMeta cancel_meta = cancel.getItemMeta();
        cancel_meta.setDisplayName(ChatColor.RED + "Cancel");
        cancel.setItemMeta(cancel_meta);
        banPlayerMenu.setItem(8, cancel);

        player.openInventory(banPlayerMenu);
    }
}
