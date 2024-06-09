package net.lukesmp.lukesmpcore;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class TransOrbisEvent implements Listener {

    public final LukeSMPCore plugin;

    public TransOrbisEvent(LukeSMPCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void playerPortalEvent(PlayerPortalEvent event) {
        Player player = event.getPlayer();
        World world = player.getWorld();
        if (event.getCause() == PlayerTeleportEvent.TeleportCause.NETHER_PORTAL) {
            if (world.getEnvironment()==World.Environment.NORMAL) {
                event.getTo().setWorld(Bukkit.getWorld(world.getName()+"_nether"));
            }
            if (world.getEnvironment()==World.Environment.NETHER) {
                if (world==Bukkit.getWorld("s1world_nether")) {
                    event.getTo().setWorld(Bukkit.getWorld("s1world"));
                }
                else if (world==Bukkit.getWorld("s2world_nether")) {
                    event.getTo().setWorld(Bukkit.getWorld("s2world"));
                }
                else if (world==Bukkit.getWorld("s3world_nether")) {
                    event.getTo().setWorld(Bukkit.getWorld("s3world"));
                }
                else if (world==Bukkit.getWorld("s4world_nether")) {
                    event.getTo().setWorld(Bukkit.getWorld("s4world"));
                }
            }
        }
    }

    @EventHandler
    public void playerMoveEvent(PlayerMoveEvent event) {
        Location location = event.getPlayer().getLocation();

        //portal pillars
        if (location.getWorld() == Bukkit.getWorld("world")) {
            if (-1043 <= location.getBlockX() && location.getBlockX() <= -1037) {
                if (819 <= location.getBlockZ() && location.getBlockZ() <= 825) {
                    //south pillar (season 1 portal)
                }
                if (699 <= location.getBlockZ() && location.getBlockZ() <= 705) {
                    //north pillar (season 3 portal)
                }
            }
            if (759 <= location.getBlockZ() && location.getBlockZ() <= 765) {
                if (-1103 <= location.getBlockX() && location.getBlockX() <= -1097) {
                    //west pillar (season 2 portal)
                }
                if (-983 <= location.getBlockX() && location.getBlockX() <= -977) {
                    //east pillar (season 4 portal)
                }
            }
        }
    }

    private void transport(Player player, World destinationWorld) {
        player.addPotionEffect(PotionEffectType.LEVITATION.createEffect(5,10));
        for (int i = 0; i < 10; i++) {
            player.getWorld().spawnParticle(org.bukkit.Particle.PORTAL, player.getLocation(), 10);
        }
        player.teleport(destinationWorld.getSpawnLocation().add(0,100,0));
        new BukkitRunnable() {
            @Override
            public void run() {
                player.addPotionEffect(PotionEffectType.SLOW_FALLING.createEffect(5,10));
            }
        }.runTaskLater(plugin, 100);
    }

    private boolean unlockCheck(Block keyLocation, Material baseBlock) {
        if (keyLocation.getType() == Material.BEACON) {
            for (int x = -1; x <= 1; x++) {
                for (int z = -1; z <= 1; z++) {
                    if (keyLocation.getRelative(x, -1, z).getType() != baseBlock) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }
}
