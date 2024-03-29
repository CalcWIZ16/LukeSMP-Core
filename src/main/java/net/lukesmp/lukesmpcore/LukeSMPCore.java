package net.lukesmp.lukesmpcore;

import org.bukkit.*;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class LukeSMPCore extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getPluginManager().registerEvents(this, this);
        Bukkit.getPluginManager().registerEvents(new LukeSMPEvent(), this);
        Bukkit.getPluginManager().registerEvents(new LukeSMPWorldSpecificEvent(), this);
        this.getCommand("world").setExecutor(new LukeSMPCommand());
        this.getCommand("getworld").setExecutor(new LukeSMPCommand());

        World world1 = new WorldCreator("world1").createWorld();
        World world1Nether = new WorldCreator("world1_nether").environment(World.Environment.NETHER).createWorld();
        World world2 = new WorldCreator("world2").createWorld();
        World world2Nether = new WorldCreator("world2_nether").environment(World.Environment.NETHER).createWorld();
        World world3 = new WorldCreator("world3").createWorld();
        World world3Nether = new WorldCreator("world3_nether").environment(World.Environment.NETHER).createWorld();
        World world4 = new WorldCreator("world4").createWorld();
        World world4Nether = new WorldCreator("world4_nether").environment(World.Environment.NETHER).createWorld();

//        File deaths = new File("plugins/LukeSMPCore/deaths.yml");
//        if (!deaths.exists()) {
//            try {
//                deaths.createNewFile();
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Player player=event.getPlayer();

        //tab header/footer
        player.setPlayerListHeader(ChatColor.DARK_AQUA+""+ChatColor.BOLD+"LukeSMP"+ChatColor.GRAY+""+ChatColor.BOLD+""+ChatColor.MAGIC+" | "+ChatColor.DARK_PURPLE+"Season 5");
        player.setPlayerListFooter(ChatColor.GRAY+""+ChatColor.BOLD+ChatColor.MAGIC+" | "+ChatColor.RED+"mc.lukesmp.net"+ChatColor.GRAY+ChatColor.BOLD+ChatColor.MAGIC+" | ");

        //change join message
        event.setJoinMessage(ChatColor.DARK_AQUA+"Luke"+ChatColor.DARK_PURPLE+"SMP"+ChatColor.RESET+" "+ChatColor.GRAY+ChatColor.BOLD+ChatColor.MAGIC+"|"+ChatColor.RESET+" "+ChatColor.RESET+ChatColor.GREEN+player.getDisplayName()+ChatColor.GRAY+" has joined");

        //title at join
        int random = (int) (Math.random() * 5);
        if (random==2){
            player.sendTitle(ChatColor.DARK_AQUA+""+ChatColor.BOLD+"Lucy"+ChatColor.DARK_PURPLE+""+ChatColor.BOLD+"SMP",ChatColor.RED+"Season 5");
            new BukkitRunnable() {
                @Override
                public void run() {
                    player.sendTitle(ChatColor.DARK_AQUA+""+ChatColor.MAGIC+ChatColor.BOLD+"Lucy"+ChatColor.DARK_PURPLE+""+ChatColor.BOLD+"SMP",ChatColor.RED+"Season 5", 0, 20, 0);
                }
            }.runTaskLater(this,30L);
            new BukkitRunnable() {
                @Override
                public void run() {
                    player.sendTitle(ChatColor.DARK_PURPLE+""+ChatColor.BOLD+"SMP",ChatColor.RED+"Season 5", 0, 20, 0);
                }
            }.runTaskLater(this, 40L);
            new BukkitRunnable() {
                @Override
                public void run() {
                    player.sendTitle(ChatColor.DARK_AQUA+""+ChatColor.BOLD+"L"+ChatColor.DARK_PURPLE+""+ChatColor.BOLD+"SMP",ChatColor.RED+"Season 5", 0, 20, 0);
                }
            }.runTaskLater(this, 42L);
            new BukkitRunnable() {
                @Override
                public void run() {
                    player.sendTitle(ChatColor.DARK_AQUA+""+ChatColor.BOLD+"Lu"+ChatColor.DARK_PURPLE+""+ChatColor.BOLD+"SMP",ChatColor.RED+"Season 5", 0, 20, 0);
                }
            }.runTaskLater(this, 44L);
            new BukkitRunnable() {
                @Override
                public void run() {
                    player.sendTitle(ChatColor.DARK_AQUA+""+ChatColor.BOLD+"Luk"+ChatColor.DARK_PURPLE+""+ChatColor.BOLD+"SMP",ChatColor.RED+"Season 5", 0, 20, 0);
                }
            }.runTaskLater(this, 46L);
            new BukkitRunnable() {
                @Override
                public void run() {
                    player.sendTitle(ChatColor.DARK_AQUA+""+ChatColor.BOLD+"Luke"+ChatColor.DARK_PURPLE+""+ChatColor.BOLD+"SMP",ChatColor.RED+"Season 5", 0, 70, 20);
                }
            }.runTaskLater(this, 48L);
        }
        else {
            player.sendTitle(ChatColor.DARK_AQUA+""+ChatColor.BOLD+"Luke"+ChatColor.DARK_PURPLE+""+ChatColor.BOLD+"SMP",ChatColor.RED+"Season 5");
        }
    }
}
