package com.ninjawulf98.entities;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class MoveEvent implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e)  {

        Entity entity = Bukkit.getWorld("world").spawnEntity(new Location(Bukkit.getWorld("world"), -270,90, -150), EntityType.SKELETON);
        entity.setGravity(false);
        entity.setGlowing(true);
        entity.setCustomName("André");
        entity.setCustomNameVisible(true);
    //        Player player = e.getPlayer();
//
//        player.getWorld().spawnEntity(player.getLocation(), EntityType.TURTLE);
    }
}
