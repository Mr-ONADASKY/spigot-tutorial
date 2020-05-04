package com.ninjawulf98.enchantments.listeners;

import com.ninjawulf98.enchantments.Enchantments;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        ItemStack chestplate = new ItemStack(Material.IRON_CHESTPLATE, 1);
        chestplate.addUnsafeEnchantment(Enchantments.glowEnchantment, 1);

        ItemMeta meta = chestplate.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.LIGHT_PURPLE + "Glow I");
        meta.setLore(lore);
        chestplate.setItemMeta(meta);

        player.getEquipment().setChestplate(chestplate);

        ItemStack sword = new ItemStack(Material.DIAMOND_SWORD, 1);
        sword.addUnsafeEnchantment(Enchantments.hemorrhageEnchantment, 1);

        ItemMeta sword_meta = sword.getItemMeta();
        ArrayList<String> sword_lore = new ArrayList<>();
        sword_lore.add(ChatColor.LIGHT_PURPLE + "Hemorrhage I");
        sword_meta.setLore(sword_lore);
        sword.setItemMeta(sword_meta);

        player.getInventory().setItemInMainHand(sword);
    }
}
