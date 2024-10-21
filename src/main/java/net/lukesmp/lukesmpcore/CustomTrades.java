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

import java.util.List;

public class CustomTrades implements Listener {

    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent event) {
        Bukkit.getConsoleSender().sendMessage("Inventory opened");


        if(event.getInventory() instanceof MerchantInventory) {
            MerchantInventory inventory = (MerchantInventory) event.getInventory();
            Merchant merchant = inventory.getMerchant();

            if(inventory.getHolder() == null) {
                return;
            }

            List<MerchantRecipe> recipes = merchant.getRecipes();
            boolean customRecipeExists = false;
            for(MerchantRecipe recipe : recipes) {
                if(recipe.getResult().getType() == Material.END_STONE && recipe.getResult().getAmount() == 8) {
                    customRecipeExists = true;
                    break;
                }
            }

            if (!customRecipeExists) {
                MerchantRecipe firstCustomRecipe = new MerchantRecipe(new ItemStack(Material.END_STONE, 8), 8);
                firstCustomRecipe.addIngredient(new ItemStack(Material.EMERALD, 1));
                recipes.add(firstCustomRecipe);
                merchant.setRecipes(recipes);
            }
        }
    }
}
