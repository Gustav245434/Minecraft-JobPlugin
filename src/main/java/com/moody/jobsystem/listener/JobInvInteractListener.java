package com.moody.jobsystem.listener;

import com.moody.jobsystem.JobSave;
import com.moody.jobsystem.JobSystem;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.util.Vector;

public class JobInvInteractListener implements Listener {

    @EventHandler
    public void onInvInteraction(InventoryClickEvent event) {

        if (event.getCurrentItem() == null) {
            return;
        }

        if (event.getCurrentItem().getType() != Material.AIR) {
            if (event.getInventory().getItem(4) != null) {
                if (event.getInventory().getItem(4).hasItemMeta()) {
                    if (event.getInventory().getItem(4).getItemMeta().hasLocalizedName()) {
                        if (event.getView().getTitle().equals("Jobcenter") && event.getInventory().getItem(4).getItemMeta().getLocalizedName().equalsIgnoreCase("jobcenter")) {
                            event.setCancelled(true);

                            Player whoClicked = (Player) event.getWhoClicked();
                            JobSave jobSave = JobSave.findByOwnerUUID(event.getWhoClicked().getUniqueId().toString());

                            switch (event.getCurrentItem().getType()) {
                                case DIAMOND_AXE:
                                    if (jobSave == null) {
                                        jobSave = new JobSave((Player) event.getWhoClicked(), JobSave.WOODCHOPER);
                                        JobSave.hinzufuegenJob(jobSave);
                                    } else {
                                        if (jobSave.getJob().equalsIgnoreCase(JobSave.WOODCHOPER)) {
                                            Bukkit.getScheduler().runTask(JobSystem.getPlugin(), whoClicked::closeInventory);
                                            whoClicked.playSound(whoClicked, Sound.ENTITY_VILLAGER_NO, 1, 1);
                                            whoClicked.sendMessage(JobSystem.pluginSignature + ChatColor.RED + "Du hast diesen Job bereits!");
                                            break;
                                        } else {
                                            jobSave.setJob(JobSave.WOODCHOPER);
                                        }
                                    }
                                    Bukkit.getScheduler().runTask(JobSystem.getPlugin(), whoClicked::closeInventory);
                                    whoClicked.playSound(whoClicked, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 2);
                                    whoClicked.sendMessage(JobSystem.pluginSignature + "Job geändert: " + ChatColor.GOLD + "Holzfäller");
                                    break;
                                case DIAMOND_SHOVEL:
                                    if (jobSave == null) {
                                        jobSave = new JobSave((Player) event.getWhoClicked(), JobSave.DIGGER);
                                        JobSave.hinzufuegenJob(jobSave);
                                    } else {
                                        if (jobSave.getJob().equalsIgnoreCase(JobSave.DIGGER)) {
                                            Bukkit.getScheduler().runTask(JobSystem.getPlugin(), whoClicked::closeInventory);
                                            whoClicked.playSound(whoClicked, Sound.ENTITY_VILLAGER_NO, 1, 1);
                                            whoClicked.sendMessage(JobSystem.pluginSignature + ChatColor.RED + "Du hast diesen Job bereits!");
                                            break;
                                        } else {
                                            jobSave.setJob(JobSave.DIGGER);
                                        }
                                    }
                                    Bukkit.getScheduler().runTask(JobSystem.getPlugin(), whoClicked::closeInventory);
                                    whoClicked.playSound(whoClicked, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 2);
                                    whoClicked.sendMessage(JobSystem.pluginSignature + "Job geändert: " + ChatColor.GOLD + "Gräber");
                                    break;
                                case DIAMOND_PICKAXE:
                                    if (jobSave == null) {
                                        jobSave = new JobSave((Player) event.getWhoClicked(), JobSave.MINER);
                                        JobSave.hinzufuegenJob(jobSave);
                                    } else {
                                        if (jobSave.getJob().equalsIgnoreCase(JobSave.MINER)) {
                                            Bukkit.getScheduler().runTask(JobSystem.getPlugin(), whoClicked::closeInventory);
                                            whoClicked.playSound(whoClicked, Sound.ENTITY_VILLAGER_NO, 1, 1);
                                            whoClicked.sendMessage(JobSystem.pluginSignature + ChatColor.RED + "Du hast diesen Job bereits!");
                                            break;
                                        } else {
                                            jobSave.setJob(JobSave.MINER);
                                        }
                                    }
                                    Bukkit.getScheduler().runTask(JobSystem.getPlugin(), whoClicked::closeInventory);
                                    whoClicked.playSound(whoClicked, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 2);
                                    whoClicked.sendMessage(JobSystem.pluginSignature + "Job geändert: " + ChatColor.GOLD + "Minenarbeiter");
                                    break;
                                case DIAMOND_HOE:
                                    if (jobSave == null) {
                                        jobSave = new JobSave((Player) event.getWhoClicked(), JobSave.FARMER);
                                        JobSave.hinzufuegenJob(jobSave);
                                    } else {
                                        if (jobSave.getJob().equalsIgnoreCase(JobSave.FARMER)) {
                                            Bukkit.getScheduler().runTask(JobSystem.getPlugin(), whoClicked::closeInventory);
                                            whoClicked.playSound(whoClicked, Sound.ENTITY_VILLAGER_NO, 1, 1);
                                            whoClicked.sendMessage(JobSystem.pluginSignature + ChatColor.RED + "Du hast diesen Job bereits!");
                                            break;
                                        } else {
                                            jobSave.setJob(JobSave.FARMER);
                                        }
                                    }
                                    Bukkit.getScheduler().runTask(JobSystem.getPlugin(), whoClicked::closeInventory);
                                    whoClicked.playSound(whoClicked, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 2);
                                    whoClicked.sendMessage(JobSystem.pluginSignature + "Job geändert: " + ChatColor.GOLD + "Farmer");
                                    break;
                                default:

                                    break;
                            }
                        }
                    }
                }
            }
        }

    }

}
