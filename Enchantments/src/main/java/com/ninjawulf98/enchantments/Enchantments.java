package com.ninjawulf98.enchantments;

import com.ninjawulf98.enchantments.customeEnchants.GlowEnchantment;
import com.ninjawulf98.enchantments.customeEnchants.HemorrhageEnchantment;
import com.ninjawulf98.enchantments.listeners.JoinListener;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

public final class Enchantments extends JavaPlugin {

    private static Enchantments plugin;

    public static ArrayList<Enchantment> custom_enchants = new ArrayList<>();
    public static GlowEnchantment glowEnchantment;
    public static HemorrhageEnchantment hemorrhageEnchantment;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        glowEnchantment = new GlowEnchantment("glow");
        hemorrhageEnchantment = new HemorrhageEnchantment("hemorrhage");

        custom_enchants.add(glowEnchantment);
        custom_enchants.add(hemorrhageEnchantment);

        registerEnchantment(glowEnchantment);
        registerEnchantment(hemorrhageEnchantment);

        this.getServer().getPluginManager().registerEvents(glowEnchantment, this);
        this.getServer().getPluginManager().registerEvents(hemorrhageEnchantment, this);

        this.getServer().getPluginManager().registerEvents(new JoinListener(),this);
    }

    @Override
    public void onDisable() {
        // Disable the Power enchantment
        try {
            Field keyField = Enchantment.class.getDeclaredField("byKey");

            keyField.setAccessible(true);
            @SuppressWarnings("unchecked")
            HashMap<NamespacedKey, Enchantment> byKey = (HashMap<NamespacedKey, Enchantment>) keyField.get(null);


             for(Enchantment enchantment: custom_enchants){
                 if(byKey.containsKey(enchantment.getKey())) {
                     byKey.remove(enchantment.getKey());
                 }
             }

            Field nameField = Enchantment.class.getDeclaredField("byName");

            nameField.setAccessible(true);
            @SuppressWarnings("unchecked")
            HashMap<String, Enchantment> byName = (HashMap<String, Enchantment>) nameField.get(null);

            for(Enchantment enchantment: custom_enchants){
                if(byName.containsKey(enchantment.getName())) {
                    byName.remove(enchantment.getName());
                }
            }

        } catch (Exception ignored) { }
    }

    public static Enchantments getPlugin () {
        return plugin;
    }

    public static void registerEnchantment(Enchantment enchantment){
        boolean registered = true;
        try {
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null, true);
            Enchantment.registerEnchantment(enchantment);
        } catch (Exception e) {
            registered = false;
            e.printStackTrace();
        }

        if(registered){

        }
    }
}
