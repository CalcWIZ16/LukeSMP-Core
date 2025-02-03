package net.lukesmp.lukeSMPCore;


import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class InventoryRetriever {

    public static ArrayList<ItemStack> readItemStacksFromContainer(Location location) {
        ArrayList<ItemStack> itemStacks = new ArrayList<>();
        Block block = location.getBlock();
        BlockState blockState = block.getState();

        if(blockState instanceof Chest) {
            Chest chest = (Chest) blockState;
            Inventory inventory = chest.getInventory();
            for (int i = 0; i < inventory.getSize(); i++) {
                ItemStack itemStack = inventory.getItem(i);
                if (itemStack != null) {
                    itemStacks.add(itemStack);
                    Bukkit.getConsoleSender().sendMessage("Item: " + itemStack.getType().name() + " Amount: " + itemStack.getAmount());
                }
            }
        }


        return itemStacks;
    }

    public static void giveItemStacksToPlayer(ArrayList<ItemStack> itemStacks, Player player) {
        for (ItemStack itemStack : itemStacks) {
            if (player.getInventory().firstEmpty() == -1) {
                player.getWorld().dropItem(player.getLocation(), itemStack);
            } else {
                player.getInventory().addItem(itemStack);
            }
        }
    }
}