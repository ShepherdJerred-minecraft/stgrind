package com.shepherdjerred.stgrind.listeners;

import com.shepherdjerred.stgrind.Main;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class GrindListener implements Listener {

    private static List<EntityType> hostileMobs = new ArrayList<>(Arrays.asList(
            EntityType.BLAZE,
            EntityType.CREEPER,
            EntityType.GUARDIAN,
            EntityType.ENDERMITE,
            EntityType.GHAST,
            EntityType.MAGMA_CUBE,
            EntityType.SHULKER,
            EntityType.SILVERFISH,
            EntityType.SKELETON,
            EntityType.SLIME,
            EntityType.WITCH,
            EntityType.ZOMBIE
    ));

    private static ConcurrentHashMap<List<Integer>, Integer> chunks = new ConcurrentHashMap<>();

    public static void removeOld() {
        Main.getInstance().getServer().getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), () -> chunks.entrySet().forEach(entry -> {

            if (entry.getValue() < 50)
                chunks.put(entry.getKey(), entry.getValue() - 1);
            else if (entry.getValue() < 75)
                chunks.put(entry.getKey(), entry.getValue() / 4);
            else if (entry.getValue() < 150)
                chunks.put(entry.getKey(), entry.getValue() / 3);
            else
                chunks.put(entry.getKey(), entry.getValue() / 2);

            if (entry.getValue() == 0)
                chunks.remove(entry.getKey());

        }), 6000L, 6000L);
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {

        List<Integer> chunkId = new ArrayList<>(Arrays.asList(
                event.getEntity().getLocation().getChunk().getX(),
                event.getEntity().getLocation().getChunk().getZ())
        );

        if (hostileMobs.contains(event.getEntity().getType())) {
            if (!chunks.containsKey(chunkId))
                chunks.put(chunkId, 1);
            else {
                chunks.put(chunkId, chunks.get(chunkId) + 1);

                event.setDroppedExp(event.getDroppedExp() / (chunks.get(chunkId) / 10));

                if (chunks.get(chunkId) > 75 && new Random().nextInt(5) + 1 > chunks.get(chunkId)) {
                    event.getDrops().clear();
                }
            }
        }

    }

}
