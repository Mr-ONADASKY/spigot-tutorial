package com.ninjawulf98.enchantments.customeEnchants;

import com.ninjawulf98.enchantments.Enchantments;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class GlowEnchantment extends Enchantment implements Listener {

    public GlowEnchantment(String namespace) {
        super(new NamespacedKey(Enchantments.getPlugin(), namespace));

    }

    @EventHandler
    public void onPlayerHit(EntityDamageByEntityEvent e){
        if(e.getDamager() instanceof Player) {
            Player player = (Player) e.getDamager();

            if (player.getEquipment().getChestplate().getEnchantments().containsKey(Enchantment.getByKey(Enchantment.getByKey(Enchantments.glowEnchantment.getKey()).getKey()))){
                e.getEntity().setGlowing(true);
            }
        }
    }

    @Override
    public String getName() {
        return "Glow";
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public int getStartLevel() {
        return 1;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.ARMOR;
    }

    @Override
    public boolean isTreasure() {
        return false;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    @Override
    public boolean conflictsWith(Enchantment other) {
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        return true;
    }
}
