package com.ninjawulf98.quartermaster.utils;

import com.ninjawulf98.quartermaster.QuarterMaster;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import javax.print.Doc;
import java.util.ArrayList;
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
                .append("creation-date", new Date())
                .append("access", new ArrayList<String>());

        QuarterMaster.getDatabaseCollection().insertOne(lock);
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

    public static void deleteLock(String lockID) {
        Document lock = LockUtils.getLock(lockID);

        QuarterMaster.getDatabaseCollection().deleteOne(lock);
    }

    public static Document getLock(String id) {
        Document filter = new Document("_id", new ObjectId(id));

        return QuarterMaster.getDatabaseCollection().find(filter).first();
    }

    public static void fillEmptyMenuTiles (Inventory menu) {
        for (int i = 0; i < menu.getSize(); i++){
            if (menu.getItem(i) == null) {
                menu.setItem(i, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
            }
        }
    }

    public static void addPlayerToLock(String lockID, Player playerToAdd) {
        Document lock = LockUtils.getLock(lockID);

        ArrayList<String> accessList =  (ArrayList<String>) lock.get("access");
        accessList.add(playerToAdd.getUniqueId().toString());

        Document newDoc = new Document("access", accessList);
        Document newDoc2 = new Document("$set", newDoc);

        Document filter = new Document("_id", lock.getObjectId("_id"));
        QuarterMaster.getDatabaseCollection().updateOne(filter, newDoc2);

    }

    public static void removePlayerFromLock(String lockID, Player playerToAdd) {
        Document lock = LockUtils.getLock(lockID);

        ArrayList<String> accessList =  (ArrayList<String>) lock.get("access");
        accessList.remove(playerToAdd.getUniqueId().toString());

        Document newDoc = new Document("access", accessList);
        Document newDoc2 = new Document("$set", newDoc);

        Document filter = new Document("_id", lock.getObjectId("_id"));
        QuarterMaster.getDatabaseCollection().updateOne(filter, newDoc2);

    }
}
