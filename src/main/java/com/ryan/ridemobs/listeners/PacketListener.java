package com.ryan.ridemobs.listeners;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.ryan.ridemobs.RideMobs;
import com.ryan.ridemobs.utils.Utils;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Vector;

public class PacketListener {

    public void addPacketListener(RideMobs instance, ProtocolManager manager) {
        manager.addPacketListener(new PacketAdapter(instance, ListenerPriority.NORMAL, PacketType.Play.Client.STEER_VEHICLE) {
            @Override
            public void onPacketReceiving(PacketEvent e) {
                PacketContainer packet = e.getPacket();
                Player player = e.getPlayer();
                Entity entity = player.getVehicle();
                if (entity != null && entity.getPersistentDataContainer().has(Utils.getKey(), PersistentDataType.STRING)) {
                    float sidewaysMovement = (packet.getFloat().read(0)) / 10;
                    float forwardMovement = (packet.getFloat().read(1)) / 5;
                    boolean jumping = packet.getBooleans().read(0);
                    Location pLoc = player.getLocation();
                    Vector sideways = pLoc.getDirection().clone().crossProduct(new Vector(0, -1, 0));
                    Vector velocity = pLoc.getDirection().multiply(forwardMovement).add(sideways.multiply(sidewaysMovement));
                    entity.setRotation(pLoc.getYaw(), pLoc.getPitch());
                    velocity.setY(jumping && entity.isOnGround() ? 0.3 : 0);
                    if (!entity.isOnGround() || entity.isInWater()) {
                        velocity.multiply(0.2);
                    }
                    entity.setVelocity(entity.getVelocity().add(velocity));
                    Utils.checkVehicle(player);
                }
            }
        });
    }
}
