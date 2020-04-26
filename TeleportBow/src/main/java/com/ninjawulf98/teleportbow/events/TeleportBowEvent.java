package com.ninjawulf98.teleportbow.events;

import com.ninjawulf98.teleportbow.TeleportBow;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class TeleportBowEvent implements Listener {

    TeleportBow plugin;

    public TeleportBowEvent(TeleportBow plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBowShoot(ProjectileHitEvent e) {
        Player player = (Player) e.getEntity().getShooter();
        Location location = e.getEntity().getLocation();

        player.teleport(location);
        player.playSound(player.getLocation(), Sound.ENTITY_CAT_PURREOW, 1.0F, 1.0F);
        player.sendMessage("Swoosh!");
    }
}
