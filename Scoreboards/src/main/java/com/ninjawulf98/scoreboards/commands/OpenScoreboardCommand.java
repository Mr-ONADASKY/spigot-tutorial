package com.ninjawulf98.scoreboards.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public class OpenScoreboardCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;

            ScoreboardManager manager = Bukkit.getScoreboardManager();
            Scoreboard scoreboard  = manager.getNewScoreboard();

            Objective objective = scoreboard.registerNewObjective("test", "dummy", ChatColor.BLUE + "Scoreboard title");
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);

            Score score = objective.getScore(ChatColor.GOLD + "Money: $" + ChatColor.GREEN + 1);
            Score s2 = objective.getScore("");
            Score s1 = objective.getScore(ChatColor.DARK_PURPLE + "https://google.com");
            score.setScore(3);
            s2.setScore(2);
            s1.setScore(1);

            player.setScoreboard(scoreboard);
        }

        return true;
    }
}
