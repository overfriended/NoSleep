package me.rocket.nosleep.listeners;

import me.rocket.nosleep.NoSleep;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;

public class Listeners implements Listener {

    @EventHandler
    public void onEnterBed(PlayerBedEnterEvent e) {
        if (NoSleep.getInstance().getConfig().getBoolean("enforceEnteringBed")) {
            if (e.getBedEnterResult() == PlayerBedEnterEvent.BedEnterResult.OK) {
                World w = e.getPlayer().getWorld();
                w.createExplosion(e.getBed().getLocation(), Integer.parseInt(NoSleep.getInstance().getExplosivePower()));
            }
        } else {
            World w = e.getPlayer().getWorld();
            w.createExplosion(e.getBed().getLocation(), Integer.parseInt(NoSleep.getInstance().getExplosivePower()));
        }
        e.setCancelled(true);
    }

}
