package net.lukesmp.lukesmpcore;

import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Tameable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

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
//    @EventHandler
//    public void entityDamageEvent(EntityDamageEvent event){
//        Entity entity=event.getEntity();
//        if (entity instanceof Tameable){
//            Tameable tameable=(Tameable) entity;
//            if (tameable.isTamed()){
//                if (tameable.getHealth()-event.getFinalDamage()<=0){
//                    tameable.remove();
//                    event.setCancelled(true);
//                    String ownerName=tameable.getOwner().getName();
//                    String petName=tameable.getName();
//                    String message="";
//                    switch (event.getCause()){
//                        case BLOCK_EXPLOSION:
//                            message= ChatColor.RED+petName+ChatColor.GRAY+" blew up";
//                            break;
//                        case CONTACT:
//                            message=ChatColor.RED+petName+ChatColor.GRAY+" shouldn't have touched that";
//                            break;
//                        case CRAMMING:
//                            message=ChatColor.RED+petName+ChatColor.GRAY+" decided it was too crowded";
//                            break;
//                        case DRAGON_BREATH:
//                            message=ChatColor.RED+petName+ChatColor.GRAY+" was breathed on too hard by a dragon";
//                            break;
//                        case DROWNING:
//                            message=ChatColor.RED+petName+ChatColor.GRAY+" learned the hard way that they couldn't breathe underwater";
//                            break;
//                        case ENTITY_ATTACK:
//                        case ENTITY_SWEEP_ATTACK:
//                        case THORNS:
//                            Entity killer = tameable.getKiller();
//                            if (killer instanceof Player){
//                                message=ChatColor.RED+petName+ChatColor.GRAY+" was slain by "+ChatColor.RED+killer;
//                            }else{
//                                message=ChatColor.RED+petName+ChatColor.GRAY+" was slain";
//                            }
//                            break;
//                        case ENTITY_EXPLOSION:
//                            message=ChatColor.RED+petName+ChatColor.GRAY+" was blown up by a creeper, aww man";
//                            break;
//                        case FALL:
//                            message=ChatColor.RED+petName+ChatColor.GRAY+" realized they couldn't fly";
//                            break;
//                        case FALLING_BLOCK:
//                            message=ChatColor.RED+petName+ChatColor.GRAY+" should've wore a helmet";
//                            break;
//                        case FIRE:
//                        case FIRE_TICK:
//                        case LAVA:
//                            message=ChatColor.RED+petName+ChatColor.GRAY+""+ChatColor.BOLD+" HATES "+ChatColor.RESET+""+ChatColor.GRAY+ownerName+" so they burned to death";
//                            break;
//                        case FREEZE:
//                            message=ChatColor.RED+petName+ChatColor.GRAY+" tried to swim in powdered snow";
//                            break;
//                        case HOT_FLOOR:
//                            message=ChatColor.RED+petName+ChatColor.GRAY+" discovered that the floor is too hot";
//                            break;
//                        case LIGHTNING:
//                            message=ChatColor.RED+petName+ChatColor.GRAY+" met their end due to Thor";
//                            break;
//                        case MAGIC:
//                            message=ChatColor.RED+petName+ChatColor.GRAY+" went Avada Kedavra";
//                            break;
//                        case PROJECTILE:
//                            message=ChatColor.RED+petName+ChatColor.GRAY+" was shot";
//                            break;
//                        case SUFFOCATION:
//                            message=ChatColor.RED+petName+ChatColor.GRAY+" can only breathe air";
//                            break;
//                        case SUICIDE:
//                            message=ChatColor.RED+petName+ChatColor.GRAY+""+ChatColor.BOLD+" HATES "+ChatColor.RESET+""+ChatColor.GRAY+ownerName+" so they committed suicide";
//                            break;
//                        case VOID:
//                            message=ChatColor.RED+petName+ChatColor.GRAY+" fell into the nothing";
//                            break;
//                        case WITHER:
//                            message=ChatColor.RED+petName+ChatColor.GRAY+" died a slow and painful death due to withering";
//                            break;
//                        default:
//                            message=ChatColor.RED+petName+ChatColor.GRAY+" died";
//                            break;
//                    }
//                    Bukkit.broadcastMessage(ChatColor.DARK_AQUA+"Luke"+ChatColor.DARK_PURPLE+"SMP"+ChatColor.RESET+" "+ChatColor.GRAY+ChatColor.BOLD+ChatColor.MAGIC+"|"+ChatColor.RESET+" "+message);
//                }
//            }
//        }
//    }
}
