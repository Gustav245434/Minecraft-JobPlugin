package com.moody.jobsystem.listener;

import com.moody.jobsystem.ChunkPersistentData;
import com.moody.jobsystem.JobSystem;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.EntityBlockFormEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.persistence.PersistentDataType;

/**
 * Event für fallende Blöcke
 */

public class JobBlockEntityFormEvent implements Listener {

    @EventHandler
    public void onEntityFormEvent(EntityChangeBlockEvent event) {

        if (event.getBlock().getType() == Material.SAND || event.getTo() == Material.SAND || event.getBlock().getType() == Material.GRAVEL || event.getTo() == Material.GRAVEL) {

            ChunkPersistentData persistentDataNewChunk = new ChunkPersistentData(event.getBlock().getChunk());

            if (event.getBlock().isPassable()) {

                // Getriggert wenn Gravel, Sand,... gerade gelandet ist

                int[] koordinaten = event.getEntity().getPersistentDataContainer().get(new NamespacedKey(JobSystem.getPlugin(), "lastposition"), PersistentDataType.INTEGER_ARRAY);
                ChunkPersistentData persistentDataOldChunk = persistentDataNewChunk;

                if (!event.getBlock().getChunk().equals(new Location(event.getBlock().getWorld(), koordinaten[0], koordinaten[1], koordinaten[2]).getChunk())) {
                    persistentDataOldChunk = new ChunkPersistentData(new Location(event.getBlock().getWorld(), koordinaten[0], koordinaten[1], koordinaten[2]).getChunk());
                }

                if(persistentDataOldChunk.contains(new Location(event.getBlock().getWorld(), koordinaten[0], koordinaten[1], koordinaten[2]))) {
                    persistentDataNewChunk.addLocation(event.getBlock().getLocation());
                    persistentDataOldChunk.removeLocation(new Location(event.getBlock().getWorld(), koordinaten[0], koordinaten[1], koordinaten[2]));
                }

            } else {

                // Getriggert wenn Gravel, Sand,... gerade am runterfallen ist

                // Speichert Koordinaten vom der Location vor dem Fall

                int[] koordinaten = {(int) event.getBlock().getLocation().getX(), (int) event.getBlock().getLocation().getY(), (int) event.getBlock().getLocation().getZ()};
                event.getEntity().getPersistentDataContainer().set(new NamespacedKey(JobSystem.getPlugin(), "lastposition"), PersistentDataType.INTEGER_ARRAY, koordinaten);
            }

        }

    }

}
