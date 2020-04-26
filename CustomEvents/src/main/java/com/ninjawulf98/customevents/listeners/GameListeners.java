package com.ninjawulf98.customevents.listeners;

import com.ninjawulf98.customevents.events.GameEndEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class GameListeners implements Listener {
	
	@EventHandler
	public void onGameEndEvent(GameEndEvent e) {
		Bukkit.getServer().broadcastMessage("Game has ended!");
		Bukkit.getServer().broadcastMessage("Winner " + e.getWinner().getName());
		Bukkit.getServer().broadcastMessage("Loser " + e.getLoser().getName());
	}
}
