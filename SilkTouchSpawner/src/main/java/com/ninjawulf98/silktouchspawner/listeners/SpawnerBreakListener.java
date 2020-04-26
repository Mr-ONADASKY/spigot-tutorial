package com.ninjawulf98.silktouchspawner.listeners;

import com.ninjawulf98.silktouchspawner.events.SpawnerBreakEvent;
import org.bukkit.ChatColor;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;

public class SpawnerBreakListener implements Listener {
	
	@EventHandler
	public void onSpawnerBreak(SpawnerBreakEvent e) {
		CreatureSpawner creatureSpawner = (CreatureSpawner) e.getSpawner().getState();
		ItemStack spawner_to_give = new ItemStack(creatureSpawner.getType());
		BlockStateMeta meta = (BlockStateMeta) spawner_to_give.getItemMeta();
		CreatureSpawner newCreatureSpawner = (CreatureSpawner) meta.getBlockState();
		
		newCreatureSpawner.setSpawnedType((creatureSpawner.getSpawnedType()));
		meta.setBlockState(newCreatureSpawner);
		spawner_to_give.setItemMeta(meta);
		meta.setDisplayName(newCreatureSpawner.getType() + " Spawner");
		
		e.getBreaker().sendMessage(ChatColor.GRAY + "You have silk touched a spawner");
		e.getBreaker().getInventory().addItem(spawner_to_give);
	}
}
