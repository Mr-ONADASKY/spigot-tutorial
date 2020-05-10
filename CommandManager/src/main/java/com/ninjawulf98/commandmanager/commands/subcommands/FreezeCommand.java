package com.ninjawulf98.commandmanager.commands.subcommands;

import com.ninjawulf98.commandmanager.commands.SubCommand;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class FreezeCommand extends SubCommand {
    @Override
    public String getName() {
        return "freeze";
    }

    @Override
    public String getDescription() {
        return "Freeze a player";
    }

    @Override
    public String getSyntax() {
        return "/prank freeze <player>";
    }

    @Override
    public void perform(Player player, String[] args) {
        if(args.length > 1) {
            Player target = Bukkit.getPlayer(args[1]);

            target.setWalkSpeed(0);

            target.sendMessage("You have just been frozen");

        }else if(args.length == 1) {
            player.sendMessage("You did not provide a name!");
            player.sendMessage("Do it like this: /prank freeze ninjawulf98");
        }
    }
}
