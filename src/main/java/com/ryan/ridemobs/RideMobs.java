package com.ryan.ridemobs;

import com.comphenix.protocol.ProtocolLibrary;
import com.ryan.ridemobs.commands.RideCommand;
import com.ryan.ridemobs.listeners.PacketListener;
import com.ryan.ridemobs.listeners.RideListener;
import com.ryan.ridemobs.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class RideMobs extends JavaPlugin {
	
	static RideMobs instance;

	@Override
	public void onEnable() {
		instance = this;
		PacketListener packetListener = new PacketListener();
		packetListener.addPacketListener(this, ProtocolLibrary.getProtocolManager());
		this.getServer().getPluginManager().registerEvents(new RideListener(), this);
		this.getCommand("ride").setExecutor(new RideCommand());
		for (Player player : this.getServer().getOnlinePlayers()) {
			Utils.checkVehicle(player);
		}
	}
	
	@Override
	public void onDisable() {}

	public static RideMobs getInstance() {
		return instance;
	}
}
