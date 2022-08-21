package com.ryan.ridemobs.utils;

import com.ryan.ridemobs.RideMobs;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;
import java.util.UUID;

public class Utils {

    static final NamespacedKey KEY = new NamespacedKey(RideMobs.getInstance(), "ride-mob");
    static HashMap<UUID, Boolean> map = new HashMap<UUID, Boolean>();

    public static void checkVehicle(Player player) {
        map.put(player.getUniqueId(), player.isInsideVehicle() && player.getVehicle().getPersistentDataContainer().has(KEY, PersistentDataType.STRING));
    }

    public static NamespacedKey getKey() {
        return KEY;
    }
}
