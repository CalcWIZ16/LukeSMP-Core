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
        Bukkit.getConsoleSender().sendMessage("Player UUID: " + playerUUID.toString());
        if (world.getName().equals("s1world")) {
            switch(playerUUID.toString()) {
                case "2c1f5eaf-cc7e-4016-a836-05a81e300ca4": //Charles_InCharge
                    return new Location(world, -92, -63, -44);
                case "3a49ddd2-fe15-429e-b35a-a1ea5ae38155": //mochigengar
                    return new Location(world, -93, -63, -44);
                case "7a3b1fd6-e326-4199-b193-c016dabe6ca3": //octopusloli
                    return new Location(world, -94, -63, -44);
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
        } else if(world.getName().equals("s2world")) {
            switch(playerUUID.toString()) {
                case "2c1f5eaf-cc7e-4016-a836-05a81e300ca4": //Charles_InCharge
                    return new Location(world, -13, -63, 172);
                case "7a3b1fd6-e326-4199-b193-c016dabe6ca3": //octopusloli
                    return new Location(world, -14, -63, 172);
                case "31e19a2c-5425-4645-ac73-0db11d32b731": //Evergren
                    return new Location(world, -15, -63, 172);
                case "32eb72fb-daa3-4f9d-91b6-82031daac3a3": //Light_5_Squid
                    return new Location(world, -16, -63, 172);
                case "34bd903f-d104-4e00-94be-75c9cbf24160": //f1akk1
                    return new Location(world, -17, -63, 172);
                case "527a81b5-190e-4b53-9300-7de72ce9051c": //Aim5300
                    return new Location(world, -18, -63, 172);
                case "5322c15c-d869-44ea-8e99-7e1cef98d13b": //Reesiee_
                    return new Location(world, -19, -63, 172);
                case "617370cd-dcdd-4de1-8045-cac18ff67cf2"://anadakiller
                    return new Location(world, -13, -63, 167);
                case "46257261-7468-4a8b-bb32-b7f5a78f7a0a": //CalcWIZ
                    return new Location(world, -14, -63, 167);
                case "47306497-6118-444f-a72e-e44be2acc73a": //Matteoweo
                    return new Location(world, -15, -63, 167);
                case "edab177b-8303-4c09-9e08-21042ff2a5d5": //astronomic_
                    return new Location(world, -16, -63, 167);
                case "f4198133-2ddc-43e4-b25e-5bbb86236fb7": //oniqn
                    return new Location(world, -17, -63, 167);
                case "8cee743b-08a5-4c37-a833-cdf06fb8f5c3": //_yumigasm_
                    return new Location(world, -18, -63, 167);
                case "9892dd76-039a-4294-91d8-d2440ade9346": //HeftyHank225
                    return new Location(world, -19, -63, 167);
                default:
                    return null;
            }
        } else if(world.getName().equals("s3world")) {
            switch(playerUUID.toString()) {
                case "2c1f5eaf-cc7e-4016-a836-05a81e300ca4": //Charles_InCharge
                    return new Location(world, 52, -63, 166);
                case "7a3b1fd6-e326-4199-b193-c016dabe6ca3": //octopusloli
                    return new Location(world, 51, -63, 166);
                case "8f873c77-5887-4caa-8498-a5dbd1b0020c": //SirDuckV
                    return new Location(world, 50, -63, 166);
                case "32eb72fb-daa3-4f9d-91b6-82031daac3a3": //Light_5_Squid
                    return new Location(world, 49, -63, 166);
                case "34bd903f-d104-4e00-94be-75c9cbf24160": //f1akk1
                    return new Location(world, 48, -63, 166);
                case "527a81b5-190e-4b53-9300-7de72ce9051c": //Aim5300
                    return new Location(world, 47, -63, 166);
                case "5322c15c-d869-44ea-8e99-7e1cef98d13b": //Reesiee_
                    return new Location(world, 46, -63, 166);
                case "9892dd76-039a-4294-91d8-d2440ade9346": //HeftyHank225
                    return new Location(world, 52, -63, 161);
                case "8cee743b-08a5-4c37-a833-cdf06fb8f5c3": //_yumigasm_
                    return new Location(world, 51, -63, 161);
                case "617370cd-dcdd-4de1-8045-cac18ff67cf2": //Anadakiller
                    return new Location(world, 50, -63, 161);
                case "46257261-7468-4a8b-bb32-b7f5a78f7a0a": //CalcWIZ
                    return new Location(world, 49, -63, 161);
                case "47306497-6118-444f-a72e-e44be2acc73a": //Matteoweo
                    return new Location(world, 48, -63, 161);
                case "a0b1a9e2-0479-441e-b977-367e1908e614": //AresBird
                    return new Location(world, 47, -63, 161);
                case "c33fab6b-68e1-42f6-9038-7db3b0cc5a9d": //BrooklynBrew
                    return new Location(world, 46, -63, 161);
                default:
                    return null;
            }
        } else if(world.getName().equals("s4world")) {
            switch(playerUUID.toString()) {
                case "2c1f5eaf-cc7e-4016-a836-05a81e300ca4": //Charles_InCharge
                    return new Location(world, 4, -63, 4);
                case "3a49ddd2-fe15-429e-b35a-a1ea5ae38155": //mochigengar
                    return new Location(world, 3, -63, 4);
                case "03e8ddbb-559a-4dee-b1d3-25c7da0e70a1": //CremmyFresh
                    return new Location(world, 2, -63, 4);
                case "6ab17125-5ea4-4945-a200-75b5114b97ca": //TheYoungNanesh
                    return new Location(world, 1, -63, 4);
                case "8cee743b-08a5-4c37-a833-cdf06fb8f5c3": //_yumigasm_
                    return new Location(world, 0, -63, 4);
                case "09a84c46-0c8e-47e9-b4c1-c7fbb4efee00": //midniii56
                    return new Location(world, -1, -63, 4);
                case "9b4c0010-bcd1-4795-87f5-9bbdea59415f": //DarnedPen
                    return new Location(world, -2, -63, 4);
                case "32eb72fb-daa3-4f9d-91b6-82031daac3a3": //Light_5_Squid
                    return new Location(world, -3, -63, 4);
                case "527a81b5-190e-4b53-9300-7de72ce9051c": //Aim5300
                    return new Location(world, 4, -63, -1);
                case "839eeda3-7116-4b28-8cc9-d5cf5e404eba": //Sayik_
                    return new Location(world, 3, -63, -1);
                case "9892dd76-039a-4294-91d8-d2440ade9346": //HeftyHank225
                    return new Location(world, 2, -63, -1);
                case "617370cd-dcdd-4de1-8045-cac18ff67cf2": //Anadakiller
                    return new Location(world, 1, -63, -1);
                case "46257261-7468-4a8b-bb32-b7f5a78f7a0a": //CalcWIZ
                    return new Location(world, 0, -63, -1);
                case "47306497-6118-444f-a72e-e44be2acc73a": //Matteoweo
                    return new Location(world, -1, -63, -1);
                case "c33fab6b-68e1-42f6-9038-7db3b0cc5a9d": //BrooklynBrew
                    return new Location(world, -2, -63, -1);
                case "f9f3cc81-df7e-4f99-ab6c-72967f526b51": //ankleBowl
                    return new Location(world, -3, -63, -1);
            }
        }
        return null;
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