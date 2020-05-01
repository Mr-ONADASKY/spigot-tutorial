package com.ninjawulf98.cortexlobby.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class GoodWeatherListener implements Listener {

    @EventHandler
    public void onWeatherChanger(WeatherChangeEvent e) {
        e.setCancelled(true);
    }
}
