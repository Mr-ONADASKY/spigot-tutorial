package com.ninjawulf98.armorstands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;

public class ArmorStandCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player){
            Player player = (Player) sender;

            ArmorStand armorstand =  (ArmorStand) player.getWorld().spawnEntity(player.getLocation(), EntityType.ARMOR_STAND);
            armorstand.setHelmet(new ItemStack(Material.JUNGLE_PLANKS));
            armorstand.setInvulnerable(true);
            armorstand.setGlowing(true);
            armorstand.setItemInHand(new ItemStack(Material.DIAMOND_AXE));
            armorstand.setArms(true);
            armorstand.setBodyPose(new EulerAngle(Math.toRadians(131), Math.toRadians(106), Math.toRadians(79)));
            armorstand.setHeadPose(new EulerAngle(Math.toRadians(90), 0,0));
            armorstand.setLeftArmPose(new EulerAngle(0, 0, Math.toRadians(270)));
            armorstand.setRightArmPose(new EulerAngle(0, 0, Math.toRadians(90)));

        }

        return true;
    }
}
