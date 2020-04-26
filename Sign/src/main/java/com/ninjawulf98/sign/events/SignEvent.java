package com.ninjawulf98.sign.events;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class SignEvent implements Listener {

    @EventHandler
    public void onSignEvent(SignChangeEvent e) {
        e.getBlock().setType(Material.DIAMOND_BLOCK);
    }
}
