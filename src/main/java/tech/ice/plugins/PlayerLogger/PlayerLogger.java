package tech.ice.plugins.PlayerLogger;

import tech.ice.plugins.PlayerLogger.commands.Lookup;
import tech.ice.plugins.PlayerLogger.commands.TabCMD;
import tech.ice.plugins.PlayerLogger.events.JoinEvent;
import tech.ice.plugins.PlayerLogger.events.MoveEvent;
import tech.ice.plugins.PlayerLogger.events.GameModeEvent;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;

public class PlayerLogger extends JavaPlugin {

    public static PlayerLogger PlayerLogger;

    @Override
    public void onEnable() {
        PlayerLogger = this;
        Bukkit.getPluginManager().registerEvents(new JoinEvent(), this);
        Bukkit.getPluginManager().registerEvents(new MoveEvent(), this);
        Bukkit.getPluginManager().registerEvents(new GameModeEvent(), this);
        Bukkit.getPluginCommand("lookup").setExecutor(new Lookup());
        Bukkit.getPluginCommand("lookup").setTabCompleter(new TabCMD());
    }
}