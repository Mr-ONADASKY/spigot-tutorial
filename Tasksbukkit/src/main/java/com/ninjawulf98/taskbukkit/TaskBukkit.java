package com.ninjawulf98.taskbukkit;

import com.ninjawulf98.taskbukkit.tasks.CoolTask;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

public final class TaskBukkit extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        BukkitTask coolTask = new CoolTask(this).runTaskLater(this, 20l);
    }
}
