package com.moody.jobsystem.listener;

import com.moody.jobsystem.ChunkPersistentData;
import com.moody.jobsystem.JobSave;
import com.moody.jobsystem.JobSystem;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

/**
 * Event für das Zerstören eines Jobblockes
 */

public class BreakJobBlockListener implements Listener {

    @EventHandler
    public void onJobBlockBreak(BlockBreakEvent event) {

        // Check, ob JobBlock abgebaut wird, um nicht immer den JSON Save zu laden.

        switch (event.getBlock().getType()) {
            case OAK_LOG, SPRUCE_LOG, BIRCH_LOG, JUNGLE_LOG, ACACIA_LOG, DARK_OAK_LOG, MANGROVE_LOG, DIRT, GRAVEL, SAND, CLAY:

                JobSave save = JobSave.findByOwnerUUID(event.getPlayer().getUniqueId().toString());
                ChunkPersistentData chunkPersistentData = new ChunkPersistentData(event.getBlock().getChunk());

                if (save == null) {
                    return;
                }

                if (save.getJob().equalsIgnoreCase(JobSave.WOODCHOPER)) {

                    switch (event.getBlock().getType()) {
                        case OAK_LOG, SPRUCE_LOG, BIRCH_LOG, JUNGLE_LOG, ACACIA_LOG, DARK_OAK_LOG, MANGROVE_LOG:
                            break;
                        default:
                            return;
                    }

                    if (chunkPersistentData.contains(event.getBlock().getLocation())) {
                        switch (event.getBlock().getType()) {
                            case OAK_LOG, SPRUCE_LOG, BIRCH_LOG, JUNGLE_LOG, ACACIA_LOG, DARK_OAK_LOG, MANGROVE_LOG:
                                event.getPlayer().sendMessage(JobSystem.pluginSignature + ChatColor.RED + "Block wurde bereits abgebaut!");
                                event.getPlayer().playSound(event.getPlayer(), Sound.BLOCK_ANVIL_PLACE, 1, 1);
                                break;
                        }
                    } else {
                        PersistentDataContainer playerDataContainer = event.getPlayer().getPersistentDataContainer();
                        if (!playerDataContainer.has(new NamespacedKey(JobSystem.getPlugin(), "logsminedcount"), PersistentDataType.INTEGER)) {
                            playerDataContainer.set(new NamespacedKey(JobSystem.getPlugin(), "logsminedcount"), PersistentDataType.INTEGER, 0);
                        }

                        int count = playerDataContainer.get(new NamespacedKey(JobSystem.getPlugin(), "logsminedcount"), PersistentDataType.INTEGER);

                        playerDataContainer.set(new NamespacedKey(JobSystem.getPlugin(), "logsminedcount"), PersistentDataType.INTEGER, count + 1);

                        count++;

                        if (count == 0) {
                            event.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GOLD + "Holzfäller: " + ChatColor.AQUA + 1 + "/8 abgebautem Holz"));
                        } else {
                            event.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GOLD + "Holzfäller: " + ChatColor.AQUA + count + "/8 abgebautem Holz"));
                        }


                        if (count == 8) {
                            playerDataContainer.set(new NamespacedKey(JobSystem.getPlugin(), "logsminedcount"), PersistentDataType.INTEGER, 0);
                            event.getPlayer().playSound(event.getPlayer(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                            event.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GOLD + "+15€"));
                        } else {
                            event.getPlayer().playSound(event.getPlayer(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.6F, 2);
                        }
                    }

                } else if (save.getJob().equalsIgnoreCase(JobSave.DIGGER)) {

                    switch (event.getBlock().getType()) {
                        case DIRT, GRAVEL, SAND, CLAY:
                            break;
                        default:
                            return;
                    }

                    if (chunkPersistentData.contains(event.getBlock().getLocation())) {
                        switch (event.getBlock().getType()) {
                            case DIRT, SAND, GRAVEL, CLAY:
                                event.getPlayer().sendMessage(JobSystem.pluginSignature + ChatColor.RED + "Block wurde bereits abgebaut!");
                                event.getPlayer().playSound(event.getPlayer(), Sound.BLOCK_ANVIL_PLACE, 1, 1);
                                break;
                        }
                    } else {

                        String persistentDataKey = "";

                        switch (event.getBlock().getType()) {
                            case DIRT -> persistentDataKey = "dirtminedcount";
                            case SAND -> persistentDataKey = "sandminedcount";
                            case GRAVEL -> persistentDataKey = "gravelminedcount";
                            case CLAY -> persistentDataKey = "clayminedcount";
                        }

                        PersistentDataContainer playerDataContainer = event.getPlayer().getPersistentDataContainer();
                        if (!playerDataContainer.has(new NamespacedKey(JobSystem.getPlugin(), persistentDataKey), PersistentDataType.INTEGER)) {
                            playerDataContainer.set(new NamespacedKey(JobSystem.getPlugin(), persistentDataKey), PersistentDataType.INTEGER, 0);
                        }

                        int count = playerDataContainer.get(new NamespacedKey(JobSystem.getPlugin(), persistentDataKey), PersistentDataType.INTEGER);

                        playerDataContainer.set(new NamespacedKey(JobSystem.getPlugin(), persistentDataKey), PersistentDataType.INTEGER, count + 1);

                        count++;

                        String materialText = "";
                        switch (event.getBlock().getType()) {
                            case DIRT -> materialText = "abgebauter Erde";
                            case SAND -> materialText = "abgebautem Sand";
                            case GRAVEL -> materialText = "abgebautem Kiesel";
                            case CLAY -> materialText = "abgebautem Ton";
                        }

                        if (count == 0) {
                            event.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GOLD + "Gräber: " + ChatColor.AQUA + 1 + "/8 " + materialText));
                        } else {
                            event.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GOLD + "Gräber: " + ChatColor.AQUA + count + "/8 " + materialText));
                        }

                        int materialGeld = 0;
                        switch (event.getBlock().getType()) {
                            case DIRT -> materialGeld = 5;
                            case SAND -> materialGeld = 8;
                            case GRAVEL -> materialGeld = 12;
                            case CLAY -> materialGeld = 20;
                        }

                        if (count == 8) {
                            playerDataContainer.set(new NamespacedKey(JobSystem.getPlugin(), persistentDataKey), PersistentDataType.INTEGER, 0);
                            event.getPlayer().playSound(event.getPlayer(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                            event.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GOLD + "+" + materialGeld + "€"));
                        } else {
                            event.getPlayer().playSound(event.getPlayer(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.6F, 2);
                        }
                    }

                }
                chunkPersistentData.removeLocation(event.getBlock().getLocation());
        }
    }

}
