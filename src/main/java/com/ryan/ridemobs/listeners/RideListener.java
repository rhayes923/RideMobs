package com.ryan.ridemobs.listeners;

import com.ryan.ridemobs.utils.Utils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.persistence.PersistentDataType;
import org.spigotmc.event.entity.EntityDismountEvent;

public class RideListener implements Listener {

    @EventHandler
    public void onEntityDismount(EntityDismountEvent e) {
        if (e.getEntityType().isAlive()) {
            if (e.getDismounted().getPersistentDataContainer().has(Utils.getKey(), PersistentDataType.STRING)) {
                e.getDismounted().remove();
            }
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Utils.checkVehicle(e.getPlayer());
    }
}
