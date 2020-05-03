package com.ninjawulf98.staffhomes;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StaffHomeCommand implements CommandExecutor {

    private final StaffHomes plugin;

    StaffHomeCommand(StaffHomes plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;

            if (p.hasPermission("staffhomes.use")) {
                if (plugin.getConfig().getBoolean("enable")) {
                    if (args.length == 1 && args[0].equalsIgnoreCase("set")) {
                        if (plugin.getConfig().isConfigurationSection("savedlocations." + p.getUniqueId())) {
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("override-message")) + Math.round(plugin.getConfig().getDouble("savedlocations." + p.getUniqueId() + ".x")) + " " + Math.round(plugin.getConfig().getDouble("savedlocations." + p.getUniqueId() + ".y")) + " " + Math.round(plugin.getConfig().getDouble("savedlocations." + p.getUniqueId() + ".z")));
                        }
                        saveLocation(p);
                    } else if (args.length == 1 && args[0].equalsIgnoreCase("return")) {
                        if (plugin.getConfig().isConfigurationSection("savedlocations." + p.getUniqueId())) {
                            Location return_location = new Location(p.getWorld(), plugin.getConfig().getDouble("savedlocations." + p.getUniqueId() + ".x"), plugin.getConfig().getDouble("savedlocations." + p.getUniqueId() + ".y"), plugin.getConfig().getDouble("savedlocations." + p.getUniqueId() + ".z"));
                            p.teleport(return_location);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("return-message")));
                            plugin.getConfig().set("savedlocations." + p.getUniqueId(), null);
                            plugin.saveConfig();
                        } else {
                            p.sendMessage(ChatColor.DARK_RED + "You never set a staff home");
                        }
                    } else if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
                        if (p.hasPermission("staffhomes.reload")) {
                            plugin.reloadConfig();
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("reload-message")));
                        } else {
                            p.sendMessage(ChatColor.DARK_RED + "You don't have permission to use this command");
                        }

                    } else if (args.length == 1) {
                        if (p.hasPermission("staffhomes.admin")) {
                            Player t = Bukkit.getPlayer(args[0]);
                            if (!(t == null)) {
                                if (plugin.getConfig().isConfigurationSection("savedlocations." + t.getUniqueId())) {
                                    p.sendMessage(ChatColor.DARK_GREEN + "Teleporting to temporary staff home(" + t.getName() + ") @: " + ChatColor.GRAY + Math.round(plugin.getConfig().getDouble("savedlocations." + t.getUniqueId() + ".x")) + " " + Math.round(plugin.getConfig().getDouble("savedlocations." + t.getUniqueId() + ".y")) + " " + Math.round(plugin.getConfig().getDouble("savedlocations." + t.getUniqueId() + ".z")));
                                    Location return_location = new Location(t.getWorld(), plugin.getConfig().getDouble("savedlocations." + t.getUniqueId() + ".x"), plugin.getConfig().getDouble("savedlocations." + t.getUniqueId() + ".y"), plugin.getConfig().getDouble("savedlocations." + t.getUniqueId() + ".z"));
                                    p.teleport(return_location);
                                } else {
                                    p.sendMessage(ChatColor.DARK_RED + "That player does not have a home set");
                                }
                            }
                        } else {
                            p.sendMessage(ChatColor.DARK_RED + "You don't have permission to use this command");
                        }
                    } else {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7==&a&lStaff&eHomes&7 by ninjawulf98=="));
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&o/staffhome set &7- &9Set a Temporary Home"));
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&o/staffhome return &7- &9Return to Home and Remove it"));
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&o/staffhome <name> &7- &9Teleport to a temporary home"));
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&o/staffhome reload &7- &9Reload the configuration"));
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7========================="));
                    }
                }
            } else {
                p.sendMessage(ChatColor.DARK_RED + "You don't have permission to use this command");
            }
        }

        return true;
    }

    private void saveLocation(Player p) {
        Location l = p.getLocation();
        // The original tutorial uses playername, which should not be used since the support for name changes has been added back in 1.7.10
        // Unique id should be used instead
        plugin.getConfig().createSection("savedlocations." + p.getUniqueId());
        plugin.getConfig().set("savedlocations." + p.getUniqueId() + ".x", l.getX());
        plugin.getConfig().set("savedlocations." + p.getUniqueId() + ".y", l.getY());
        plugin.getConfig().set("savedlocations." + p.getUniqueId() + ".z", l.getZ());
        plugin.saveConfig();
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("set-message")) + Math.round(plugin.getConfig().getDouble("savedlocations." + p.getUniqueId() + ".x")) + " " + Math.round(plugin.getConfig().getDouble("savedlocations." + p.getUniqueId() + ".y")) + " " + Math.round(plugin.getConfig().getDouble("savedlocations." + p.getUniqueId() + ".z")));
    }
}
