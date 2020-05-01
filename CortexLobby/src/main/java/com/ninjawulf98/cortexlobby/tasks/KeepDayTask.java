package com.ninjawulf98.cortexlobby.tasks;

import com.ninjawulf98.cortexlobby.CortexLobby;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;

public class KeepDayTask extends BukkitRunnable {

    CortexLobby plugin;

    public KeepDayTask(CortexLobby plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        World world = Bukkit.getServer().getWorld(plugin.getConfig().getString("lobby-world"));

        if(world != null) {
            world.setTime(0L);
        } else {
            System.out.println("[CortexLobby] The world currently set as a lobby does not exist. Edit the config.yml");
        }
    }

}
