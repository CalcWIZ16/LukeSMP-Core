package net.lukesmp.lukeSMPCore;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.logging.Level;

public class LukeSMPCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("getworld")){
            if (sender instanceof Player){
                Player player = (Player) sender;
                player.sendMessage(player.getWorld().getName().toString());
            }
            return true;
        }
        if(command.getName().equalsIgnoreCase("world")) {
            if (args.length == 0) {
                sender.sendMessage(ChatColor.RED + "Please specify a world!");
                return true;
            }
            if (args.length == 1) {
                World world = Bukkit.getWorld(args[0]);
                if (world == null) {
                    sender.sendMessage(ChatColor.RED + "That world does not exist!");
                    return true;
                }
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    if(player.getUniqueId().toString().equals("f1460c69-e2fd-4e43-ab00-3cbc81340d6b") || player.getUniqueId().toString().equals("46257261-7468-4a8b-bb32-b7f5a78f7a0a")) {
                        player.teleport(world.getSpawnLocation());
                        player.sendMessage(ChatColor.GREEN + "Teleported to " + world.getName());
                        return true;
                    }
                    player.teleport(world.getSpawnLocation());
                    player.sendMessage(ChatColor.GREEN + "Teleported to " + world.getName());
                    return true;
                }
                sender.sendMessage(ChatColor.RED + "You must be a player to use this command!");
                return true;
            }
        }
        if(command.getName().equalsIgnoreCase("retrieve")){
            if(args.length == 0) {
                sender.sendMessage(ChatColor.RED + "Please confirm");
                return true;
            }
            if(args.length == 1) {
                if(args[0].equalsIgnoreCase("confirm")) {
                    if (sender instanceof Player) {
                        Player player = (Player) sender;
                        Location keyLocation = InventoryRetriever.getKeyLocation(player.getUniqueId(), player.getWorld());

                        if (keyLocation == null) {
                            player.sendMessage(ChatColor.RED + "You do not have any items to retrieve in this world");
                            return true;
                        }

                        if (keyLocation.getBlock().getType() == Material.EMERALD_BLOCK) {
                            InventoryRetriever.giveItemStacksToPlayer(InventoryRetriever.readItemStacksFromContainer(keyLocation.subtract(0, 0, 1)), player);
                            InventoryRetriever.giveItemStacksToPlayer(InventoryRetriever.readItemStacksFromContainer(keyLocation.subtract(0, 0, 1)), player);
                            player.sendMessage(ChatColor.GREEN + "Items have been retrieved");
                            keyLocation.add(0, 0, 2).getBlock().setType(Material.RED_CONCRETE);
                        } else {
                            player.sendMessage(ChatColor.RED + "You have already claimed your items in this world");
                        }
                        return true;
                    }
                    sender.sendMessage(ChatColor.RED + "You must be a player to use this command!");
                    return true;
                }
//                else {
//                    Player player = (Player) sender;
//                    Location keyLocation = InventoryRetriever.getKeyLocation(UUID.fromString(args[0]), player.getWorld());
//
//                    if(keyLocation == null) {
//                        player.sendMessage(ChatColor.RED + "You do not have any items to retrieve in this world");
//                        return true;
//                    }
//
//                    if(keyLocation.getBlock().getType() == Material.EMERALD_BLOCK) {
//                        InventoryRetriever.giveItemStacksToPlayer(InventoryRetriever.readItemStacksFromContainer(keyLocation.subtract(0,0,1)), player);
//                        InventoryRetriever.giveItemStacksToPlayer(InventoryRetriever.readItemStacksFromContainer(keyLocation.subtract(0,0,1)), player);
//                        player.sendMessage(ChatColor.GREEN + "Items have been retrieved");
//                        keyLocation.add(0,0,2).getBlock().setType(Material.RED_CONCRETE);
//                    } else {
//                        player.sendMessage(ChatColor.RED + "You have already claimed your items in this world");
//                    }
//                    return true;
//                }
            }

        }
        return false;
    }
}
