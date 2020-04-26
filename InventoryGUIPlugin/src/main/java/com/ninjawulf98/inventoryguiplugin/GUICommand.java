package com.ninjawulf98.inventoryguiplugin;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GUICommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            Inventory gui = Bukkit.createInventory(player, 9, "How should I kill him?");
            ItemStack item1 = new ItemStack(Material.CHIPPED_ANVIL, 1);
            ItemMeta item1_meta = item1.getItemMeta();
            item1_meta.setDisplayName("Slice");
            item1.setItemMeta(item1_meta);

            ItemStack item2 = new ItemStack(Material.CYAN_CARPET, 1);
            ItemMeta item2_meta = item2.getItemMeta();
            item2_meta.setDisplayName("Explode");
            item2.setItemMeta(item2_meta);

            gui.addItem(item1, item2);
            player.openInventory(gui);

        }

        return true;
    }
}
