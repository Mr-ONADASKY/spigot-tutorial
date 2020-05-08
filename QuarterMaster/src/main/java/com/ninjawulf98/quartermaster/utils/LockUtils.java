package com.ninjawulf98.quartermaster.utils;

import com.ninjawulf98.quartermaster.QuarterMaster;
import org.bson.Document;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.Date;

public class LockUtils {

    public static void createNewLock(Player player, Block block) {
        Document lock = new Document("uuid", player.getUniqueId().toString())
                .append("type", "chest")
                .append("location",
                        new Document("x", block.getX())
                                .append("y", block.getY())
                                .append("z", block.getZ()))
                .append("creation-date", new Date());

        QuarterMaster.getDatabaseCollection().insertOne(lock);
        System.out.println("New lock created!");
        QuarterMaster.Locks_being_created.remove(player);
    }
}
