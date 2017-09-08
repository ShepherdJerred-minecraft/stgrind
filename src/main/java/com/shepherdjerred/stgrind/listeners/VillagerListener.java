package com.shepherdjerred.stgrind.listeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

import java.util.Random;

public class VillagerListener implements Listener {

    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent event) {

        if (event.getEntity().getType() == EntityType.VILLAGER) {

            if (new Random().nextInt(100) + 1 < 50)
                event.setCancelled(true);

            int count = 0;

            for (Entity entity : event.getEntity().getNearbyEntities(10, 5, 10)) {

                if (entity.getType() == EntityType.VILLAGER)
                    count++;

                if (count > 5)
                    event.setCancelled(true);

            }

        }

    }

}
