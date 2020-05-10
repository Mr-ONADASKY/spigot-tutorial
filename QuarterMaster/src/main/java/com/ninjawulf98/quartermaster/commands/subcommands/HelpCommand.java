package com.ninjawulf98.quartermaster.commands.subcommands;

import com.ninjawulf98.quartermaster.commands.CommandManager;
import com.ninjawulf98.quartermaster.commands.SubCommand;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class HelpCommand extends SubCommand {

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "Show all of the commands for QuarterMaster";
    }

    @Override
    public String getSyntax() {
        return "/qm help";
    }

    @Override
    public void perform(Player p, String[] args) {

        CommandManager commandManager = new CommandManager();

        p.sendMessage(ChatColor.DARK_RED + "=======" + ChatColor.BLUE + ChatColor.BOLD + "Quarter" + ChatColor.RED + ChatColor.ITALIC + "Master " + ChatColor.YELLOW + "Commands " + ChatColor.DARK_RED + "=======");

        for (SubCommand subCommand: commandManager.getSubCommands()){
            p.sendMessage(ChatColor.YELLOW + subCommand.getSyntax() + " - " + ChatColor.GRAY + ChatColor.ITALIC + subCommand.getDescription());
        }
        p.sendMessage(ChatColor.DARK_RED + "=========================================");
    }
}
