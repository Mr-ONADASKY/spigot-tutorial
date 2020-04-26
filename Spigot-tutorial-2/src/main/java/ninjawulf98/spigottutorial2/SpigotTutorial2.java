package ninjawulf98.spigottutorial2;

import ninjawulf98.spigottutorial2.events.OnDeath;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedLeaveEvent;
import org.bukkit.event.player.PlayerShearEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.text.MessageFormat;

public final class SpigotTutorial2 extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
      System.out.println("Initializing plugin");
              getServer().getPluginManager().registerEvents(new OnDeath(), this);
//      getServer().getPluginManager().registerEvents(this, this);
    }
//
//    @EventHandler
//    public void onLeaveBed(PlayerBedLeaveEvent event){
//        Player player = event.getPlayer();
//        String message = MessageFormat.format("Hey sleepy sleepy {0}, wanna have some candy?",  player.getName().toString());
//
//        player.sendMessage(message);
//    }
//
//    @EventHandler
//    public void onShearSheep(PlayerShearEntityEvent event) {
//        event.setCancelled(true);
//        event.getPlayer().sendMessage(ChatColor.BLUE + "Nice try!!!");
//    }
}
