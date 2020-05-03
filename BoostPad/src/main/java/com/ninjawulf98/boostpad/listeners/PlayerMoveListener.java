package com.ninjawulf98.boostpad.listeners;

import com.ninjawulf98.boostpad.BoostPad;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveListener implements Listener {

    BoostPad plugin;

    public PlayerMoveListener(BoostPad plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerWalk(PlayerMoveEvent e) {
        if(plugin.getConfig().getBoolean("enable")) {
            Player p = e.getPlayer();
            Location blockUnder = p.getLocation();
            blockUnder.setY(blockUnder.getY() - 1);

            if(p.getLocation().getBlock().getType().equals(Material.valueOf(plugin.getConfig().getString("top-block"))) && blockUnder.getBlock().getType().equals(Material.valueOf(plugin.getConfig().getString("under-block")))){
                p.setVelocity(p.getLocation().getDirection().multiply(plugin.getConfig().getInt("velocity-multiplier")).setY(plugin.getConfig().getInt("y-velocity")));
                plugin.jumping_players.add(p);
                if (plugin.getConfig().getBoolean("message")){
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("launch-message")));
                }
            }
        }
    }
}
