package ninjawulf98.spigottutorial2.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class OnDeath implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent e){
        e.getEntity().sendMessage("Noob Noob");
        e.getEntity().setFlying(true);
    }
}
