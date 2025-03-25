package net.lukesmp.lukeSMPCore;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.UUID;

public final class LukeSMPCore extends JavaPlugin implements Listener {
    private final ArrayList<UUID> playersInAbyssalSpire = new ArrayList<>();

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        Bukkit.getPluginManager().registerEvents(new LukeSMPEvent(), this);
        Bukkit.getPluginManager().registerEvents(new LukeSMPWorldSpecificEvent(), this);
        Bukkit.getPluginManager().registerEvents(new TransOrbisEvent(this), this);
        Bukkit.getPluginManager().registerEvents(new CustomTrades(), this);
        Bukkit.getPluginManager().registerEvents(new JoinLeaveManager(this), this);

        this.getCommand("world").setExecutor(new LukeSMPCommand());
        this.getCommand("getworld").setExecutor(new LukeSMPCommand());
        this.getCommand("retrieve").setExecutor(new LukeSMPCommand());

        new WorldCreator("s1world").createWorld();
        new WorldCreator("s1world_nether").environment(World.Environment.NETHER).createWorld();
        new WorldCreator("s2world").createWorld();
        new WorldCreator("s2world_nether").environment(World.Environment.NETHER).createWorld();
        new WorldCreator("s3world").createWorld();
        new WorldCreator("s3world_nether").environment(World.Environment.NETHER).createWorld();
        new WorldCreator("s4world").createWorld();
        new WorldCreator("s4world_nether").environment(World.Environment.NETHER).createWorld();

        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }

        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        getServer().getMessenger().registerOutgoingPluginChannel(this, "spire:inv");
    }

    @Override
    public void onDisable() {}

    public void addPlayerToAbyssalSpire(Player player) {
        playersInAbyssalSpire.add(player.getUniqueId());
    }

    public boolean removePlayerFromAbyssalSpire(Player player) {
        if (playersInAbyssalSpire.contains(player.getUniqueId())) {
            playersInAbyssalSpire.remove(player.getUniqueId());
            return true;
        }
        return false;
    }
}