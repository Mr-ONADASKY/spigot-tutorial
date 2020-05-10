package com.ninjawulf98.commandmanager.commands;

import com.ninjawulf98.commandmanager.commands.subcommands.ExplodeCommand;
import com.ninjawulf98.commandmanager.commands.subcommands.FreezeCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class PrankManager implements CommandExecutor {

    private ArrayList<SubCommand> subCommands = new ArrayList<>();

    public PrankManager(){
        subCommands.add(new ExplodeCommand());
        subCommands.add(new FreezeCommand());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {
            Player p = (Player) sender;

            if(args.length > 0) {
                for (SubCommand subCommand: subCommands) {
                    if(args[0].equalsIgnoreCase(subCommand.getName())){
                        subCommand.perform(p, args);
                    }
                }
            }else if (args.length == 0){
                p.sendMessage("---------------------------------");
                for (SubCommand subCommand: subCommands) {
                    p.sendMessage(subCommand.getSyntax() + " - " + subCommand.getDescription());
                }
                p.sendMessage("---------------------------------");

            }
        }

        return true;
    }

    public ArrayList<SubCommand> getSubCommands() {
        return subCommands;
    }
}
