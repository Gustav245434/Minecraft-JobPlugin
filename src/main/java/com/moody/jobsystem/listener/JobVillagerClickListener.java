package com.moody.jobsystem.listener;

import com.moody.jobsystem.ItemBuilder;
import com.moody.jobsystem.JobSave;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;
import java.util.List;

public class JobVillagerClickListener implements Listener {

    @EventHandler
    public void onPlayerInteractJobVillager(PlayerInteractEntityEvent event) {

        if (event.getRightClicked().getCustomName() != null) {
            if (event.getRightClicked().getType().equals(EntityType.VILLAGER) && event.getRightClicked().getCustomName().equalsIgnoreCase(ChatColor.GOLD + "Jobs") && event.getRightClicked().isInvulnerable()) {
                Inventory jobinv = Bukkit.createInventory(event.getPlayer(), 9*5, "Jobcenter");
                jobinv.setItem(0, new ItemBuilder(Material.WHITE_STAINED_GLASS_PANE).setDisplayName(" ").build());
                jobinv.setItem(1, new ItemBuilder(Material.WHITE_STAINED_GLASS_PANE).setDisplayName(" ").build());
                jobinv.setItem(2, new ItemBuilder(Material.WHITE_STAINED_GLASS_PANE).setDisplayName(" ").build());
                jobinv.setItem(3, new ItemBuilder(Material.WHITE_STAINED_GLASS_PANE).setDisplayName(" ").build());
                jobinv.setItem(4, new ItemBuilder(Material.WOODEN_AXE).setDisplayName(ChatColor.GOLD + "Jobcenter").setLocalizedName("jobcenter").build());
                jobinv.setItem(5, new ItemBuilder(Material.WHITE_STAINED_GLASS_PANE).setDisplayName(" ").build());
                jobinv.setItem(6, new ItemBuilder(Material.WHITE_STAINED_GLASS_PANE).setDisplayName(" ").build());
                jobinv.setItem(7, new ItemBuilder(Material.WHITE_STAINED_GLASS_PANE).setDisplayName(" ").build());
                jobinv.setItem(8, new ItemBuilder(Material.WHITE_STAINED_GLASS_PANE).setDisplayName(" ").build());

                jobinv.setItem(36, new ItemBuilder(Material.WHITE_STAINED_GLASS_PANE).setDisplayName(" ").build());
                jobinv.setItem(37, new ItemBuilder(Material.WHITE_STAINED_GLASS_PANE).setDisplayName(" ").build());
                jobinv.setItem(38, new ItemBuilder(Material.WHITE_STAINED_GLASS_PANE).setDisplayName(" ").build());
                jobinv.setItem(39, new ItemBuilder(Material.WHITE_STAINED_GLASS_PANE).setDisplayName(" ").build());
                jobinv.setItem(40, new ItemBuilder(Material.WHITE_STAINED_GLASS_PANE).setDisplayName(" ").build());
                jobinv.setItem(41, new ItemBuilder(Material.WHITE_STAINED_GLASS_PANE).setDisplayName(" ").build());
                jobinv.setItem(42, new ItemBuilder(Material.WHITE_STAINED_GLASS_PANE).setDisplayName(" ").build());
                jobinv.setItem(43, new ItemBuilder(Material.WHITE_STAINED_GLASS_PANE).setDisplayName(" ").build());
                jobinv.setItem(44, new ItemBuilder(Material.WHITE_STAINED_GLASS_PANE).setDisplayName(" ").build());

                ItemStack diamondaxe = new ItemBuilder(Material.DIAMOND_AXE).setDisplayName(ChatColor.GOLD + "Holzfäller").setLore(ChatColor.GRAY + "Werde Holzfäller und verdiene pro", ChatColor.GRAY + "8 abgebauten" + ChatColor.AQUA + " Holzstämmen" + ChatColor.GREEN + " 15€").build();
                ItemStack diamondshovel = new ItemBuilder(Material.DIAMOND_SHOVEL).setDisplayName(ChatColor.GOLD + "Gräber").setLore(ChatColor.GRAY + "Werde Gräber und verdiene pro", ChatColor.GRAY + "8 abgebauter" + ChatColor.AQUA + " Erde" + ChatColor.GREEN + " 5€", ChatColor.GRAY + "8 abgebauter" + ChatColor.AQUA + " Sand" + ChatColor.GREEN + " 8€", ChatColor.GRAY + "8 abgebauter" + ChatColor.AQUA + " Kiesel" + ChatColor.GREEN + " 12€", ChatColor.GRAY + "8 abgebauter" + ChatColor.AQUA + " Ton" + ChatColor.GREEN + " 20€").build();
                ItemStack diamondpickaxe = new ItemBuilder(Material.DIAMOND_PICKAXE).setDisplayName(ChatColor.GOLD + "Minenarbeiter").setLore(ChatColor.GRAY + "Werde Minenarbeiter und verdiene pro", ChatColor.GRAY + "abgebauten" + ChatColor.AQUA + " Diamanterz" + ChatColor.GREEN + " 100€", ChatColor.GRAY + "abgebauten" + ChatColor.AQUA + " Golderz" + ChatColor.GREEN + " 70€", ChatColor.GRAY + "abgebauten" + ChatColor.AQUA + " Emeralderz" + ChatColor.GREEN + " 150€", ChatColor.GRAY + "abgebauten" + ChatColor.AQUA + " Eisenerz" + ChatColor.GREEN + " 30€", ChatColor.GRAY + "abgebauter" + ChatColor.AQUA + " Kohle" + ChatColor.GREEN + " 10€", ChatColor.GRAY + "abgebauten" + ChatColor.AQUA + " Lapislazuli" + ChatColor.GREEN + " 60€", ChatColor.GRAY + "abgebauten" + ChatColor.AQUA + " Redstone" + ChatColor.GREEN + " 40€", ChatColor.GRAY + "abgebauten" + ChatColor.AQUA + " Kupfer" + ChatColor.GREEN + " 5€").build();
                ItemStack diamondhoe = new ItemBuilder(Material.DIAMOND_HOE).setDisplayName(ChatColor.GOLD + "Farmer").setLore(ChatColor.GRAY + "Werde Farmer und verdiene pro", ChatColor.GRAY + "vollständig gewachsenen" + ChatColor.AQUA + " Weizen" + ChatColor.GRAY + "," + ChatColor.AQUA + " Karotten" + ChatColor.GRAY + ", etc." + ChatColor.GREEN + " 10€").build();

                JobSave save = JobSave.findByOwnerUUID(event.getPlayer().getUniqueId().toString());

                if (save != null) {
                    switch (save.getJob()) {
                        case "woodchoper" -> {
                            diamondaxe.addEnchantment(Enchantment.DURABILITY, 1);
                            ItemMeta itemMeta = diamondaxe.getItemMeta();
                            if (itemMeta != null) {
                                itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                                List<String> itemLore = itemMeta.getLore();
                                itemLore.add("");
                                itemLore.add(ChatColor.GREEN + "Aktueller Job!");
                                itemMeta.setLore(itemLore);
                            }
                            diamondaxe.setItemMeta(itemMeta);
                        }
                        case "digger" -> {
                            diamondshovel.addEnchantment(Enchantment.DURABILITY, 1);
                            ItemMeta itemMeta = diamondshovel.getItemMeta();
                            if (itemMeta != null) {
                                itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                                List<String> itemLore = itemMeta.getLore();
                                itemLore.add("");
                                itemLore.add(ChatColor.GREEN + "Aktueller Job!");
                                itemMeta.setLore(itemLore);
                            }
                            diamondshovel.setItemMeta(itemMeta);
                        }
                        case "miner" -> {
                            diamondpickaxe.addEnchantment(Enchantment.DURABILITY, 1);
                            ItemMeta itemMeta = diamondpickaxe.getItemMeta();
                            if (itemMeta != null) {
                                itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                                List<String> itemLore = itemMeta.getLore();
                                itemLore.add("");
                                itemLore.add(ChatColor.GREEN + "Aktueller Job!");
                                itemMeta.setLore(itemLore);
                            }
                            diamondpickaxe.setItemMeta(itemMeta);
                        }
                        case "farmer" -> {
                            diamondhoe.addEnchantment(Enchantment.DURABILITY, 1);
                            ItemMeta itemMeta = diamondhoe.getItemMeta();
                            if (itemMeta != null) {
                                itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                                List<String> itemLore = itemMeta.getLore();
                                itemLore.add("");
                                itemLore.add(ChatColor.GREEN + "Aktueller Job!");
                                itemMeta.setLore(itemLore);
                            }
                            diamondhoe.setItemMeta(itemMeta);
                        }
                    }

                }


                jobinv.setItem(19, diamondaxe);
                jobinv.setItem(21, diamondshovel);
                jobinv.setItem(23, diamondpickaxe);
                jobinv.setItem(25, diamondhoe);

                event.getPlayer().openInventory(jobinv);
            }
        }

    }

}
