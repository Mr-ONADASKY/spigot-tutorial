package com.ninjawulf98.commandmanager.commands.subcommands;

import com.ninjawulf98.commandmanager.commands.SubCommand;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class ExplodeCommand extends SubCommand {

    @Override
    public String getName() {
        return "explode";
    }

    @Override
    public String getDescription() {
        return "Explode a player";
    }

    @Override
    public String getSyntax() {
        return "/prank explode <player name>";
    }

    @Override
    public void perform(Player player, String[] args) {
        if(args.length > 1) {
            Player target = Bukkit.getPlayer(args[1]);

            player.sendMessage("You successfully bombed" + target.getDisplayName());

            target.playSound(target.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1, 1);
            target.setHealth(0);

            target.sendMessage("You jus got exploded");
        }else if(args.length == 1) {
            player.sendMessage("You did not provide a name!");
            player.sendMessage("Do it like this: /prank explode ninjawulf98");
        }
    }
}
