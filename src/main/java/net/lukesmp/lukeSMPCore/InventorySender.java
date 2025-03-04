package net.lukesmp.lukeSMPCore;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class InventorySender {

    private final JavaPlugin plugin;

    public InventorySender(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void sendInventory(Player player, String targetServer) {
        ItemStack[] inventoryContents = player.getInventory().getContents();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);

        try {
            dataOutputStream.writeUTF("Forward");
            dataOutputStream.writeUTF(targetServer);
            dataOutputStream.writeUTF("inv");

            ByteArrayOutputStream msgBytes = new ByteArrayOutputStream();
            DataOutputStream msgOut = new DataOutputStream(msgBytes);

            msgOut.writeUTF(player.getName());
            msgOut.writeInt(inventoryContents.length);

            for (ItemStack item : inventoryContents) {
                if (item != null) {
                    msgOut.writeUTF(item.getType().name());
                    msgOut.writeInt(item.getAmount());
                    // Add more item data as needed (e.g., item meta)
                } else {
                    msgOut.writeUTF("null");
                }
            }

            dataOutputStream.writeShort(msgBytes.toByteArray().length);
            dataOutputStream.write(msgBytes.toByteArray());

            player.sendPluginMessage(plugin, "lukesmp:inv", byteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}