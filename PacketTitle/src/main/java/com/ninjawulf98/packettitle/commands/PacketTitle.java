package com.ninjawulf98.packettitle.commands;

import net.minecraft.server.v1_15_R1.IChatBaseComponent;
import net.minecraft.server.v1_15_R1.PacketPlayOutTitle;
import net.minecraft.server.v1_15_R1.PlayerConnection;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class PacketTitle implements CommandExecutor {
	
	@Override
	public boolean onCommand (CommandSender sender, Command command, String label, String[] args) {
		Player player = (Player) sender;
		
		if(args.length > 1) {
			Player target = Bukkit.getPlayer(args[0]);
			if(target == null){
				player.sendMessage(ChatColor.RED + args[0] + " is not a player");
				
				return true;
			}
			
			StringBuilder sb = new StringBuilder();
			for(int i = 1; i < args.length; i++) {
				sb.append(args[i] + " ");
			}
			
			String titleString = sb.toString().trim();
			String title = ChatColor.translateAlternateColorCodes('&', titleString);
			
			PlayerConnection connection = ((CraftPlayer) target.getPlayer()).getHandle().playerConnection;
			IChatBaseComponent text = IChatBaseComponent.ChatSerializer.a("{'text': '" + title + "'}");
			PacketPlayOutTitle packet = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, text, 1, 80, 123);
			connection.sendPacket(packet);
			player.sendMessage(target.getName() + " was send the title, " + title);
		}
		
		
		return true;
	}
}
