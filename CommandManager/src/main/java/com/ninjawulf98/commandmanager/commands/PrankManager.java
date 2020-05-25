package com.ninjawulf98.commandmanager.commands;

import com.ninjawulf98.commandmanager.commands.subcommands.ExplodeCommand;
import com.ninjawulf98.commandmanager.commands.subcommands.FreezeCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PrankManager implements TabExecutor {

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

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        if(args.length == 1){
            ArrayList<String> subcommandsArguments = new ArrayList<>();

            for(SubCommand subCommand: subCommands) {
                subcommandsArguments.add(subCommand.getName());
            }

            return subcommandsArguments;
        }else if(args.length == 2){
            for(SubCommand subCommand: subCommands) {
                if(args[0].equalsIgnoreCase(subCommand.getName())){
                   return subCommand.getSubcommandArguments((Player) sender, args);
                }
            }
        }

        return null;
    }
}
