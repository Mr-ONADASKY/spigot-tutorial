package com.ninjawulf98.placeholderapiplugin;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;

public class SpigotExpansion extends PlaceholderExpansion {


    @Override
    public String getIdentifier() {
        return "spigottutorial";
    }

    @Override
    public String getAuthor() {
        return "ninjawulf98";
    }

    @Override
    public String getVersion() {
        return "1.0";
    }

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String onPlaceholderRequest(Player p, String params) {
        if(p == null) {
            return "";
        }

        if(params.equals("isvanished")) {
            return String.valueOf(PlaceHolderApiPlugin.getInstance().vanished.contains(p));
        }

        return null;
    }
}
