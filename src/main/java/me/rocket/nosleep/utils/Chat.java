package me.rocket.nosleep.utils;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;

public class Chat {

    public Chat getInstance() {
        return this;
    }

    public String translate(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public String translate(String message, char c) {
        return ChatColor.translateAlternateColorCodes(c, message);
    }

    public void log(String message) {
        System.out.println(translate(message));
    }

    public void send(Player player, String message) {
        player.sendMessage(translate(message));
    }

}
