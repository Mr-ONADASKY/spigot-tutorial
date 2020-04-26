package com.ninjawulf98.armorstandgui;

import com.ninjawulf98.armorstandgui.commands.ArmorStandCommand;
import com.ninjawulf98.armorstandgui.events.MenuHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;

public final class ArmorStandGUI extends JavaPlugin {

    public HashMap<Player, ArmorStand> armorstands = new HashMap<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("The plugin has started up.");

        getCommand("armorstand").setExecutor(new ArmorStandCommand(this));

        getServer().getPluginManager().registerEvents(new MenuHandler(this), this);

        getConfig().options().copyDefaults();
        saveDefaultConfig();
    }

    public void openMainMenu(Player player) {
        Inventory main_menu = Bukkit.createInventory(player, 9, ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("main-menu")));

        //Options for main menu
        ItemStack create = new ItemStack(Material.ARMOR_STAND);
        ItemMeta create_meta = create.getItemMeta();
        create_meta.setDisplayName(ChatColor.GREEN + "Create");
        ArrayList<String> create_lore = new ArrayList<>();
        create_lore.add(ChatColor.DARK_PURPLE + "Create a new armor stand.");
        create_meta.setLore(create_lore);
        create.setItemMeta(create_meta);

        ItemStack close = new ItemStack(Material.BARRIER);
        ItemMeta close_meta = close.getItemMeta();
        close_meta.setDisplayName(ChatColor.RED + "Close");
        close.setItemMeta(close_meta);

        main_menu.setItem(0, create);
        main_menu.setItem(8, close);
        player.openInventory(main_menu);
    }

    public void openCreateMenu(Player player) {
        Inventory create_menu = Bukkit.createInventory(player, 9, ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("create-menu")));

        ItemStack arms = new ItemStack(Material.ARMOR_STAND);
        ItemStack glow = new ItemStack(Material.BEACON);
        ItemStack armor = new ItemStack(Material.LEATHER_CHESTPLATE);
        ItemStack base = new ItemStack(Material.STONE_SLAB);
        ItemStack complete = new ItemStack(Material.GREEN_WOOL);
        ItemStack cancel = new ItemStack(Material.RED_WOOL);

        ItemMeta arms_meta = arms.getItemMeta();
        arms_meta.setDisplayName(ChatColor.YELLOW + "Arms");
        ItemMeta glow_meta = glow.getItemMeta();
        glow_meta.setDisplayName("Glow");
        ItemMeta armor_meta = armor.getItemMeta();
        armor_meta.setDisplayName(ChatColor.AQUA + "Armor");
        ItemMeta base_meta = base.getItemMeta();
        base_meta.setDisplayName(ChatColor.GOLD + "Base");
        ItemMeta complete_meta = complete.getItemMeta();
        complete_meta.setDisplayName(ChatColor.GREEN + "Complete & Create");
        ItemMeta cancel_meta = cancel.getItemMeta();
        cancel_meta.setDisplayName(ChatColor.RED + "Cancel creation");

        arms.setItemMeta(arms_meta);
        glow.setItemMeta(glow_meta);
        armor.setItemMeta(armor_meta);
        base.setItemMeta(base_meta);
        complete.setItemMeta(complete_meta);
        cancel.setItemMeta(cancel_meta);

        create_menu.setItem(0, arms);
        create_menu.setItem(1, glow);
        create_menu.setItem(2, armor);
        create_menu.setItem(3, base);
        create_menu.setItem(7, complete);
        create_menu.setItem(8, cancel);

        player.openInventory(create_menu);
    }

    public void openConfirmMenu(Player player, Material option) {
        Inventory confirm_menu = Bukkit.createInventory(player, 36, ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("confirm-menu")));

        ItemStack option_item = new ItemStack(option);
        ItemMeta option_meta = option_item.getItemMeta();

        switch(option){
            case ARMOR_STAND:
                option_meta.setDisplayName(ChatColor.YELLOW + "Give arms?");
                option_item.setItemMeta(option_meta);
                break;
            case BEACON:
                option_meta.setDisplayName(ChatColor.YELLOW + "Add glow?");
                option_item.setItemMeta(option_meta);
                break;
        }

        ItemStack yes = new ItemStack(Material.GREEN_WOOL);
        ItemMeta yes_meta = yes.getItemMeta();
        yes_meta.setDisplayName(ChatColor.GREEN + "YES");
        yes.setItemMeta(yes_meta);
        ItemStack no = new ItemStack(Material.RED_WOOL);
        ItemMeta no_meta = no.getItemMeta();
        no_meta.setDisplayName(ChatColor.RED + "NO");
        no.setItemMeta(no_meta);

        confirm_menu.setItem(13, option_item);
        confirm_menu.setItem(21, yes);
        confirm_menu.setItem(23, no);
        player.openInventory(confirm_menu);
    }

    public void openArmorMenu(Player player) {
        Inventory armorMenu = Bukkit.createInventory(player, 45, ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("armor-menu")));

        ItemStack head = new ItemStack(Material.DIAMOND_HELMET);
        ItemStack body = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemStack legs = new ItemStack(Material.DIAMOND_LEGGINGS);
        ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);

        ItemStack confirm = new ItemStack(Material.GREEN_WOOL);
        ItemMeta confirm_meta = confirm.getItemMeta();
        confirm_meta.setDisplayName(ChatColor.GREEN + "Done");
        confirm.setItemMeta(confirm_meta);

        armorMenu.setItem(11, head);
        armorMenu.setItem(12, body);
        armorMenu.setItem(14, legs);
        armorMenu.setItem(15, boots);
        armorMenu.setItem(40, confirm);

        player.openInventory(armorMenu);
    }

}
