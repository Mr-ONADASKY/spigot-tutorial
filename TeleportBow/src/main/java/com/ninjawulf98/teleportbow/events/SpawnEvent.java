package com.ninjawulf98.teleportbow.events;

import com.ninjawulf98.teleportbow.TeleportBow;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class SpawnEvent implements Listener {

    TeleportBow plugin;

    public SpawnEvent(TeleportBow plugin) {
        this.plugin = plugin;
    }

    public void onSpawnEvent(PlayerJoinEvent e){
        Player player = e.getPlayer();

        if(plugin.getConfig().getBoolean("give-bow-on-join")){
            plugin.givePlayerBow(player);
        }
    }

}
