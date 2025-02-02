package net.lukesmp.lukeSMPCore;


import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.ArrayList;

public class InventoryRetriever {

    public static ArrayList<ItemStack> readItemStacksFromFile(File file) {
        Bukkit.getConsoleSender().sendMessage("Reading inventory from file: " + file.getName());
        ArrayList<ItemStack> itemStacks = new ArrayList<>();

        //read file line by line


        return itemStacks;
    }
}