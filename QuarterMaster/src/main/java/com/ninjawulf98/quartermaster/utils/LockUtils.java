package com.ninjawulf98.quartermaster.utils;

import com.ninjawulf98.quartermaster.QuarterMaster;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.Date;
import java.util.UUID;

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

    public static boolean isCurrentlyLocked(Block block) {
        int x = block.getX();
        int y = block.getY();
        int z = block.getZ();
        Document filter = new Document("location", new Document("x", x).append("y", y).append("z", z));

        if (QuarterMaster.getDatabaseCollection().countDocuments(filter) == 1) {
            return true;
        }

        return false;
    }

    public static Player getWhoLocked(Block block) {
        int x = block.getX();
        int y = block.getY();
        int z = block.getZ();
        Document filter = new Document("location", new Document("x", x).append("y", y).append("z", z));

        String uuidString = QuarterMaster.getDatabaseCollection().find(filter).first().getString("uuid");
        UUID uuid = UUID.fromString(uuidString);

        return Bukkit.getPlayer(uuid);
    }

    public static void deleteLock (Block block) {
        int x = block.getX();
        int y = block.getY();
        int z = block.getZ();
        Document filter = new Document("location", new Document("x", x).append("y", y).append("z", z));

        QuarterMaster.getDatabaseCollection().deleteOne(filter);
        System.out.println("Lock deleted");
    }

    public static Document getLock(String id) {
        Document filter = new Document("_id", new ObjectId(id));

        return QuarterMaster.getDatabaseCollection().find(filter).first();
    }
}
