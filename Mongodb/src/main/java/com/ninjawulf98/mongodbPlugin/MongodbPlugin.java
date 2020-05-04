package com.ninjawulf98.mongodbPlugin;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.function.Consumer;


public final class MongodbPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        MongoClient mongoClient = MongoClients.create("mongodb://mongodb:mongodb@localhost:27017");
        MongoDatabase database = mongoClient.getDatabase("spigot-plugin");
        MongoCollection<Document> col = database.getCollection("vehicles");

        Document filter = new Document("wheels", 4);
        col.find(filter).forEach((Consumer<Document>) document -> {
            System.out.println(document.toJson());
        });


        System.out.println("Plugin has started up");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
