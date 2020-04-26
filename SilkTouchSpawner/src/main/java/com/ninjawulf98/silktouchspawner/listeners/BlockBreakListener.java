package com.ninjawulf98.silktouchspawner.listeners;

import com.ninjawulf98.silktouchspawner.events.SpawnerBreakEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		Block blockBroken = e.getBlock();
		if (blockBroken.getType().equals(Material.SPAWNER) && e.getPlayer().getInventory().getItemInMainHand().containsEnchantment(Enchantment.SILK_TOUCH) && e.getPlayer().hasPermission("SilktouchSpawner.retrieve")) {
			Bukkit.getServer().getPluginManager().callEvent(new SpawnerBreakEvent(e.getPlayer(), blockBroken));
		} else if (!e.getPlayer().hasPermission("SilktouchSpawner.retrieve")) {
			e.getPlayer().sendMessage(ChatColor.GRAY + "You cannot get spawners from silk touch");
		}
	}
}
