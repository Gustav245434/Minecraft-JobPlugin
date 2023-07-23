package com.moody.jobsystem;

import com.moody.jobsystem.commands.JobNPCCommand;
import com.moody.jobsystem.listener.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class JobSystem extends JavaPlugin {


    static JobSystem plugin;
    public static String pluginSignature = ChatColor.GRAY + "[" + ChatColor.AQUA + "JobSystem" + ChatColor.GRAY + "] ";
    public static final String keineRechteMessage = pluginSignature + ChatColor.RED + "Du hast dafür keine Rechte!";

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;

        getCommand("createjobnpc").setExecutor(new JobNPCCommand());

        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new JobVillagerClickListener(), this);
        pluginManager.registerEvents(new JobInvInteractListener(), this);
        pluginManager.registerEvents(new BreakJobBlockListener(), this);
        pluginManager.registerEvents(new JobBlockPlaceListener(), this);
        pluginManager.registerEvents(new JobBlockPistonMoveListener(), this);
        pluginManager.registerEvents(new JobBlockExplodeListener(), this);
        pluginManager.registerEvents(new JobBlockPlacedByEndermanListener(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public static JobSystem getPlugin() {
        return plugin;
    }
}
