package net.lukesmp.main;

import org.bukkit.*;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityPortalEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.world.WorldInitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffectType;

import java.awt.*;
import java.util.Random;


public final class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getPluginManager().registerEvents(this, this);
        new WorldCreator("world1").createWorld();
        new WorldCreator("world1_nether").environment(World.Environment.NETHER).createWorld();
        new WorldCreator("world2").createWorld();
        new WorldCreator("world2_nether").environment(World.Environment.NETHER).createWorld();
        new WorldCreator("world3").createWorld();
        new WorldCreator("world3_nether").environment(World.Environment.NETHER).createWorld();
        new WorldCreator("world4").createWorld();
        new WorldCreator("world4_nether").environment(World.Environment.NETHER).createWorld();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    Random rand = new Random();

    @EventHandler
    public void playerPortalEvent(PlayerPortalEvent event) {
        Player player = event.getPlayer();
        World world = player.getWorld();
        if (world.getEnvironment()==World.Environment.NORMAL) {
            event.getTo().setWorld(Bukkit.getWorld(world.getName()+"_nether"));
        }
        if (world.getEnvironment()==World.Environment.NETHER) {
            if (world==Bukkit.getWorld("world1_nether")) {
                event.getTo().setWorld(Bukkit.getWorld("world1"));
            }
            else if (world==Bukkit.getWorld("world2_nether")) {
                event.getTo().setWorld(Bukkit.getWorld("world2"));
            }
            else if (world==Bukkit.getWorld("world3_nether")) {
                event.getTo().setWorld(Bukkit.getWorld("world3"));
            }
            else if (world==Bukkit.getWorld("world4_nether")) {
                event.getTo().setWorld(Bukkit.getWorld("world4"));
            }
        }

    }

    //Remove spawn chunks from memory
    @EventHandler
    public void onInit(WorldInitEvent event) {
        event.getWorld().setKeepSpawnInMemory(false);
    }

    //world switcher
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("getworld")){
            if (sender instanceof Player){
                Player player = (Player) sender;
                player.sendMessage(player.getWorld().getName());
            }
            return true;
        }
        if (command.getName().equalsIgnoreCase("world")) {
            if (args.length == 0) {
                sender.sendMessage(ChatColor.RED + "Please specify a world!");
                return true;
            }
            if (args.length == 1) {
                World world = Bukkit.getWorld(args[0]);
                if (world == null) {
                    sender.sendMessage(ChatColor.RED + "That world does not exist!");
                    return true;
                }
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    player.teleport(world.getSpawnLocation());
                    player.sendMessage(ChatColor.GREEN + "Teleported to " + world.getName());
                    return true;
                }
                sender.sendMessage(ChatColor.RED + "You must be a player to use this command!");
                return true;
            }
        }
        return false;
    }




    //kill on 'The B'
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event){
        Player player=event.getPlayer();
        Location loc=player.getLocation();
        if((loc.getWorld()==Bukkit.getWorld("s4world"))){
            if((loc.getBlockX() >= -521) && (player.getLocation().getBlockX() <= -520)){
                if((loc.getBlockY() == 68)){
                    if((loc.getBlockZ() >= -180) && (player.getLocation().getBlockZ() <= -179)){
                        player.setHealth(0);
                    }
                }
            }
        }
    }

    //custom chat messages
    @EventHandler
    public void playerChatEvent(PlayerChatEvent event){
        String player=event.getPlayer().getDisplayName();
        String chat=event.getMessage();
        event.setFormat(ChatColor.DARK_AQUA+player+ChatColor.RESET+" "+ChatColor.GRAY+ChatColor.BOLD+ChatColor.MAGIC+"|"+ChatColor.RESET+" "+ChatColor.RESET+ChatColor.GRAY+chat);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Player player=event.getPlayer();

        //tab header/footer
        player.setPlayerListHeader(ChatColor.DARK_AQUA+""+ChatColor.BOLD+"LukeSMP"+ChatColor.GRAY+""+ChatColor.BOLD+""+ChatColor.MAGIC+" | "+ChatColor.DARK_PURPLE+"Season 5");
        player.setPlayerListFooter(ChatColor.GRAY+""+ChatColor.BOLD+ChatColor.MAGIC+" | "+ChatColor.RED+"mc.lukesmp.net"+ChatColor.GRAY+ChatColor.BOLD+ChatColor.MAGIC+" | ");

        //title at join
        player.sendTitle(ChatColor.DARK_AQUA+""+ChatColor.BOLD+"Luke"+ChatColor.DARK_PURPLE+""+ChatColor.BOLD+"SMP",ChatColor.RED+"Season 5");

        //change join message
        event.setJoinMessage(ChatColor.DARK_AQUA+"Luke"+ChatColor.DARK_PURPLE+"SMP"+ChatColor.RESET+" "+ChatColor.GRAY+ChatColor.BOLD+ChatColor.MAGIC+"|"+ChatColor.RESET+" "+ChatColor.RESET+ChatColor.GREEN+player.getDisplayName()+ChatColor.GRAY+" has joined");
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
        Player player=event.getPlayer();
        //change quit message
        event.setQuitMessage(ChatColor.DARK_AQUA+"Luke"+ChatColor.DARK_PURPLE+"SMP"+ChatColor.GRAY+""+ChatColor.BOLD+ChatColor.MAGIC+" | "+ChatColor.RED+player.getDisplayName()+ChatColor.GRAY+" has left");
    }

    @EventHandler
    public void playerDeathEvent(PlayerDeathEvent event){
        Player player=event.getEntity().getPlayer();
        Location l = event.getEntity().getLocation();
        World w = event.getEntity().getWorld();
        String deathMessage=event.getDeathMessage();
        Location deathLocation = event.getEntity().getLocation();
        if (event.getEntity().getKiller() != null){
            String killer=event.getEntity().getKiller().getDisplayName();
            String deathMessage1=deathMessage.replace(player.getDisplayName(), ChatColor.RED+player.getDisplayName()+ChatColor.GRAY);
            String deathMessage2=deathMessage1.replace(killer, ChatColor.GREEN+killer+ChatColor.GRAY);
            String deathMessage3=deathMessage2.replace("[",ChatColor.AQUA+"[");
            event.setDeathMessage(ChatColor.DARK_AQUA+"Luke"+ChatColor.DARK_PURPLE+"SMP"+ChatColor.RESET+" "+ChatColor.GRAY+ChatColor.BOLD+ChatColor.MAGIC+"|"+ChatColor.RESET+" "+deathMessage3);
        } else{
            String deathMessage1=deathMessage.replace(player.getDisplayName(), ChatColor.RED+player.getDisplayName()+ChatColor.GRAY);
            String deathMessage2=deathMessage1.replace("[",ChatColor.AQUA+"[");
            event.setDeathMessage(ChatColor.DARK_AQUA+"Luke"+ChatColor.DARK_PURPLE+"SMP"+ChatColor.RESET+" "+ChatColor.GRAY+ChatColor.BOLD+ChatColor.MAGIC+"|"+ChatColor.RESET+" "+deathMessage2);
        }

        //died on B
        if((deathLocation.getBlockX() >= -521) && (player.getLocation().getBlockX() <= -520)){
            if((deathLocation.getBlockY() == 68)){
                if((deathLocation.getBlockZ() >= -180) && (player.getLocation().getBlockZ() <= -179)){
                    event.setDeathMessage(ChatColor.DARK_AQUA+"Luke"+ChatColor.DARK_PURPLE+"SMP"+ChatColor.RESET+" "+ChatColor.GRAY+ChatColor.BOLD+ChatColor.MAGIC+"|"+ChatColor.RED+" "+player.getDisplayName()+ChatColor.GRAY+" recieved a JUG");
                }
            }
        }

        //SmexyLuke death
        if (player.getUniqueId().toString().equals("46257261-7468-4a8b-bb32-b7f5a78f7a0a")){
            w.dropItemNaturally(l, new ItemStack(Material.GUNPOWDER, rand.nextInt(4)));
        }
        //Light_5_Squid death
        if (player.getUniqueId().toString().equals("32eb72fb-daa3-4f9d-91b6-82031daac3a3")){
            if (player.hasPotionEffect(PotionEffectType.GLOWING)){
                w.dropItemNaturally(l, new ItemStack(Material.GLOW_INK_SAC, rand.nextInt(4)));
            } else{
                w.dropItemNaturally(l, new ItemStack(Material.INK_SAC, rand.nextInt(4)));
            }
        }
        //ankleBowl death
        if (player.getUniqueId().toString().equals("f9f3cc81-df7e-4f99-ab6c-72967f526b51")){
            w.dropItemNaturally(l, new ItemStack(Material.BOWL, rand.nextInt(4)));
        }
        //FruityMaybe death
        if (player.getUniqueId().toString().equals("2c1f5eaf-cc7e-4016-a836-05a81e300ca4")){
            w.dropItemNaturally(l, new ItemStack(Material.APPLE, rand.nextInt(4)));
        }
    }

    //custom pet death messages
    @EventHandler
    public void entityDamageEvent(EntityDamageEvent event){
        Entity entity=event.getEntity();
        if (entity instanceof Tameable){
            Tameable tameable=(Tameable) entity;
            if (tameable.isTamed()){
                if (tameable.getHealth()-event.getFinalDamage()<=0){
                    tameable.remove();
                    event.setCancelled(true);
                    String ownerName=tameable.getOwner().getName();
                    String petName=tameable.getName();
                    String message="";
                    switch (event.getCause()){
                        case BLOCK_EXPLOSION:
                            message= ChatColor.RED+petName+ChatColor.GRAY+" blew up";
                            break;
                        case CONTACT:
                            message=ChatColor.RED+petName+ChatColor.GRAY+" shouldn't have touched that";
                            break;
                        case CRAMMING:
                            message=ChatColor.RED+petName+ChatColor.GRAY+" decided it was too crowded";
                            break;
                        case DRAGON_BREATH:
                            message=ChatColor.RED+petName+ChatColor.GRAY+" was breathed on too hard by a dragon";
                            break;
                        case DROWNING:
                            message=ChatColor.RED+petName+ChatColor.GRAY+" learned the hard way that they couldn't breathe underwater";
                            break;
                        case ENTITY_ATTACK:
                        case ENTITY_SWEEP_ATTACK:
                        case THORNS:
                            Entity killer = tameable.getKiller();
                            if (killer instanceof Player){
                                message=ChatColor.RED+petName+ChatColor.GRAY+" was slain by "+ChatColor.RED+killer;
                            }else{
                                message=ChatColor.RED+petName+ChatColor.GRAY+" was slain";
                            }
                            break;
                        case ENTITY_EXPLOSION:
                            message=ChatColor.RED+petName+ChatColor.GRAY+" was blown up by a creeper, aww man";
                            break;
                        case FALL:
                            message=ChatColor.RED+petName+ChatColor.GRAY+" realized they couldn't fly";
                            break;
                        case FALLING_BLOCK:
                            message=ChatColor.RED+petName+ChatColor.GRAY+" should've wore a helmet";
                            break;
                        case FIRE:
                        case FIRE_TICK:
                        case LAVA:
                            message=ChatColor.RED+petName+ChatColor.GRAY+""+ChatColor.BOLD+" HATES "+ChatColor.RESET+""+ChatColor.GRAY+ownerName+" so they burned to death";
                            break;
                        case FREEZE:
                            message=ChatColor.RED+petName+ChatColor.GRAY+" tried to swim in powdered snow";
                            break;
                        case HOT_FLOOR:
                            message=ChatColor.RED+petName+ChatColor.GRAY+" discovered that the floor is too hot";
                            break;
                        case LIGHTNING:
                            message=ChatColor.RED+petName+ChatColor.GRAY+" met their end due to Thor";
                            break;
                        case MAGIC:
                            message=ChatColor.RED+petName+ChatColor.GRAY+" went Avada Kedavra";
                            break;
                        case PROJECTILE:
                            message=ChatColor.RED+petName+ChatColor.GRAY+" was shot";
                            break;
                        case SUFFOCATION:
                            message=ChatColor.RED+petName+ChatColor.GRAY+" can only breathe air";
                            break;
                        case SUICIDE:
                            message=ChatColor.RED+petName+ChatColor.GRAY+""+ChatColor.BOLD+" HATES "+ChatColor.RESET+""+ChatColor.GRAY+ownerName+" so they committed suicide";
                            break;
                        case VOID:
                            message=ChatColor.RED+petName+ChatColor.GRAY+" fell into the nothing";
                            break;
                        case WITHER:
                            message=ChatColor.RED+petName+ChatColor.GRAY+" died a slow and painful death due to withering";
                            break;
                        default:
                            message=ChatColor.RED+petName+ChatColor.GRAY+" died";
                            break;
                    }
                    Bukkit.broadcastMessage(ChatColor.DARK_AQUA+"Luke"+ChatColor.DARK_PURPLE+"SMP"+ChatColor.RESET+" "+ChatColor.GRAY+ChatColor.BOLD+ChatColor.MAGIC+"|"+ChatColor.RESET+" "+message);
                }
            }
        }
    }
}
