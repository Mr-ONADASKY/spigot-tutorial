package com.ninjawulf98.taskbukkit.tasks;


import com.ninjawulf98.taskbukkit.TaskBukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class CoolTask extends BukkitRunnable {

    TaskBukkit plugin;
    String message;

    public CoolTask(TaskBukkit plugin, String message) {
        this.plugin = plugin;
        this.message = message;
    }

    @Override
    public void run() {
    System.out.println(message);
    }

}
