package com.ninjawulf98.randomtp;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Random;

public class TeleportUtils {

    static RandomTP plugin;

    public TeleportUtils(RandomTP plugin) {
        TeleportUtils.plugin = plugin;
    }

    public static HashSet<Material> bad_blocks = new HashSet<>();

    static {
        bad_blocks.add(Material.LAVA);
        bad_blocks.add(Material.FIRE);
    }

    public static Location generateLocation (Player player) {

        Random random = new Random();

        int x = 0;
        int y = 0;
        int z = 0;

        if(plugin.getConfig().getBoolean("world-border")){
         x = random.nextInt(plugin.getConfig().getInt("border"));
         z = random.nextInt(plugin.getConfig().getInt("border"));

        } else {
            x = random.nextInt(25000);
            z = random.nextInt(25000);
        }

        y = player.getWorld().getHighestBlockYAt(x,z);


        Location randomLocation = new Location(player.getWorld(), x,y,z);

        if(isLocationSafe(randomLocation)){
            return randomLocation;
        }

        return generateLocation(player);
    }

    public static boolean isLocationSafe(Location location) {

        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();

        Block block = location.getWorld().getBlockAt(x, y, z);
        Block below = location.getWorld().getBlockAt(x, y -1, z);
        Block above = location.getWorld().getBlockAt(x, y + 1, z);

        return !(bad_blocks.contains(below.getType()) || block.getType().isSolid() || above.getType().isSolid());
    }
}
