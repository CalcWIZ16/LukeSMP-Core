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
        Player player = event.getPlayer();
        Block block = event.getBlock();
        Integer x = block.getX();
        Integer y = block.getY();
        Integer z = block.getZ();
        if (block.getWorld().getName().equals("world")) {
            //passageways
            if (y>=120){
                if (-1043<=x && x<=-1037) {
                    //North passage
                    if (707<=z && z<=748) {
                        event.setCancelled(true);
                    }
                    //South passage
                    if (776<=z && z<=817) {
                        event.setCancelled(true);
                    }
                }
                if (759<=z && z<=765) {
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
        if(block.getWorld().getName().equals("world1")) {
            //passageway
            if(y>=92) {
                if(-99<=x && x<=-93) {
                    if(-103<=z && z<=-62) {
                        event.setCancelled(true);
                    }
                }
            }
            //spire
            if(-100<=x && x<=-92) {
                if(-112<=z && z<=-104) {
                    event.setCancelled(true);
                }
            }
            //origin
            if(-110<=x && x<=-82) {
                if(-62<=z && z<=-34) {
                    event.setCancelled(true);
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
            if (y>=120){
                if (-1043<=x && x<=-1037) {
                    //North passage
                    if (707<=z && z<=748) {
                        event.setCancelled(true);
                    }
                    //South passage
                    if (776<=z && z<=817) {
                        event.setCancelled(true);
                    }
                }
                if (759<=z && z<=765) {
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
        if(block.getWorld().getName().equals("world1")) {
            //passageway
            if(y>=92) {
                if(-99<=x && x<=-93) {
                    if(-103<=z && z<=-62) {
                        event.setCancelled(true);
                    }
                }
            }
            //spire
            if(-100<=x && x<=-92) {
                if(-112<=z && z<=-104) {
                    event.setCancelled(true);
                }
            }
            //origin
            if(-110<=x && x<=-82) {
                if(-62<=z && z<=-34) {
                    event.setCancelled(true);
                }
            }
        }
    }
}
