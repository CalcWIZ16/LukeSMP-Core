package net.lukesmp.lukesmpcore;

import org.bukkit.*;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class LukeSMPCore extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getPluginManager().registerEvents(this, this);
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
}
