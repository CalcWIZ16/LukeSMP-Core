package net.lukesmp.lukeSMPCore;

import com.destroystokyo.paper.ParticleBuilder;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
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
import org.bukkit.util.ChatPaginator;
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
            for(Player player : Bukkit.getOnlinePlayers()) {
                Location location = player.getLocation();
                if(!(playersInTransit.contains(player.getUniqueId()))) {
                    // in season 5 world
                    if(location.getWorld() == Bukkit.getWorld("world")) {
                        if(-1053 <= location.getBlockX() && location.getBlockX() <= -1027) {
                            //abyssal spire trigger
//                            if(50 <= location.getBlockY() && location.getBlockY() <= 65) {
//                                if(749 <= location.getBlockZ() && location.getBlockZ() <= 775) {
//                                    Block center = location.getWorld().getBlockAt(-1040, 58, 762);
//                                    if(center.getLocation().distance(location) <= 13) {
//
//                                        InventorySender.sendInventoryData(plugin, player);
//
//                                        //teleport to abyssal spire
//                                        player.sendMessage(ChatColor.GREEN + "Connecting to the Abyssal Spire...");
//
//                                        ByteArrayDataOutput out = ByteStreams.newDataOutput();
//                                        out.writeUTF("Connect");
//                                        out.writeUTF("AbyssalSpire");
//                                        player.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
//
//                                        plugin.addPlayerToAbyssalSpire(player);
//                                    }
//                                }
//                            }
                            //on n-s axis
                            if(-1043 <= location.getBlockX() && location.getBlockX() <= -1037) {
                                // south pillar (season 1 portal)
                                if(794 <= location.getBlockZ() && location.getBlockZ() <= 814) {
                                    if(124 <= location.getBlockY() == location.getBlockY() <= 130) {
                                        Location enchantLoc = new Location(Bukkit.getWorld("world"), -1039.5, 126, 818.5);
                                        for (int i = 0; i < 10; i++) {
                                            new BukkitRunnable() {
                                                @Override
                                                public void run() {
                                                    player.getWorld().spawnParticle(Particle.ENCHANT, enchantLoc, 10, 1, 1, 0);
                                                }
                                            }.runTaskLater(plugin, i);
                                        }
                                    }
                                }
                                if(819 <= location.getBlockZ() && location.getBlockZ() <= 825) {
                                    transport(player, Bukkit.getWorld("s1world"));
                                }
                                // north pillar (season 3 portal)
                                if(710 <= location.getBlockZ() && location.getBlockZ() <= 730) {
                                    if(124 <= location.getBlockY() == location.getBlockY() <= 130) {
                                        Location enchantLoc = new Location(Bukkit.getWorld("world"), -1039.5, 126, 706.5);
                                        for (int i = 0; i < 10; i++) {
                                            new BukkitRunnable() {
                                                @Override
                                                public void run() {
                                                    player.getWorld().spawnParticle(Particle.ENCHANT, enchantLoc, 10, 1, 1, 0);
                                                }
                                            }.runTaskLater(plugin, i);
                                        }
                                    }
                                }
                                if(699 <= location.getBlockZ() && location.getBlockZ() <= 705) {
                                    transport(player, Bukkit.getWorld("s3world"));
                                }
                            }
                        }

                        //on e-w axis
                        if(759 <= location.getBlockZ() && location.getBlockZ() <= 765) {
                            // west pillar (season 2 portal)
                            if(-1092 <= location.getBlockX() && location.getBlockX() <= -1072) {
                                if(124 <= location.getBlockY() == location.getBlockY() <= 130) {
                                    Location enchantLoc = new Location(Bukkit.getWorld("world"), -1096.5, 126, 762.5);
                                    for (int i = 0; i < 10; i++) {
                                        new BukkitRunnable() {
                                            @Override
                                            public void run() {
                                                player.getWorld().spawnParticle(Particle.ENCHANT, enchantLoc, 10, 0, 1, 1);
                                            }
                                        }.runTaskLater(plugin, i);
                                    }
                                }
                            }
                            if (-1103 <= location.getBlockX() && location.getBlockX() <= -1097) {
                                transport(player, Bukkit.getWorld("s2world"));
                            }
                            // east pillar (season 4 portal)
                            if(-1008 <= location.getBlockX() && location.getBlockX() <= -988) {
                                if(124 <= location.getBlockY() == location.getBlockY() <= 130) {
                                    Location enchantLoc = new Location(Bukkit.getWorld("world"), -983, 126, 762.5);
                                    for (int i = 0; i < 10; i++) {
                                        new BukkitRunnable() {
                                            @Override
                                            public void run() {
                                                player.getWorld().spawnParticle(Particle.ENCHANT, enchantLoc, 10, 0, 1, 1);
                                            }
                                        }.runTaskLater(plugin, i);
                                    }
                                }
                            }
                            if (-983 <= location.getBlockX() && location.getBlockX() <= -977) {
                                transport(player, Bukkit.getWorld("s4world"));
                            }
                        }
                    }
                    //in season 1 world
                    if(location.getWorld() == Bukkit.getWorld("s1world")) {
                        if(-99 <= location.getBlockX() && location.getBlockX() <= -93) {
                            // entrance particles
                            if(-100 <= location.getBlockZ() && location.getBlockZ() <= -80) {
                                if(96 <= location.getBlockY() == location.getBlockY() <= 102) {
                                    Location loc1 = new Location(Bukkit.getWorld("s1world"), -95.5, 98, -103.5);
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
                            if (-111 <= location.getBlockZ() && location.getBlockZ() <= -105) {
                                //south pillar (season 1 portal)
                                transport(player, Bukkit.getWorld("world"));
                            }
                        }
                    }
                    //in season 2 world
                    if(location.getWorld() == Bukkit.getWorld("s2world")) {
                        if(165 <= location.getBlockZ() && location.getBlockZ() <= 171) {
                            // entrance particles
                            if(15 <= location.getBlockX() && location.getBlockX() <= 35) {
                                if(64 <= location.getBlockY() && location.getBlockY() <= 70) {
                                    Location loc1 = new Location(Bukkit.getWorld("s2world"), 20.5, 66, 170.5);//check location
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
                            if(39 <= location.getBlockX() && location.getBlockX() <= 47) {
                                transport(player, Bukkit.getWorld("world"));
                            }

                        }
                    }
                    //in season 3 world
                    if(location.getWorld() == Bukkit.getWorld("s3world")) {
                        if(45 <= location.getBlockX() && location.getBlockX() <= 51) {
                            // entrance particles
                            if(194 <= location.getBlockZ() && location.getBlockZ() <= 214) {
                                if(92 <= location.getBlockY() && location.getBlockY() <= 98) {
                                    Location loc1 = new Location(Bukkit.getWorld("s3world"), 50.5, 66, 191.5); //check location
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
                            if(48 <= location.getBlockZ() && location.getBlockZ() <= 54) {
                                transport(player, Bukkit.getWorld("world"));
                            }
                        }
                    }
                    //in season 4 world
                    if(location.getWorld() == Bukkit.getWorld("s4world")) {
                        if(-3 <= location.getBlockZ() && location.getBlockZ() <= 3) {
                            // entrance particles
                            if(-52 <= location.getBlockX() && location.getBlockX() <= -32) {
                                if(88 <= location.getBlockY() && location.getBlockY() <= 94) {
                                    Location loc1 = new Location(Bukkit.getWorld("s4world"), -15.5, 106, -63.5); //check location
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
                            if(-20 <= location.getBlockZ() && location.getBlockZ() <= -14) {
                                transport(player, Bukkit.getWorld("world"));
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
        Location destination = null;
        switch (destinationWorld.getName()) {
            case "world":
                destination = new Location(destinationWorld, -1039.5, 146, 762.5);
                break;
            case "s1world":
                destination = new Location(destinationWorld, -95.5, 122, -47.5);
                break;
            case "s2world":
                destination = new Location(destinationWorld, -16.9, 83, 168.5);
                break;
            case "s3world":
                destination = new Location(destinationWorld, 48, 84, 191);
                break;
            case "s4world":
                destination = new Location(destinationWorld, -15, 110, -63);
                break;
        }

        Location finalDestination = destination;
        new BukkitRunnable() {
            @Override
            public void run() {
                player.teleport(finalDestination);
                player.addPotionEffect(PotionEffectType.SLOW_FALLING.createEffect(100,0));
                playersInTransit.remove(player.getUniqueId());

                Location keylocation = InventoryRetriever.getKeyLocation(player.getUniqueId(), destinationWorld);
                if(keylocation != null) {
                    if(keylocation.getBlock().getType() == Material.EMERALD_BLOCK) {
                        player.sendMessage(ChatColor.DARK_AQUA+""+ChatColor.BOLD+"Luke"+ChatColor.DARK_PURPLE+""+ChatColor.BOLD+"SMP"+ChatColor.GRAY+""+ChatColor.BOLD+""+ChatColor.MAGIC+" | "+ChatColor.GRAY+"Retrieve your items from this season utilizing the "+ ChatColor.GREEN+"/retrieve"+ChatColor.GRAY+" command. By running this command, your inventory and ender chest from your current world will be dropped at your location. Be sure you have a place nearby to store them!");
                    }
                }
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
}
