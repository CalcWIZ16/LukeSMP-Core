package net.lukesmp.lukeSMPCore;


import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

public class InventoryRetriever {

    public static Location getKeyLocation(UUID playerUUID, World world) {
        if (world.getName().equals("s1world")) {
            Bukkit.getConsoleSender().sendMessage("Player UUID: " + playerUUID.toString());
            switch(playerUUID.toString()) {
                case "2c1f5eaf-cc7e-4016-a836-05a81e300ca4": //Charles_InCharge
                    return new Location(world, -92, -63, -44);
                case "3a49ddd2-fe15-429e-b35a-a1ea5ae38155": //mochigengar
                    return new Location(world, -93, -63, -44);
                case "7a3b1fd6-e326-4199-b193-c016dabe6ca3": //octopusloli
                    return new Location(Bukkit.getWorld("world"), -94, -63, -44);
                case "32eb72fb-daa3-4f9d-91b6-82031daac3a3": //Light_5_Squid
                    return new Location(world, -95, -63, -44);
                case "9892dd76-039a-4294-91d8-d2440ade9346": //HeftyHank225
                    return new Location(world, -96, -63, -44);
                case "8cee743b-08a5-4c37-a833-cdf06fb8f5c3": //_yumigasm_
                    return new Location(world, -97, -63, -44);
                case "617370cd-dcdd-4de1-8045-cac18ff67cf2": //Anadakiller
                    return new Location(world, -98, -63, -44);
                case "46257261-7468-4a8b-bb32-b7f5a78f7a0a": //CalcWIZ
                    return new Location(world, -92, -63, -49);
                case "47306497-6118-444f-a72e-e44be2acc73a": //Matteoweo
                    return new Location(world, -93, -63, -49);
                case "49573799-7897-4771-990c-6cfadcc4559e": //LucyMint34
                    return new Location(world, -94, -63, -49);
                case "edab177b-8303-4c09-9e08-21042ff2a5d5": //astronomic_
                    return new Location(world, -95, -63, -49);
                case "f9f3cc81-df7e-4f99-ab6c-72967f526b51": //ankleBowl
                    return new Location(world, -96, -63, -49);
                case "f4198133-2ddc-43e4-b25e-5bbb86236fb7": //oniqn
                    return new Location(world, -97, -63, -49);
                default:
                    return null;
            }
        } else {
            return null;
        }
    }

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