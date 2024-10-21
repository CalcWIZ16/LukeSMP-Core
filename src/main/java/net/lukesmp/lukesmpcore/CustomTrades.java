package net.lukesmp.lukesmpcore;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Merchant;
import org.bukkit.inventory.MerchantInventory;
import org.bukkit.inventory.MerchantRecipe;

import java.util.ArrayList;

public class CustomTrades implements Listener {

    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent event) {
        Bukkit.getConsoleSender().sendMessage("Inventory opened");
        if(event.getInventory() instanceof MerchantInventory) {
            Bukkit.getConsoleSender().sendMessage("instance of merchant");
            MerchantInventory inventory = (MerchantInventory) event.getInventory();
            Merchant merchant = inventory.getMerchant();

            if(inventory.getHolder() == null) {
                return;
            }

            MerchantRecipe firstCustomRecipe = new MerchantRecipe(new ItemStack(Material.END_STONE, 8), 8);
            firstCustomRecipe.addIngredient(new ItemStack(Material.EMERALD, 1));


            merchant.setRecipe(merchant.getRecipeCount()-1, firstCustomRecipe);


        }
    }
}
