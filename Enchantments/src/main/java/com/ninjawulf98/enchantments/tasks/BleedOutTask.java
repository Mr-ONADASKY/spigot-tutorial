package com.ninjawulf98.enchantments.tasks;

import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;

public class BleedOutTask extends BukkitRunnable {

    LivingEntity victim;

    public BleedOutTask(LivingEntity victim) {
        this.victim = victim;
    }

    @Override
    public void run() {
        if(victim.getHealth() > 1) {
            victim.setHealth(victim.getHealth() - 1);
            System.out.println(victim.getHealth());
        }else if(victim.getHealth() == 1){
            victim.setHealth(victim.getHealth() - 1);
            this.cancel();
        }
    }
}
