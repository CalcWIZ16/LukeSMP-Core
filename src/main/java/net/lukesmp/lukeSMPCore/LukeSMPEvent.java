package net.lukesmp.lukeSMPCore;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffectType;

public class LukeSMPEvent implements Listener {
    @EventHandler
    public void playerChatEvent(PlayerChatEvent event){
        String player=event.getPlayer().getDisplayName();
        String chat=event.getMessage();
        event.setFormat(ChatColor.DARK_AQUA+player+ChatColor.RESET+" "+ChatColor.GRAY+ChatColor.BOLD+ChatColor.MAGIC+"|"+ChatColor.RESET+" "+ChatColor.RESET+ChatColor.GRAY+chat);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
        Player player=event.getPlayer();
        //change quit message
        event.setQuitMessage(ChatColor.DARK_AQUA+"Luke"+ChatColor.DARK_PURPLE+"SMP"+ChatColor.GRAY+""+ChatColor.BOLD+ChatColor.MAGIC+" | "+ChatColor.RED+player.getDisplayName()+ChatColor.GRAY+" has left");
    }
    @EventHandler
    public void playerDeathEvent(PlayerDeathEvent event) {
        Player player = event.getEntity().getPlayer();
        Location deathLocation = event.getEntity().getLocation();
        String deathMessage = event.getDeathMessage();

        if (event.getEntity().getKiller() != null) {
            String killerString = event.getEntity().getKiller().getDisplayName();
            deathMessage = deathMessage.replace(player.getDisplayName(), ChatColor.RED + player.getDisplayName() + ChatColor.GRAY);
            if (player.getKiller().hasPotionEffect(PotionEffectType.INVISIBILITY)) {
                deathMessage = deathMessage.replace(killerString, ChatColor.MAGIC + "------");
                if (deathMessage.contains("using")) {
                    deathMessage = deathMessage.substring(0, deathMessage.indexOf(" using ["));
                }
            } else {
                deathMessage = deathMessage.replace(killerString, ChatColor.GREEN + killerString + ChatColor.GRAY);
                deathMessage = deathMessage.replace("[", ChatColor.AQUA + "[");
            }
        }

        event.setDeathMessage(ChatColor.DARK_AQUA + "Luke" + ChatColor.DARK_PURPLE + "SMP" + ChatColor.RESET + " " + ChatColor.GRAY + ChatColor.BOLD + ChatColor.MAGIC + "|" + ChatColor.RESET + " " + deathMessage);

        //died on B
        if (deathLocation.getWorld().getName().toString() == "s4world") {
            if ((deathLocation.getBlockX() >= -521) && (deathLocation.getBlockX() <= -520)) {
                if ((deathLocation.getBlockY() == 68)) {
                    if ((deathLocation.getBlockZ() >= -180) && (deathLocation.getBlockZ() <= -179)) {
                        event.setDeathMessage(ChatColor.DARK_AQUA + "Luke" + ChatColor.DARK_PURPLE + "SMP" + ChatColor.RESET + " " + ChatColor.GRAY + ChatColor.BOLD + ChatColor.MAGIC + "|" + ChatColor.RED + " " + player.getDisplayName() + ChatColor.GRAY + " recieved a JUG");
                    }
                }
            }
        }
    }
}
