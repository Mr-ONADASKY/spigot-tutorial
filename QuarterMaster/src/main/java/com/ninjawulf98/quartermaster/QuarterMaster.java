package com.ninjawulf98.quartermaster;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.ninjawulf98.quartermaster.commands.CommandManager;
import com.ninjawulf98.quartermaster.listeners.MenuListeners;
import com.ninjawulf98.quartermaster.listeners.ChestListeners;
import com.ninjawulf98.quartermaster.utils.LockMenuSystem;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class QuarterMaster extends JavaPlugin {

    private static QuarterMaster plugin;

    private MongoClient mongoClient;
    private MongoDatabase database;
    private static MongoCollection<Document> col;

    public static HashMap<Player, LockMenuSystem> lockMenuSystemHashMap = new HashMap<>();

    @Override
    public void onEnable() {

        plugin = this;
        //Setup config
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        if(getConfig().getString("connection-string").isEmpty()){
            System.out.println("[QuarterMaster] - You need to specify a mongodb connection string in the config.ym;");

        }else {
            mongoClient = MongoClients.create(getConfig().getString("connection-string"));
            database = mongoClient.getDatabase(getConfig().getString("database"));
            col = database.getCollection("locks");


            getCommand("quartermaster").setExecutor(new CommandManager());

            Bukkit.getPluginManager().registerEvents(new MenuListeners(), this);
            Bukkit.getPluginManager().registerEvents(new ChestListeners(), this);
        }


    }

    @Override
    public void onDisable() {
    }

    public static MongoCollection<Document> getDatabaseCollection() {
        return col;
    }

    public static LockMenuSystem getPlayerMenuSystem(Player p) {

        if(QuarterMaster.lockMenuSystemHashMap.containsKey(p)){
            return lockMenuSystemHashMap.get(p);
        } else {
            LockMenuSystem lockMenuSystem = new LockMenuSystem(p);
            lockMenuSystemHashMap.put(p, lockMenuSystem);

            return lockMenuSystem;
        }
    }

    public static  QuarterMaster getPlugin() {
        return plugin;
    }
}
