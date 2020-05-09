package com.ninjawulf98.quartermaster;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.ninjawulf98.quartermaster.commands.LockCommand;
import com.ninjawulf98.quartermaster.listeners.MenuListener;
import com.ninjawulf98.quartermaster.listeners.OpenChestListener;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class QuarterMaster extends JavaPlugin {

    private MongoClient mongoClient;
    private MongoDatabase database;
    private static MongoCollection<Document> col;

    public static HashMap<Player, Block> Locks_being_created = new HashMap<>();

    @Override
    public void onEnable() {

        mongoClient = MongoClients.create("mongodb://mongodb:mongodb@localhost:27017");
        database = mongoClient.getDatabase("quartermaster");
        col = database.getCollection("locks");


        getCommand("lock").setExecutor(new LockCommand());
        Bukkit.getPluginManager().registerEvents(new MenuListener(), this);
        Bukkit.getPluginManager().registerEvents(new OpenChestListener(), this);
    }

    @Override
    public void onDisable() {
    }

    public static MongoCollection<Document> getDatabaseCollection() {
        return col;
    }
}
