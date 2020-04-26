package com.ninjawulf98.mongoplugin;

import com.mongodb.Block;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bukkit.plugin.java.JavaPlugin;

public final class MongoPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
    MongoClient mongoClient = MongoClients.create("mongodb://minecraft:minecraft1@ds014808.mlab.com:14808/minecraft-mongodb-tutorial?retryWrites=false");
        MongoCollection<Document> collection = mongoClient.getDatabase("minecraft-mongodb-tutorial").getCollection("users");

        System.out.println("Connected to database");

        Document document1 = new Document("name", "bob");

        collection.insertOne(document1);

        collection.find().forEach(printBlock);
    }

    Block<Document> printBlock = new Block<Document>()  {

        @Override
        public void apply(final Document document) {
            System.out.println(document.toJson());
        }
    };
}
