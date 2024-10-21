package net.lukesmp.lukesmpcore;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.WanderingTrader;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Merchant;
import org.bukkit.inventory.MerchantInventory;
import org.bukkit.inventory.MerchantRecipe;

import java.util.ArrayList;

public class CustomTrades implements Listener {

//    @EventHandler
//    public void onInventoryOpen(InventoryOpenEvent event) {
//        Bukkit.getConsoleSender().sendMessage("Inventory opened");
//        if(event.getInventory() instanceof MerchantInventory) {
//            Bukkit.getConsoleSender().sendMessage("instance of merchant");
//            MerchantInventory inventory = (MerchantInventory) event.getInventory();
//            Merchant merchant = inventory.getMerchant();
//
//            ArrayList<MerchantRecipe> newRecipes = new ArrayList<>();
//            for(MerchantRecipe recipe : merchant.getRecipes()) {
//                newRecipes.add(recipe);
//            }
//
//            if(inventory.getHolder() == null) {
//                return;
//            }
//
//            MerchantRecipe firstCustomRecipe = new MerchantRecipe(new ItemStack(Material.END_STONE, 8), 8);
//            firstCustomRecipe.addIngredient(new ItemStack(Material.EMERALD, 1));
//
//            newRecipes.add(firstCustomRecipe);
//
//            merchant.setRecipes(newRecipes);
//
//        }
//    }

    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent event) {
        if(event.getEntity() instanceof WanderingTrader) {
            WanderingTrader trader = (WanderingTrader) event.getEntity();
            MerchantInventory inventory = (MerchantInventory) trader.getInventory();
            Merchant merchant = inventory.getMerchant();

            ArrayList<MerchantRecipe> newRecipes = new ArrayList<>();
            for(MerchantRecipe recipe : merchant.getRecipes()) {
                newRecipes.add(recipe);
            }

            if(inventory.getHolder() == null) {
                return;
            }

            MerchantRecipe firstCustomRecipe = new MerchantRecipe(new ItemStack(Material.END_STONE, 8), 8);
            firstCustomRecipe.addIngredient(new ItemStack(Material.EMERALD, 1));

            newRecipes.add(firstCustomRecipe);

            merchant.setRecipes(newRecipes);
        }
    }
}
