package com.shepherdjerred.stgrind.listeners;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class GolemListener implements Listener {

    @EventHandler
    public void onGolenDeath(EntityDeathEvent event) {

        if (event.getEntity().getType() == EntityType.IRON_GOLEM) {
            event.getDrops().remove(new ItemStack(Material.IRON_INGOT));
            event.setDroppedExp(0);
        }

    }

}
