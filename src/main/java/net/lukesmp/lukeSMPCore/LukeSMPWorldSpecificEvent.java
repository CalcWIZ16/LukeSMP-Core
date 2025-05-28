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
//    @EventHandler
//    public void onPlayerMove(PlayerMoveEvent event){
//        Location location = event.getPlayer().getLocation();
//
//        //kill player on B
//        if(location.getWorld() == Bukkit.getWorld("s4world")){
//            if((location.getBlockX() >= -521) && (location.getBlockX() <= -520)){
//                if((location.getBlockY() == 68)){
//                    if((location.getBlockZ() >= -180) && (location.getBlockZ() <= -179)){
//                        event.getPlayer().setHealth(0);
//                    }
//                }
//            }
//        }
//    }

    //spawn pillars protection
    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent event) {
        event.setCancelled(protectedBlock(event.getBlock()));
    }

    @EventHandler
    public void onBlockPlaceEvent(BlockPlaceEvent event) {
        event.setCancelled(protectedBlock(event.getBlock()));
    }

    private boolean protectedBlock(Block block) {
        int x = block.getX();
        int y = block.getY();
        int z = block.getZ();
        if(block.getWorld().getName().equals("world")) {
            //passageways
            if(y>=120){
                if(-1043<=x && x<=-1037) {
                    //North passage
                    if(707<=z && z<=748) {
                        return true;
                    }
                    //South passage
                    if(776<=z && z<=817) {
                        return true;
                    }
                }
                if(759<=z && z<=765) {
                    //East passage
                    if(-1026<=x && x<=-985) {
                        return true;
                    }
                    //West passage
                    if(-1095<=x && x<=-1054) {
                        return true;
                    }
                }
            }
            //spires
            if(-1044<=x && x<=-1036){
                //south spire
                if(818<=z && z<=826) {
                    return true;
                }
                //north spire
                if(698<=z && z<=706) {
                    return true;
                }
            }
            if(758<=z && z<=766) {
                //east spire
                if(-984<=x && x<=-976) {
                    return true;
                }
                //west spire
                if(-1104<=x && x<=-1096) {
                    return true;
                }
            }
        }
        if(block.getWorld().getName().equals("s1world")) {
            //passageway
            if(y>=92) {
                if(-99<=x && x<=-93) {
                    if(-103<=z && z<=-62) {
                        return true;
                    }
                }
            }
            //spire
            if(-100<=x && x<=-92) {
                if(-112<=z && z<=-104) {
                    return true;
                }
            }
            //origin
            if(-110<=x && x<=-82) {
                if(-62<=z && z<=-34) {
                    return true;
                }
            }
        }
        if(block.getWorld().getName().equals("s2world")) {
            //passageway
            if(y>=60) {
                if(165<=z && z<=171) {
                    if(-3<=x && x<=38) {
                        return true;
                    }
                }
            }
            //spire
            if(39<=x && x<=47) {
                if(164<=z && z<=172) {
                    return true;
                }
            }
            //origin
            if(-31<=x && x<=-3) {
                if(154<=z && z<=182) {
                    return true;
                }
            }
        }
        if(block.getWorld().getName().equals("s3world")) {
            //passageway
            if(y >= 88) {
                if(45 <= x && x <= 51) {
                    if(176 <= z && z <= 217) {
                        return true;
                    }
                }
            }
            //spire
            if(44 <= x && x <= 52) {
                if(218 <= z && z <= 226) {
                    return true;
                }
            }
            //origin
            if(34 <= x && x <= 62) {
                if(148 <= z && z <= 176) {
                    return true;
                }
            }
        }
        if(block.getWorld().getName().equals("s4world")) {
            //passageway
            if(y >= 84) {
                if(-55 <= x && x <= -14) {
                    if(-3 <= z && z <= 3) {
                        return true;
                    }
                }
            }
            //spire
            if(-64 <= x && x <= -56) {
                if(-4 <= z && z <= 4) {
                    return true;
                }
            }
            //origin
            if(-14 <= x && x <= 14) {
                if(-14 <= z && z <= 14) {
                    return true;
                }
            }
        }
        return false;
    }
}
