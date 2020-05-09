package com.ninjawulf98.quartermaster.utils;

import com.ninjawulf98.quartermaster.QuarterMaster;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.UUID;
import java.util.function.Consumer;

public class LockMenuSystem {

    Player p;

    private Inventory menu;
    private String lockID;
    private Block lockToCreate;

    private Player playerToAdd;
    private Player playerToRemove;

    public LockMenuSystem(Player p) {
        this.p = p;
    }

    public void showAskGUI(){
        menu = Bukkit.createInventory(p, 9, ChatColor.DARK_AQUA + "Lock chest?");
        ItemStack yes = new ItemStack(Material.TOTEM_OF_UNDYING, 1);
        ItemMeta yes_meta = yes.getItemMeta();
        yes_meta.setDisplayName(ChatColor.GREEN + "Yes");
        yes.setItemMeta(yes_meta);

        ItemStack no = new ItemStack(Material.BARRIER, 1);
        ItemMeta no_meta = no.getItemMeta();
        no_meta.setDisplayName(ChatColor.RED + "No");
        no.setItemMeta(no_meta);

        menu.setItem(3, yes);
        menu.setItem(5, no);

        LockUtils.fillEmptyMenuTiles(menu);
        p.openInventory(menu);
    }

    public void showLocksListGUI() {
        menu = Bukkit.createInventory(p, 54, ChatColor.DARK_RED + "Your locks:");

        String uuid  = p.getUniqueId().toString();
        Document filter = new Document("uuid", uuid);
        QuarterMaster.getDatabaseCollection().find(filter).forEach((Consumer <Document>) document -> {

            ItemStack lock = new ItemStack(Material.CHEST, 1);
            ItemMeta lock_meta = lock.getItemMeta();
            lock_meta.setDisplayName(ChatColor.GREEN + "Chest Lock");

            ArrayList<String> lore = new ArrayList<>();
            lore.add(ChatColor.GOLD + "-------------");
            lore.add(ChatColor.YELLOW + "Location:");

            Document location = (Document) document.get("location");

            lore.add(ChatColor.AQUA + "x: " + ChatColor.GREEN + location.getInteger("x"));
            lore.add(ChatColor.AQUA + "y: " + ChatColor.GREEN + location.getInteger("y"));
            lore.add(ChatColor.AQUA + "z: " + ChatColor.GREEN + location.getInteger("z"));
            lore.add("Date Created: " + document.getDate("creation-date").toString());
            lore.add(ChatColor.GOLD + "-------------");
            lore.add(document.getObjectId("_id").toString());

            lock_meta.setLore(lore);
            lock.setItemMeta(lock_meta);
            menu.addItem(lock);
        });

        LockUtils.fillEmptyMenuTiles(menu);
        p.openInventory(menu);
    }

    public void showLockManagerGUI() {
        menu = Bukkit.createInventory(p, 9, ChatColor.GOLD + "Lock Manager");
        Document lock = LockUtils.getLock(this.lockID);

        ItemStack manage_access = new ItemStack(Material.ARMOR_STAND, 1);
        ItemMeta access_meta = manage_access.getItemMeta();
        access_meta.setDisplayName(ChatColor.YELLOW + "Access Manager");
        ArrayList<String> access_lore = new ArrayList<>();
        access_lore.add(ChatColor.GREEN + "Manage who has access to this lock");
        access_meta.setLore(access_lore);
        manage_access.setItemMeta(access_meta);

        ItemStack delete_lock = new ItemStack(Material.WITHER_ROSE, 1);
        ItemMeta delete_meta = delete_lock.getItemMeta();
        delete_meta.setDisplayName(ChatColor.DARK_RED + "Delete Lock");
        ArrayList<String> delete_lore = new ArrayList<>();
        delete_lore.add(ChatColor.GREEN + "Deleting the lock will ");
        delete_lore.add(ChatColor.GREEN + "make the block totally unprotected.");
        delete_meta.setLore(delete_lore);
        delete_lock.setItemMeta(delete_meta);

        ItemStack totem = new ItemStack(Material.TOTEM_OF_UNDYING, 1);
        ItemMeta totem_meta = totem.getItemMeta();
        totem_meta.setDisplayName(ChatColor.GOLD + "Lock Manager");
        totem.setItemMeta(totem_meta);

        ItemStack lock_info = new ItemStack(Material.WRITABLE_BOOK, 1);
        ItemMeta info_meta = lock_info.getItemMeta();
        info_meta.setDisplayName(ChatColor.GREEN + "Lock Information");
        ArrayList<String> info_lore = new ArrayList<>();

        info_lore.add(ChatColor.GOLD + "-------------");
        info_lore.add(ChatColor.YELLOW + "Location:");

        Document location = (Document) lock.get("location");

        info_lore.add(ChatColor.AQUA + "  x: " + ChatColor.GREEN + location.getInteger("x"));
        info_lore.add(ChatColor.AQUA + "  y: " + ChatColor.GREEN + location.getInteger("y"));
        info_lore.add(ChatColor.AQUA + "  z: " + ChatColor.GREEN + location.getInteger("z"));
        info_lore.add("Date Created: " + lock.getDate("creation-date").toString());
        info_lore.add(ChatColor.GOLD + "-------------");

        info_meta.setLore(info_lore);
        lock_info.setItemMeta(info_meta);

        ItemStack close_menu = new ItemStack(Material.BARRIER, 1);
        ItemMeta close_meta = close_menu.getItemMeta();
        close_meta.setDisplayName(ChatColor.DARK_RED + "Close");
        ArrayList<String> close_lore = new ArrayList<>();
        close_lore.add(ChatColor.GREEN + "Go back the locks list");
        close_meta.setLore(close_lore);
        close_menu.setItemMeta(close_meta);

        //set the slots for the options
        menu.setItem(0, manage_access);
        menu.setItem(1, delete_lock);
        menu.setItem(4, totem);
        menu.setItem(7, lock_info);
        menu.setItem(8, close_menu);

        LockUtils.fillEmptyMenuTiles(menu);
        p.openInventory(menu);
    }

