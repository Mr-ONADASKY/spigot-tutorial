package com.ninjawulf98.playerjoinleave.events;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    @EventHandler
    void onPlayerJoin(PlayerJoinEvent e) {
        String playerName = e.getPlayer().getDisplayName();
        e.setJoinMessage(ChatColor.AQUA + "Welcome back to the server " + ChatColor.RED + playerName);
    }
}
