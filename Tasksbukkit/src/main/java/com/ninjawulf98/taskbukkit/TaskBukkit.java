package com.ninjawulf98.taskbukkit;

import com.ninjawulf98.taskbukkit.tasks.CoolTask;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

public final class TaskBukkit extends JavaPlugin {
    private final long secondInTicks = 20L;

    @Override
    public void onEnable() {
        // Plugin startup logic
        BukkitTask delayTask = new CoolTask(this, "Running task after 5 seconds...").runTaskLater(this, this.secondInTicks * 5);
        BukkitTask timerTask = new CoolTask(this, "Time to float!").runTaskTimer(this, 0L,this.secondInTicks * 5);

        BukkitScheduler scheduler = getServer().getScheduler();
        scheduler.runTaskLater(this,        new Runnable()

        {
            @Override
            public void run() {
                System.out.println("Running anonymous class in scheduler");
            }
        },secondInTicks);

    }
}
