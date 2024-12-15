package net.lukesmp.lukeSMPCore;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LukeSMPCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("getworld")){
            if (sender instanceof Player){
                Player player = (Player) sender;
                player.sendMessage(player.getWorld().getName().toString());
            }
            return true;
        }
        if (command.getName().equalsIgnoreCase("world")) {
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
        return false;
    }
}
