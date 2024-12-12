package net.lukesmp.lukesmpcore;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class TransOrbisEvent implements Listener {

    public final LukeSMPCore plugin;
    private final Set<UUID> playersInTransit = new HashSet<>();

    public TransOrbisEvent(LukeSMPCore plugin) {
        this.plugin = plugin;
        startAsyncPositionDetection();
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

    private void startAsyncPositionDetection() {
        BukkitTask posDetector = Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (!(playersInTransit.contains(player.getUniqueId()))) {
                    Location location = player.getLocation();
                    if (location.getWorld() == Bukkit.getWorld("world")) {
                        if (-1043 <= location.getBlockX() && location.getBlockX() <= -1037) {
                            if (819 <= location.getBlockZ() && location.getBlockZ() <= 825) {
                                //south pillar (season 1 portal)
                                player.sendMessage("Transporting to season 1");
                                transport(player, Bukkit.getWorld("s1world"));
                            }
                            if (699 <= location.getBlockZ() && location.getBlockZ() <= 705) {
                                //north pillar (season 3 portal)
                                player.sendMessage("Transporting to season 3");
                                transport(player, Bukkit.getWorld("s3world"));
                            }
                        }
                        if (759 <= location.getBlockZ() && location.getBlockZ() <= 765) {
                            if (-1103 <= location.getBlockX() && location.getBlockX() <= -1097) {
                                //west pillar (season 2 portal)
                                player.sendMessage("Transporting to season 2");
                                transport(player, Bukkit.getWorld("s2world"));
                            }
                            if (-983 <= location.getBlockX() && location.getBlockX() <= -977) {
                                //east pillar (season 4 portal)
                                player.sendMessage("Transporting to season 4");
                                transport(player, Bukkit.getWorld("s4world"));
                            }
                        }
                    }
                }
            }
        }, 0L, 20L);
    }

    /**
     * trans-orbis transportation and animation logic
     */
    private void transport(Player player, World destinationWorld) {
        playersInTransit.add(player.getUniqueId());

        new BukkitRunnable() {
            @Override
            public void run() {
                player.teleport(destinationWorld.getSpawnLocation().add(0,20,0));
                boolean onGround = false;
                player.addPotionEffect(PotionEffectType.SLOW_FALLING.createEffect(-1,10));
                while(!onGround) {
                    if(player.isOnGround()){
                        player.removePotionEffect(PotionEffectType.SLOW_FALLING);
                        onGround = true;
                    }
                }
            }
        }.runTaskLater(plugin, 40L);

        player.setVelocity(new Vector(0, 15, 0));
        for(int i = 0; i < 40; i++) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    player.spawnParticle(Particle.FIREWORKS_SPARK, player.getLocation(), 10);
                }
            }.runTaskLater(plugin, i);
        }

        for(int i = 0; i < 120; i++) {
            Location playerLoc = player.getLocation();
            double t = i * Math.PI / 10;
            double x = Math.sin(t)*3 + playerLoc.getX();
            double z = Math.cos(t)*3 + playerLoc.getZ();
            Location loc = player.getLocation().add(x, playerLoc.getY(), z);
            destinationWorld.spawnParticle(Particle.FIREWORKS_SPARK, loc, 1);

        }
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
