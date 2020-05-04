package com.ninjawulf98.vaulteconomy.listeners;

import com.ninjawulf98.vaulteconomy.VaultEconomy;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {

    @EventHandler
    public  void onBlockBreeak(BlockBreakEvent e){
        Economy eco = VaultEconomy.getEconomy();
        Player p = e.getPlayer();

        EconomyResponse response = eco.depositPlayer(p, 5.0);
        if(response.transactionSuccess()){
            p.sendMessage(ChatColor.GREEN + eco.format(response.amount) + " added.");
        }
    }
}
