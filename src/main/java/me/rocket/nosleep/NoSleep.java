package me.rocket.nosleep;

import me.rocket.nosleep.commands.CommandTest;
import me.rocket.nosleep.listeners.Listeners;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class NoSleep extends JavaPlugin {

    private static NoSleep instance;
    private boolean isActive = false;

    private String explosivePower = getConfig().getString("explosivePower");

    public String getExplosivePower() {
        return explosivePower;
    }

    public void setExplosivePower(String explosivePower) {
        this.explosivePower = explosivePower;
    }

    public static NoSleep getInstance() {
        return instance;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActivated(boolean state) {
        isActive = state;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        PluginManager pm = Bukkit.getServer().getPluginManager();
        instance = this;

        pm.registerEvents(new Listeners(), this);
        getCommand("nosleep").setExecutor(new CommandTest());

        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
