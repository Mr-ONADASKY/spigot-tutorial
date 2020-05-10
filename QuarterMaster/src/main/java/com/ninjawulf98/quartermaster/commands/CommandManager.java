package com.ninjawulf98.quartermaster.commands;

import com.ninjawulf98.quartermaster.commands.subcommands.HelpCommand;
import com.ninjawulf98.quartermaster.commands.subcommands.ManagerCommand;
import com.ninjawulf98.quartermaster.commands.subcommands.LockCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CommandManager implements CommandExecutor {

    ArrayList<SubCommand> subCommands = new ArrayList<>();

    public CommandManager() {
        this.subCommands.add(new LockCommand());
        this.subCommands.add(new ManagerCommand());
        this.subCommands.add(new HelpCommand());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {

            Player p = (Player) sender;

            if (args.length > 0) {
                for(SubCommand subCommand: subCommands){
                    if(args[0].equalsIgnoreCase(subCommand.getName())){
                        subCommand.perform(p, args);
                    }
                }
            }else if(args.length == 0) {
                HelpCommand help = new HelpCommand();
                help.perform(p, args);
            }
        }

        return true;
    }

    public ArrayList<SubCommand> getSubCommands() {
        return subCommands;
    }
}
