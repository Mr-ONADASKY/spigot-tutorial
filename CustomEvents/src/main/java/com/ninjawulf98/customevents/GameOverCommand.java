package com.ninjawulf98.customevents;

import com.ninjawulf98.customevents.events.GameEndEvent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GameOverCommand implements CommandExecutor {
	
	@Override
	public boolean onCommand (CommandSender sender, Command command, String label, String[] args) {
		
		if (sender instanceof Player) {
			Player player = (Player) sender;
			
			if(args.length == 1) {
				Player target = Bukkit.getPlayer(args[0]);
				Bukkit.getServer().getPluginManager().callEvent(new GameEndEvent(player, target, 42));
			}
			
		}
		
		return true;
	}
}
