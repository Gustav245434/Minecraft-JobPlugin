package com.moody.jobsystem.listener;

import com.moody.jobsystem.ChunkPersistentData;
import com.moody.jobsystem.JobSave;
import com.moody.jobsystem.JobSystem;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import static org.bukkit.Bukkit.spigot;

public class BreakJobBlockListener implements Listener {

    @EventHandler
    public void onJobBlockBreak(BlockBreakEvent event) {
        switch (event.getBlock().getType()) {
            case OAK_LOG, SPRUCE_LOG, BIRCH_LOG, JUNGLE_LOG, ACACIA_LOG, DARK_OAK_LOG, MANGROVE_LOG:

                JobSave save = JobSave.findByOwnerUUID(event.getPlayer().getUniqueId().toString());
                ChunkPersistentData chunkPersistentData = new ChunkPersistentData(event.getBlock().getChunk());

                if (save.getJob().equalsIgnoreCase(JobSave.WOODCHOPER)) {
                    if (chunkPersistentData.contains(event.getBlock().getLocation())) {
                        event.getPlayer().sendMessage(JobSystem.pluginSignature + ChatColor.RED + "Block wurde bereits abgebaut!");
                        event.getPlayer().playSound(event.getPlayer(), Sound.BLOCK_ANVIL_PLACE, 1, 1);
                    } else {
                        PersistentDataContainer playerDataContainer = event.getPlayer().getPersistentDataContainer();
                        if (!playerDataContainer.has(new NamespacedKey(JobSystem.getPlugin(), "blocksminedcount"), PersistentDataType.INTEGER)) {
                            playerDataContainer.set(new NamespacedKey(JobSystem.getPlugin(), "blocksminedcount"), PersistentDataType.INTEGER, 0);
                        }

                        int count = playerDataContainer.get(new NamespacedKey(JobSystem.getPlugin(), "blocksminedcount"), PersistentDataType.INTEGER);

                        playerDataContainer.set(new NamespacedKey(JobSystem.getPlugin(), "blocksminedcount"), PersistentDataType.INTEGER, count + 1);

                        count++;

                        if (count == 0) {
                            event.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GOLD + "Holzfäller: " + ChatColor.AQUA + 1 + "/8 abgebautem Holz"));
                        } else {
                            event.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GOLD + "Holzfäller: " + ChatColor.AQUA + count + "/8 abgebautem Holz"));
                        }


                        if (count == 8) {
                            playerDataContainer.set(new NamespacedKey(JobSystem.getPlugin(), "blocksminedcount"), PersistentDataType.INTEGER, 0);
                            event.getPlayer().playSound(event.getPlayer(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                            event.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GOLD + "+15€"));
                        } else {
                            event.getPlayer().playSound(event.getPlayer(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1.3F);
                        }
                    }
                }
                chunkPersistentData.removeLocation(event.getBlock().getLocation());
        }
    }

}
