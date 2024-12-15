package net.lukesmp.lukeSMPCore;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class LukeSMPCore extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getPluginManager().registerEvents(this, this);
        Bukkit.getPluginManager().registerEvents(new LukeSMPEvent(), this);
        Bukkit.getPluginManager().registerEvents(new LukeSMPWorldSpecificEvent(), this);
        Bukkit.getPluginManager().registerEvents(new TransOrbisEvent(this), this);
        Bukkit.getPluginManager().registerEvents(new CustomTrades(), this);

        this.getCommand("world").setExecutor(new LukeSMPCommand());
        this.getCommand("getworld").setExecutor(new LukeSMPCommand());

        World world1 = new WorldCreator("s1world").createWorld();
        World world1Nether = new WorldCreator("s1world_nether").environment(World.Environment.NETHER).createWorld();
        World world2 = new WorldCreator("s2world").createWorld();
        World world2Nether = new WorldCreator("s2world_nether").environment(World.Environment.NETHER).createWorld();
        World world3 = new WorldCreator("s3world").createWorld();
        World world3Nether = new WorldCreator("s3world_nether").environment(World.Environment.NETHER).createWorld();
        World world4 = new WorldCreator("s4world").createWorld();
        World world4Nether = new WorldCreator("s4world_nether").environment(World.Environment.NETHER).createWorld();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
