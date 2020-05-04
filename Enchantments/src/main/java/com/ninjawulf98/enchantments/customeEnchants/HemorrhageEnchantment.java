package com.ninjawulf98.enchantments.customeEnchants;

import com.ninjawulf98.enchantments.Enchantments;
import com.ninjawulf98.enchantments.tasks.BleedOutTask;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;

public class HemorrhageEnchantment extends Enchantment implements Listener {

    public HemorrhageEnchantment(String namespace) {
        super(new NamespacedKey(Enchantments.getPlugin(), namespace));
    }

    @EventHandler
    public void onPlayerHit(EntityDamageByEntityEvent e){
        if (e.getDamager() instanceof Player) {
            Player player = ((Player) e.getDamager()).getPlayer();

            ItemStack item = player.getInventory().getItemInMainHand();
            if (item.getEnchantments().containsKey(Enchantment.getByKey(Enchantments.hemorrhageEnchantment.getKey()))){

                LivingEntity victim = (LivingEntity) e.getEntity();
                player.sendMessage(ChatColor.DARK_RED + "You have just hemorrhaged your target.");
                player.sendMessage(ChatColor.DARK_RED + "They are going to bleed out. And die.");

                BukkitTask task = new BleedOutTask(victim).runTaskTimer(Enchantments.getPlugin(), 0L, 100L);

            }
        }
    }

    @Override
    public String getName() {
        return "Hemorrhage";
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }

    @Override
    public int getStartLevel() {
        return 1;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.WEAPON;
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
