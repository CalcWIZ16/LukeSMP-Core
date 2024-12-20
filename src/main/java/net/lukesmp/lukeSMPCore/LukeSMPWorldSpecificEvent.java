package net.lukesmp.lukeSMPCore;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class LukeSMPWorldSpecificEvent implements Listener {
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event){
        Location location = event.getPlayer().getLocation();

        //kill player on B
        if(location.getWorld() == Bukkit.getWorld("world4")){
            if((location.getBlockX() >= -521) && (location.getBlockX() <= -520)){
                if((location.getBlockY() == 68)){
                    if((location.getBlockZ() >= -180) && (location.getBlockZ() <= -179)){
                        event.getPlayer().setHealth(0);
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
