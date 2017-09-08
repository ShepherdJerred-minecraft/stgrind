package com.shepherdjerred.stgrind.listeners;

import com.shepherdjerred.stgrind.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class CobbleListener implements Listener {

    private static ConcurrentHashMap<Location, Integer> blocks = new ConcurrentHashMap<>();

    public static void removeOld() {

        Main.getInstance().getServer().getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), () -> blocks.entrySet().forEach(entry -> {

            blocks.put(entry.getKey(), entry.getValue() - 1);

            if (entry.getValue() == 0)
                blocks.remove(entry.getKey());

        }), 6000L, 6000L);

    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {

        Block block = event.getBlock();
        Location location = block.getLocation();

        if (block.getType() == Material.COBBLESTONE)

            if (!blocks.containsKey(location))
                blocks.put(location, 1);
            else {
                Integer count = blocks.get(location) + 1;

                blocks.put(location, count);

                if (count < 3) {
                    if (new Random().nextInt(3) + 1 < count) {
                        event.getBlock().getDrops().clear();
                        Bukkit.broadcastMessage("Cleared drops!");
                        return;
                    }

                    if (new Random().nextInt(6) + 1 < count) {
                        event.setCancelled(true);
                        event.getBlock().setType(Material.OBSIDIAN);
                        Bukkit.broadcastMessage("Set obsidian!");
                    }
                }
            }

    }

}
