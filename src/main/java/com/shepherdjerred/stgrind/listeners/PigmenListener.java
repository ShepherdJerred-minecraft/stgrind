package com.shepherdjerred.stgrind.listeners;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class PigmenListener implements Listener {

    @EventHandler
    public void onPigmenDeath(EntityDeathEvent event) {

        if (event.getEntity().getType() == EntityType.PIG_ZOMBIE) {
            event.getDrops().remove(new ItemStack(Material.GOLD_NUGGET));
            event.getDrops().remove(new ItemStack(Material.GOLD_SWORD));
            event.setDroppedExp(0);
        }

    }

}
