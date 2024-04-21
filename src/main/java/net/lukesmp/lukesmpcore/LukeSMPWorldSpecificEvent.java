package net.lukesmp.lukesmpcore;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

public class LukeSMPWorldSpecificEvent implements Listener {
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

    //death on B
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event){
        Player player=event.getPlayer();
        Location loc=player.getLocation();
        if((loc.getWorld()==Bukkit.getWorld("world4"))){
            if((loc.getBlockX() >= -521) && (player.getLocation().getBlockX() <= -520)){
                if((loc.getBlockY() == 68)){
                    if((loc.getBlockZ() >= -180) && (player.getLocation().getBlockZ() <= -179)){
                        player.setHealth(0);
                    }
                }
            }
        }
    }

    //spawn pillars protection
    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent event) {
        if (event.getBlock().getType() == Material.OBSIDIAN || event.getBlock().getType() == Material.DEEPSLATE_BRICKS || event.getBlock().getType() == Material.CRACKED_DEEPSLATE_BRICKS) {
            Player player = event.getPlayer();
            Block block = event.getBlock();
            Integer x = block.getX();
            Integer y = block.getY();
            Integer z = block.getZ();
            if (block.getWorld().getName().equals("world")) {
                //passageways
                if (y>=122){
                    if (-1042<=x && x<=-1038) {
                        //North passage
                        if (707<=z && z<=748) {
                            event.setCancelled(true);
                        }
                        //South passage
                        if (776<=z && z<=817) {
                            event.setCancelled(true);
                        }
                    }
                    if (760<=z && z<=764) {
                        //East passage
                        if (-1026<=x && x<=-985) {
                            event.setCancelled(true);
                        }
                        //West passage
                        if (-1095<=x && x<=-1054) {
                            event.setCancelled(true);
                        }
                    }
                }
                //spires
                if (-1044<=x && x<=-1036){
                    //south spire
                    if (818<=z && z<=826) {
                        event.setCancelled(true);
                    }
                    //north spire
                    if (698<=z && z<=706) {
                        event.setCancelled(true);
                    }
                }
                if (758<=z && z<=766) {
                    //east spire
                    if (-984<=x && x<=-976) {
                        event.setCancelled(true);
                    }
                    //west spire
                    if (-1104<=x && x<=-1096) {
                        event.setCancelled(true);
                    }
                }
            }
        }
    }
    @EventHandler
    public void onBlockPlaceEvent(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        Integer x = block.getX();
        Integer y = block.getY();
        Integer z = block.getZ();
        if (block.getWorld().getName().equals("world")) {
            //passageways
            if (y >= 122) {
                if (-1042 <= x && x <= -1038) {
                    //North passage
                    if (707 <= z && z <= 748) {
                        event.setCancelled(true);
                    }
                    //South passage
                    if (776 <= z && z <= 817) {
                        event.setCancelled(true);
                    }
                }
                if (760 <= z && z <= 764) {
                    //East passage
                    if (-1026 <= x && x <= -985) {
                        event.setCancelled(true);
                    }
                    //West passage
                    if (-1095 <= x && x <= -1054) {
                        event.setCancelled(true);
                    }
                }
            }
            //spires
            if (-1044 <= x && x <= -1036) {
                //south spire
                if (818 <= z && z <= 826) {
                    event.setCancelled(true);
                }
                //north spire
                if (698 <= z && z <= 706) {
                    event.setCancelled(true);
                }
            }
            if (758 <= z && z <= 766) {
                //east spire
                if (-984 <= x && x <= -976) {
                    event.setCancelled(true);
                }
                //west spire
                if (-1104 <= x && x <= -1096) {
                    event.setCancelled(true);
                }
            }
        }
    }

}
