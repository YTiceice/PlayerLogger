package tech.ice.plugins.PlayerLogger;

import tech.ice.plugins.PlayerLogger.commands.Lookup;
import tech.ice.plugins.PlayerLogger.commands.TabCMD;
import tech.ice.plugins.PlayerLogger.config.LoadConfig;
import tech.ice.plugins.PlayerLogger.events.JoinEvent;
import tech.ice.plugins.PlayerLogger.events.MoveEvent;
import tech.ice.plugins.PlayerLogger.events.GameModeEvent;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;

import java.io.IOException;

import org.bstats.bukkit.Metrics;

public class PlayerLogger extends JavaPlugin {

    public static PlayerLogger PlayerLogger;

    @Override
    public void onEnable() {
        int ID = 16236;
        Metrics metrics = new Metrics(this, ID);

        PlayerLogger = this;
        Bukkit.getPluginManager().registerEvents(new JoinEvent(), this);
        Bukkit.getPluginManager().registerEvents(new MoveEvent(), this);
        Bukkit.getPluginManager().registerEvents(new GameModeEvent(), this);
        Bukkit.getPluginCommand("lookup").setExecutor(new Lookup());
        Bukkit.getPluginCommand("lookup").setTabCompleter(new TabCMD());

        try {
            LoadConfig.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}