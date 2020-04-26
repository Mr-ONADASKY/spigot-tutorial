package com.ninjawulf98.apiplugin;

import com.connorlinfoot.actionbarapi.ActionBarAPI;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener{

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        ActionBarAPI.sendActionBar(e.getPlayer(), ChatColor.GREEN + "Welcome to the server");
    }
}