    public void showConfirmDeleteMenu() {
        menu = Bukkit.createInventory(p, 9, ChatColor.RED + "Confirm: Delete lock?");

        ItemStack yes = new ItemStack(Material.EMERALD, 1);
        ItemMeta yes_meta = yes.getItemMeta();
        yes_meta.setDisplayName(ChatColor.GREEN + "Yes");
        yes.setItemMeta(yes_meta);

        ItemStack no = new ItemStack(Material.BARRIER, 1);
        ItemMeta no_meta = yes.getItemMeta();
        no_meta.setDisplayName(ChatColor.DARK_RED + "No");
        no.setItemMeta(no_meta);

        menu.setItem(3, yes);
        menu.setItem(5, no);

        LockUtils.fillEmptyMenuTiles(menu);
        p.openInventory(menu);
    }

    public void showAccessManagerMenu() {
        menu = Bukkit.createInventory(p, 45, ChatColor.GREEN + "Access Manager");

        ItemStack remove = new ItemStack(Material.REDSTONE_BLOCK, 1);
        ItemMeta remove_meta = remove.getItemMeta();
        remove_meta.setDisplayName(ChatColor.DARK_RED + "Remove Player");
        ArrayList<String> remove_lore = new ArrayList<>();
        remove_lore.add(ChatColor.YELLOW + "Remove players from this lock");
        remove_meta.setLore(remove_lore);
        remove.setItemMeta(remove_meta);
        menu.setItem(13, remove);

        ItemStack players = new ItemStack(Material.PLAYER_HEAD, 1);
        ItemMeta players_meta = players.getItemMeta();
        players_meta.setDisplayName(ChatColor.AQUA + "View Players");
        ArrayList<String> players_lore = new ArrayList<>();
        players_lore.add(ChatColor.GREEN + "See who has access to your lock");
        players_meta.setLore(players_lore);
        players.setItemMeta(players_meta);
        menu.setItem(22, players);

        ItemStack add = new ItemStack(Material.ENDER_EYE, 1);
        ItemMeta add_meta = add.getItemMeta();
        add_meta.setDisplayName(ChatColor.GOLD + "Add Player to Lock");
        add.setItemMeta(add_meta);
        menu.setItem(31, add);

        ItemStack close = new ItemStack(Material.BARRIER, 1);
        ItemMeta close_meta = close.getItemMeta();
        close_meta.setDisplayName(ChatColor.DARK_RED + "Close");
        close.setItemMeta(close_meta);
        menu.setItem(44, close);

        LockUtils.fillEmptyMenuTiles(menu);
        p.openInventory(menu);
    }

    public void showPlayersWithAccessMenu() {
        menu = Bukkit.createInventory(p, 45, ChatColor.YELLOW  + "Players with Access to Lock");

        ArrayList<String> accesssList = (ArrayList<String>) LockUtils.getLock(this.lockID).get("access");

        if(accesssList.isEmpty()) {
            p.sendMessage(ChatColor.GRAY + "You have not added anyone to this lock.");
        } else {
            for (String s : accesssList) {
                UUID uuid = UUID.fromString(s);
                Player playerWithAccess = Bukkit.getPlayer(uuid);

                ItemStack player = new ItemStack(Material.PLAYER_HEAD, 1);
                ItemMeta player_meta = player.getItemMeta();
                player_meta.setDisplayName(playerWithAccess.getDisplayName());
                player.setItemMeta(player_meta);
                menu.addItem(player);
            }

            ItemStack close = new ItemStack(Material.BARRIER, 1);
            ItemMeta close_meta = close.getItemMeta();
            close_meta.setDisplayName(ChatColor.DARK_RED + "Close");
            close.setItemMeta(close_meta);
            menu.setItem(44, close);

            LockUtils.fillEmptyMenuTiles(menu);
            p.openInventory(menu);
        }
    }

