package com.ninjawulf98.silktouchspawner.events;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class SpawnerBreakEvent extends Event {
	
	private static final HandlerList handlers = new HandlerList();
	
	private Player breaker;
	private Block spawner;
	
	public SpawnerBreakEvent(Player breaker, Block spawner) {
		this.breaker = breaker;
		this.spawner = spawner;
	}
	
	@Override
	public HandlerList getHandlers () {
		return handlers;
	}
	
	public static  HandlerList getHandlerList() {
		return handlers;
	}
	
	public Player getBreaker () {
		return breaker;
	}
	
	public Block getSpawner () {
		return spawner;
	}
}
