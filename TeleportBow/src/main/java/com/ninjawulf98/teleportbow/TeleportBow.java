package com.ninjawulf98.teleportbow;

import com.ninjawulf98.teleportbow.commands.TeleportBowCommand;
import com.ninjawulf98.teleportbow.events.SpawnEvent;
import com.ninjawulf98.teleportbow.events.TeleportBowEvent;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class TeleportBow extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        getServer().getPluginManager().registerEvents(new TeleportBowEvent(this), this);
        getServer().getPluginManager().registerEvents(new SpawnEvent(this), this);
        getCommand("tpbow").setExecutor(new TeleportBowCommand(this));
    }

    public void givePlayerBow(Player player) {
        ItemStack teleport_bow = new ItemStack(Material.BOW);
        teleport_bow.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 666);

        ItemMeta meta = teleport_bow.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("bow-name")));
        meta.setUnbreakable(true);

        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("bow-description")));
        meta.setLore(lore);

        teleport_bow.setItemMeta(meta);

        player.getInventory().addItem(teleport_bow);
    }

}
