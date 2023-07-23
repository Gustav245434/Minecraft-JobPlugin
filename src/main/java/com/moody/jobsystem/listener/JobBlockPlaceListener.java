package com.moody.jobsystem.listener;

import com.moody.jobsystem.ChunkPersistentData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class JobBlockPlaceListener implements Listener {

    @EventHandler
    public void onJobBlockPlace(BlockPlaceEvent event) {

        switch (event.getBlockPlaced().getType()) {
            case OAK_LOG, SPRUCE_LOG, BIRCH_LOG, JUNGLE_LOG, ACACIA_LOG, DARK_OAK_LOG, MANGROVE_LOG:

                ChunkPersistentData persistentData = new ChunkPersistentData(event.getBlockPlaced().getChunk());
                    persistentData.addLocation(event.getBlockPlaced().getLocation());
                    event.getPlayer().sendMessage(persistentData.convertLocationsToString());

                break;
        }

    }

}
