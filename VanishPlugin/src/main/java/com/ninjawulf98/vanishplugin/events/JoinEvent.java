package com.ninjawulf98.vanishplugin.events;

import com.ninjawulf98.vanishplugin.VanishPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {

    VanishPlugin plugin;

    public JoinEvent(VanishPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void PlayerJoin(PlayerJoinEvent e) {
    for(Player people: plugin.invisible_list) {
        Player player = e.getPlayer();
           player.hidePlayer(plugin, people);
    }
    }
}