    public void showPlayersToAddMenu() {
        menu = Bukkit.createInventory(p, 54, ChatColor.GREEN + "Choose a Player to Add:");

        ArrayList<Player> list = new ArrayList<>(p.getServer().getOnlinePlayers());
        for (int i = 0; i < list.size(); i++){
            if(!list.get(i).equals(p)) {
                ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD, 1);
                ItemMeta meta = playerHead.getItemMeta();

                meta.setDisplayName(list.get(i).getDisplayName());
                playerHead.setItemMeta(meta);

                menu.addItem(playerHead);
            }
        }

        ItemStack close = new ItemStack(Material.BARRIER, 1);
        ItemMeta close_meta = close.getItemMeta();
        close_meta.setDisplayName(ChatColor.DARK_RED + "Close");
        close.setItemMeta(close_meta);
        menu.setItem(53, close);

        LockUtils.fillEmptyMenuTiles(menu);
        p.openInventory(menu);
    }

    public void showPlayersToRemoveMenu() {
        menu = Bukkit.createInventory(p, 45, ChatColor.YELLOW  + "Choose a player to remove");

        ArrayList<String> accesssList = (ArrayList<String>) LockUtils.getLock(this.lockID).get("access");

        if(accesssList.isEmpty()) {
            p.sendMessage(ChatColor.GRAY + "You have not added anyone to this lock.");
        } else {
            for (String s : accesssList) {
                UUID uuid = UUID.fromString(s);
                Player playerWithAccess = Bukkit.getPlayer(uuid);

                ItemStack player = new ItemStack(Material.PLAYER_HEAD, 1);
                ItemMeta player_meta = player.getItemMeta();
                player_meta.setDisplayName(playerWithAccess.getDisplayName());
                player.setItemMeta(player_meta);
                menu.addItem(player);
            }

            ItemStack close = new ItemStack(Material.BARRIER, 1);
            ItemMeta close_meta = close.getItemMeta();
            close_meta.setDisplayName(ChatColor.DARK_RED + "Close");
            close.setItemMeta(close_meta);
            menu.setItem(44, close);

            LockUtils.fillEmptyMenuTiles(menu);
            p.openInventory(menu);
        }
    }

    public void showConfirmAddPlayerMenu() {
        menu = Bukkit.createInventory(p, 9, ChatColor.GREEN + "Confirm: Add Player");

        ItemStack yes = new ItemStack(Material.EMERALD, 1);
        ItemMeta yes_meta = yes.getItemMeta();
        yes_meta.setDisplayName(ChatColor.GREEN + "Yes");
        ArrayList<String> yes_lore = new ArrayList<>();
        yes_lore.add(ChatColor.AQUA + "Would you like to add");
        yes_lore.add(ChatColor.AQUA + "this player to your lock?");
        yes_meta.setLore(yes_lore);
        yes.setItemMeta(yes_meta);

        ItemStack no = new ItemStack(Material.BARRIER, 1);
        ItemMeta no_meta = yes.getItemMeta();
        no_meta.setDisplayName(ChatColor.DARK_RED + "No");
        no.setItemMeta(no_meta);

        menu.setItem(3, yes);
        menu.setItem(5, no);

        LockUtils.fillEmptyMenuTiles(menu);
        p.openInventory(menu);
    }

    public void showConfirmRemovePlayerMenu() {
        menu = Bukkit.createInventory(p, 9, ChatColor.RED + "Confirm: Remove Player");

        ItemStack yes = new ItemStack(Material.EMERALD, 1);
        ItemMeta yes_meta = yes.getItemMeta();
        yes_meta.setDisplayName(ChatColor.GREEN + "Yes");
        ArrayList<String> yes_lore = new ArrayList<>();
        yes_lore.add(ChatColor.AQUA + "Would you like to remove");
        yes_lore.add(ChatColor.AQUA + "this player from your lock?");
        yes_meta.setLore(yes_lore);
        yes.setItemMeta(yes_meta);

        ItemStack no = new ItemStack(Material.BARRIER, 1);
        ItemMeta no_meta = yes.getItemMeta();
        no_meta.setDisplayName(ChatColor.DARK_RED + "No");
        no.setItemMeta(no_meta);

        menu.setItem(3, yes);
        menu.setItem(5, no);

        LockUtils.fillEmptyMenuTiles(menu);
        p.openInventory(menu);
    }


    public Inventory getMenu() {
        return menu;
    }

    public Block getLockToCreate() {
        return lockToCreate;
    }

    public void setLockToCreate(Block lockToCreate) {
        this.lockToCreate = lockToCreate;
    }

    public String getLockID() {
        return lockID;
    }

    public void setLockID(String lockID) {
        this.lockID = lockID;
    }

    public Player getPlayerToAdd() {
        return playerToAdd;
    }

    public void setPlayerToAdd(Player playerToAdd) {
        this.playerToAdd = playerToAdd;
    }

    public Player getPlayerToRemove() {
        return playerToRemove;
    }

    public void setPlayerToRemove(Player playerToRemove) {
        this.playerToRemove = playerToRemove;
    }
}
