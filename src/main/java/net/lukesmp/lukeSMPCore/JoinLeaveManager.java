package net.lukesmp.lukeSMPCore;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.IOException;

public class JoinLeaveManager implements Listener {
    public final LukeSMPCore plugin;

    public JoinLeaveManager(LukeSMPCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) throws IOException {
        Player player=event.getPlayer();
        
        if(plugin.removePlayerFromAbyssalSpire(player)) {
            player.teleport(Bukkit.getWorld("world").getSpawnLocation());
        }

        //tab header/footer
        player.setPlayerListHeader(ChatColor.DARK_AQUA+""+ChatColor.BOLD+"Luke"+ChatColor.DARK_PURPLE+""+ChatColor.BOLD+"SMP"+ChatColor.GRAY+""+ChatColor.BOLD+""+ChatColor.MAGIC+" | "+ChatColor.RED+""+ChatColor.BOLD+"Season V");
        player.setPlayerListFooter(ChatColor.GRAY+""+ChatColor.BOLD+ChatColor.MAGIC+" | "+ChatColor.DARK_AQUA+"mc.lukesmp.net"+ChatColor.GRAY+ChatColor.BOLD+ChatColor.MAGIC+" | ");

        //change join message
        event.setJoinMessage(ChatColor.DARK_AQUA+"Luke"+ChatColor.DARK_PURPLE+"SMP"+ChatColor.RESET+" "+ChatColor.GRAY+ChatColor.BOLD+ChatColor.MAGIC+"|"+ChatColor.RESET+" "+ChatColor.RESET+ChatColor.GREEN+player.getDisplayName()+ChatColor.GRAY+" has joined");

        //title at join
        joinTitle(player);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
        Player player=event.getPlayer();
        //change quit message
        event.setQuitMessage(ChatColor.DARK_AQUA+"Luke"+ChatColor.DARK_PURPLE+"SMP"+ChatColor.GRAY+""+ChatColor.BOLD+ChatColor.MAGIC+" | "+ChatColor.RED+player.getDisplayName()+ChatColor.GRAY+" has left");
    }

    private void joinTitle(Player player) {
        int random = (int) (Math.random() * 6);
        if (random==3){
            player.sendTitle(ChatColor.DARK_AQUA+""+ChatColor.BOLD+"Lucy"+ChatColor.DARK_PURPLE+""+ChatColor.BOLD+"SMP",ChatColor.RED+"Season V");
            new BukkitRunnable() {
                @Override
                public void run() {
                    player.sendTitle(ChatColor.DARK_AQUA+""+ChatColor.MAGIC+ChatColor.BOLD+"Lucy"+ChatColor.DARK_PURPLE+""+ChatColor.BOLD+"SMP",ChatColor.RED+"Season V", 0, 20, 0);
                }
            }.runTaskLater(plugin,30L);
            new BukkitRunnable() {
                @Override
                public void run() {
                    player.sendTitle(ChatColor.DARK_PURPLE+""+ChatColor.BOLD+"SMP",ChatColor.RED+"Season V", 0, 20, 0);
                }
            }.runTaskLater(plugin, 40L);
            new BukkitRunnable() {
                @Override
                public void run() {
                    player.sendTitle(ChatColor.DARK_AQUA+""+ChatColor.BOLD+"L"+ChatColor.DARK_PURPLE+""+ChatColor.BOLD+"SMP",ChatColor.RED+"Season V", 0, 20, 0);
                }
            }.runTaskLater(plugin, 42L);
            new BukkitRunnable() {
                @Override
                public void run() {
                    player.sendTitle(ChatColor.DARK_AQUA+""+ChatColor.BOLD+"Lu"+ChatColor.DARK_PURPLE+""+ChatColor.BOLD+"SMP",ChatColor.RED+"Season V", 0, 20, 0);
                }
            }.runTaskLater(plugin, 44L);
            new BukkitRunnable() {
                @Override
                public void run() {
                    player.sendTitle(ChatColor.DARK_AQUA+""+ChatColor.BOLD+"Luk"+ChatColor.DARK_PURPLE+""+ChatColor.BOLD+"SMP",ChatColor.RED+"Season V", 0, 20, 0);
                }
            }.runTaskLater(plugin, 46L);
            new BukkitRunnable() {
                @Override
                public void run() {
                    player.sendTitle(ChatColor.DARK_AQUA+""+ChatColor.BOLD+"Luke"+ChatColor.DARK_PURPLE+""+ChatColor.BOLD+"SMP",ChatColor.RED+"Season V", 0, 70, 20);
                }
            }.runTaskLater(plugin, 48L);
        }
        else {
            player.sendTitle(ChatColor.DARK_AQUA+""+ChatColor.BOLD+"Luke"+ChatColor.DARK_PURPLE+""+ChatColor.BOLD+"SMP",ChatColor.RED+"Season V");
        }
    }
}
