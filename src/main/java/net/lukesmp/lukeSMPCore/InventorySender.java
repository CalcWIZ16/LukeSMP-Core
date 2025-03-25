package net.lukesmp.lukeSMPCore;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.Base64;

public class InventorySender {
    public static void sendInventoryData(JavaPlugin plugin, Player player) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("SendInventory");
        out.writeUTF(player.getUniqueId().toString());
        out.writeUTF(serializeInventory(player)); // Serialize inventory

        player.sendPluginMessage(plugin, "spire:inv", out.toByteArray());
    }

    public static String serializeInventory(Player player) {
        YamlConfiguration config = new YamlConfiguration();

        // Iterate through 36 main slots
        for (int i = 0; i < 36; i++) {
            config.set("slot_" + i, player.getInventory().getItem(i));
        }

        // Add armor and offhand
        config.set("armor_helmet", player.getInventory().getHelmet());
        config.set("armor_chestplate", player.getInventory().getChestplate());
        config.set("armor_leggings", player.getInventory().getLeggings());
        config.set("armor_boots", player.getInventory().getBoots());
        config.set("offhand", player.getInventory().getItemInOffHand());

        return Base64.getEncoder().encodeToString(config.saveToString().getBytes()); // Convert to Base64
    }
}