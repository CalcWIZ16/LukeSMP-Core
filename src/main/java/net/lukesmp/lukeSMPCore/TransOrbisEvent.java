package net.lukesmp.lukeSMPCore;

import com.destroystokyo.paper.ParticleBuilder;
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
                Location location = player.getLocation();
                //check for Trans-Orbis pillars
                if(!(playersInTransit.contains(player.getUniqueId()))) {
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
                //check for by entrance to pillar
                if(location.getWorld() == Bukkit.getWorld("world")) {
                    if(-1042 <= location.getBlockX() && location.getBlockX() <= -1038) {
                        if(794 <= location.getBlockZ() && location.getBlockZ() <= 814) {
                            if(124 <= location.getBlockY() == location.getBlockY() <= 130) {
                                Location loc1 = new Location(Bukkit.getWorld("world"), -1039.5, 126, 818.5);
                                for(int i = 0; i < 10; i++) {
                                    new BukkitRunnable() {
                                        @Override
                                        public void run() {
                                            player.getWorld().spawnParticle(Particle.ENCHANT, loc1, 10, 1, 1, 0);
                                        }
                                    }.runTaskLater(plugin, i);
                                }
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
                player.addPotionEffect(PotionEffectType.SLOW_FALLING.createEffect(100,0));
                playersInTransit.remove(player.getUniqueId());
            }
        }.runTaskLater(plugin, 40L);

        player.getWorld().spawnParticle(Particle.GUST_EMITTER_LARGE, player.getLocation(), 1);
        player.setVelocity(new Vector(0, 10, 0));

//        for(int i = 0; i < 40; i++) {
//            new BukkitRunnable() {
//                @Override
//                public void run() {
//                    player.spawnParticle(Particle.FIREWORKS_SPARK, player.getLocation(), 10);
//                }
//            }.runTaskLater(plugin, i);
//        }

        for(int i = 0; i < 160; i++) {
            int t = i;
            Particle helixParticle = Particle.FIREWORK;
//            ParticleBuilder helixParticle = new ParticleBuilder(Particle.HEART).location(player.getLocation()).color(0,0,255);
//            helixParticle.spawn();

            new BukkitRunnable() {
                @Override
                public void run() {
                    Location location = player.getLocation();
                    double sine = (Math.sin(t/5.0)*3);
                    double cosine = (Math.cos(t/5.0)*3);
//                    helixParticle.location(location);
//                    helixParticle.spawn();

                    player.getWorld().spawnParticle(helixParticle, player.getLocation().add(sine, 0, cosine),0);
                    player.getWorld().spawnParticle(helixParticle, player.getLocation().add(-sine, 0, cosine), 0);
                    player.getWorld().spawnParticle(helixParticle, player.getLocation().add(sine, 0, -cosine), 0);
                    player.getWorld().spawnParticle(helixParticle, player.getLocation().add(-sine, 0, -cosine), 0);

                    player.getWorld().spawnParticle(helixParticle, player.getLocation().add(cosine, 0, sine), 0);
                    player.getWorld().spawnParticle(helixParticle, player.getLocation().add(-cosine, 0, sine), 0);
                    player.getWorld().spawnParticle(helixParticle, player.getLocation().add(cosine, 0, -sine), 0);
                    player.getWorld().spawnParticle(helixParticle, player.getLocation().add(-cosine, 0, -sine), 0);
                }
            }.runTaskLaterAsynchronously(plugin, t);
        }
    }

    private boolean unlockCheck(Block keyLocation, Material baseBlock) {
        if (keyLocation.getType() == baseBlock) {
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
