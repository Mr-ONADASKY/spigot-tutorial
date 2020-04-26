package com.ninjawulf98.playerjoinleave.events;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeave implements Listener {
    @EventHandler
    void onPlayerQuit(PlayerQuitEvent e) {
        String playerName = e.getPlayer().getDisplayName();
        e.setQuitMessage(ChatColor.RED + playerName + ChatColor.AQUA + " deserted");
    }
}
