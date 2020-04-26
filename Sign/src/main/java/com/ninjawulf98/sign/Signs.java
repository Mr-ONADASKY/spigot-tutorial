package com.ninjawulf98.sign;

import com.ninjawulf98.sign.commands.Sign;
import com.ninjawulf98.sign.events.SignEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Signs extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        getServer().getPluginManager().registerEvents(new SignEvent(), this);
        getCommand("sign").setExecutor(new Sign());
    }

}
