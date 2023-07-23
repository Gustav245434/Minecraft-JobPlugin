package com.moody.jobsystem.commands;

import com.moody.jobsystem.JobSave;
import com.moody.jobsystem.JobSystem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import org.bukkit.inventory.EquipmentSlot;

import java.util.List;

public class JobNPCCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {

            if (sender.isOp()) {

                Entity npc = ((Player) sender).getWorld().spawnEntity(((Player) sender).getLocation(), EntityType.VILLAGER);
                LivingEntity npcliving = (LivingEntity) npc;
                npcliving.setAI(false);
                npcliving.setGravity(false);
                npcliving.setInvulnerable(true);
                npcliving.setSilent(true);
                npcliving.setCollidable(false);
                npcliving.setCanPickupItems(false);
                npcliving.setRemoveWhenFarAway(false);
                npcliving.setCustomName(ChatColor.GOLD + "Jobs");
                npcliving.setCustomNameVisible(true);

                ((Player) sender).sendMessage(JobSystem.pluginSignature + ChatColor.GREEN + "Erfolgreich Job Villager erstellt!");
                ((Player) sender).playSound(((Player) sender), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1 ,2);

            } else {

                sender.sendMessage(JobSystem.keineRechteMessage);
                ((Player) sender).playSound(((Player) sender), Sound.ENTITY_VILLAGER_NO, 1, 1);

            }


        } else {
            sender.sendMessage(JobSystem.pluginSignature + ChatColor.RED + "Du musst ein Spieler sein um diesen Command auszuführen.");
        }

        return false;
    }
}
