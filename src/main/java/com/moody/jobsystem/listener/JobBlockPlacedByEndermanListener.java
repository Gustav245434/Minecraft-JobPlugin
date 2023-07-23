package com.moody.jobsystem.listener;

import com.moody.jobsystem.ChunkPersistentData;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;

public class JobBlockPlacedByEndermanListener implements Listener {

    @EventHandler
    public void onEndermanPlace(EntityChangeBlockEvent event) {

        switch (event.getBlock().getType()) {
            case OAK_LOG, SPRUCE_LOG, BIRCH_LOG, JUNGLE_LOG, ACACIA_LOG, DARK_OAK_LOG, MANGROVE_LOG:

                if (event.getEntityType() == EntityType.ENDERMAN) {
                    event.setCancelled(true);
                    Bukkit.getConsoleSender().sendMessage("gecancelt");
                }


                break;
        }


    }

}
