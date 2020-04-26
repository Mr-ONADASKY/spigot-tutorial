package com.ninjawulf98.taskbukkit.tasks;


import com.ninjawulf98.taskbukkit.TaskBukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class CoolTask extends BukkitRunnable {

    TaskBukkit plugin;

    public CoolTask(TaskBukkit plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
    System.out.println("Running cool task...");
    }

}
