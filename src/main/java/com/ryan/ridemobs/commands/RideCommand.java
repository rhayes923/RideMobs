package com.ryan.ridemobs.commands;

import com.ryan.ridemobs.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

public class RideCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length != 1) {
                player.sendMessage(ChatColor.RED + "[RideMobs] Invalid arguments!");
                return false;
            }

            for (EntityType e : EntityType.values()) {
                if (e.isAlive() && args[0].toUpperCase().contentEquals(e.toString())) {
                    Entity entity = Bukkit.getWorld("world").spawnEntity(player.getLocation(), EntityType.valueOf(e.toString()));
                    entity.addPassenger(player);
                    entity.getPersistentDataContainer().set(Utils.getKey(), PersistentDataType.STRING, "Rideable Mob");
                    String article = ("AEIOU").indexOf(entity.getName().charAt(0)) != -1 ? "an" : "a";
                    player.sendMessage(ChatColor.RED + "[RideMobs]" + ChatColor.LIGHT_PURPLE + " Now riding " + article + " " + entity.getName());
                    return true;
                }
            }
            player.sendMessage(ChatColor.RED + "[RideMobs] That is not a mob!");
        }
        return true;
    }
}
