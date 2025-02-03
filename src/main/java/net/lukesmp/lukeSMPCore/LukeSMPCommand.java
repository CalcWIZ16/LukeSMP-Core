package net.lukesmp.lukeSMPCore;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
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
                    if(sender instanceof Player) {
                        Player player = (Player) sender;
                        World world = player.getWorld();
                        UUID playerUUID = player.getUniqueId();
                        InventoryRetriever.giveItemStacksToPlayer(InventoryRetriever.readItemStacksFromContainer(new Location(Bukkit.getWorld("world"), -1040, 124, 771)), player);

//                        switch(world.getName()) {
//                            case "s1world":
//                            case "s1world_nether":
//                                player.sendMessage("You are in world 1");
//                                playerFile = new File(Bukkit.getPluginsFolder() + "/LukeSMPCore/s1/" + playerUUID + ".dat");
//                                break;
//                            case "s2world":
//                            case "s2world_nether":
//                                player.sendMessage("You are in world 2");
//                                playerFile = new File(Bukkit.getPluginsFolder() + "/LukeSMPCore/s2/" + playerUUID + ".dat");
//                                break;
//                            case "s3world":
//                            case "s3world_nether":
//                                player.sendMessage("You are in world 3");
//                                playerFile = new File(Bukkit.getPluginsFolder() + "/LukeSMPCore/s3/" + playerUUID + ".dat");
//                                break;
//                            case "s4world":
//                            case "s4world_nether":
//                                player.sendMessage("You are in world 4");
//                                playerFile = new File(Bukkit.getPluginsFolder() + "/LukeSMPCore/s4/" + playerUUID + ".dat");
//                                break;
//                            default:
//                                player.sendMessage("There is no inventory to retrieve for this world");
//                                return true;
//                        }
                        return true;
                    }
                    sender.sendMessage(ChatColor.RED + "You must be a player to use this command!");
                    return true;
                }
            }

        }
        return false;
    }
}
