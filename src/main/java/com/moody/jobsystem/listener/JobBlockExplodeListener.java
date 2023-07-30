package com.moody.jobsystem.listener;

import com.moody.jobsystem.ChunkPersistentData;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public class JobBlockExplodeListener implements Listener {

    @EventHandler
    public void onBlockExplode(EntityExplodeEvent event) {

        for (Block affectedBlocks : event.blockList()) {

            switch (affectedBlocks.getType()) {
                case OAK_LOG, SPRUCE_LOG, BIRCH_LOG, JUNGLE_LOG, ACACIA_LOG, DARK_OAK_LOG, MANGROVE_LOG, DIRT, GRAVEL, SAND, CLAY:
                    new ChunkPersistentData(affectedBlocks.getChunk()).removeLocation(affectedBlocks.getLocation());
                    break;
            }


        }

    }
}
