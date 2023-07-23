package com.moody.jobsystem.listener;

import com.moody.jobsystem.ChunkPersistentData;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;

import java.util.ArrayList;
import java.util.List;

public class JobBlockPistonMoveListener implements Listener {

    @EventHandler
    public void onPistonExtend(BlockPistonExtendEvent event) {
        List<Block> blockList = new ArrayList<>();

        // Suche der Blöcke, die schon platziert wurden und jetzt gemoved werden sollen

        for (Block aktuellerBlock : event.getBlocks()) {
            switch (aktuellerBlock.getType()) {
                case OAK_LOG, SPRUCE_LOG, BIRCH_LOG, JUNGLE_LOG, ACACIA_LOG, DARK_OAK_LOG, MANGROVE_LOG:

                    ChunkPersistentData data = new ChunkPersistentData(aktuellerBlock.getChunk());
                    if (data.contains(aktuellerBlock.getLocation())) {
                        blockList.add(aktuellerBlock);
                    }

                    break;
            }
        }

        // Locations in den ChunkDataContainer um ein Block in Richtung PistonMovement ändern

        for (Block aktuellerBlock : blockList) {
            ChunkPersistentData dataBeforeMovement = new ChunkPersistentData(aktuellerBlock.getChunk());
            Location newBlockLocation = new Location(aktuellerBlock.getWorld(), aktuellerBlock.getX(), aktuellerBlock.getY(), aktuellerBlock.getZ());
            switch (event.getDirection()) {
                case UP -> newBlockLocation.add(0, 1, 0);
                case DOWN -> newBlockLocation.add(0, -1, 0);
                case EAST -> newBlockLocation.add(1, 0, 0);
                case WEST -> newBlockLocation.add(-1, 0, 0);
                case NORTH -> newBlockLocation.add(0, 0, -1);
                case SOUTH -> newBlockLocation.add(0, 0, 1);
            }

            if (!newBlockLocation.getChunk().equals(aktuellerBlock.getChunk())) {
                ChunkPersistentData dataAfterMovement = new ChunkPersistentData(newBlockLocation.getChunk());
                dataAfterMovement.addLocation(newBlockLocation);
                dataBeforeMovement.removeLocation(aktuellerBlock.getLocation());
            } else {

                dataBeforeMovement.addLocation(newBlockLocation);
                dataBeforeMovement.removeLocation(aktuellerBlock.getLocation());

            }


        }

    }

    @EventHandler
    public void onPistonRetract(BlockPistonRetractEvent event) {
        List<Block> blockList = new ArrayList<>();

        // Suche der Blöcke, die schon platziert wurden und jetzt gemoved werden sollen

        for (Block aktuellerBlock : event.getBlocks()) {
            switch (aktuellerBlock.getType()) {
                case OAK_LOG, SPRUCE_LOG, BIRCH_LOG, JUNGLE_LOG, ACACIA_LOG, DARK_OAK_LOG, MANGROVE_LOG:

                    ChunkPersistentData data = new ChunkPersistentData(aktuellerBlock.getChunk());
                    if (data.contains(aktuellerBlock.getLocation())) {
                        blockList.add(aktuellerBlock);
                    }

                    break;
            }
        }

        // Locations in den ChunkDataContainer um ein Block in Richtung PistonMovement ändern

        for (Block aktuellerBlock : blockList) {
            ChunkPersistentData dataBeforeMovement = new ChunkPersistentData(aktuellerBlock.getChunk());
            Location newBlockLocation = new Location(aktuellerBlock.getWorld(), aktuellerBlock.getX(), aktuellerBlock.getY(), aktuellerBlock.getZ());
            switch (event.getDirection()) {
                case UP -> newBlockLocation.add(0, 1, 0);
                case DOWN -> newBlockLocation.add(0, -1, 0);
                case EAST -> newBlockLocation.add(1, 0, 0);
                case WEST -> newBlockLocation.add(-1, 0, 0);
                case NORTH -> newBlockLocation.add(0, 0, -1);
                case SOUTH -> newBlockLocation.add(0, 0, 1);
            }

            if (!newBlockLocation.getChunk().equals(aktuellerBlock.getChunk())) {
                ChunkPersistentData dataAfterMovement = new ChunkPersistentData(newBlockLocation.getChunk());
                dataAfterMovement.addLocation(newBlockLocation);
                dataBeforeMovement.removeLocation(aktuellerBlock.getLocation());
            } else {

                dataBeforeMovement.addLocation(newBlockLocation);
                dataBeforeMovement.removeLocation(aktuellerBlock.getLocation());

            }


        }
    }
}
