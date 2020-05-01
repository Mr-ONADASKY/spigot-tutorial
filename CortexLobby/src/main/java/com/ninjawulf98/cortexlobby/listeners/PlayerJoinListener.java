package com.ninjawulf98.cortexlobby.listeners;

import com.ninjawulf98.cortexlobby.CortexLobby;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    CortexLobby plugin;

    public PlayerJoinListener(CortexLobby plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {

        if (plugin.getConfig().getBoolean("motd")) {
            Player p = e.getPlayer();

            for (int i = 0; i < plugin.getConfig().getList("motd-message").size(); i++) {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getStringList("motd-message").get(i)));
            }
        }
    }
}
