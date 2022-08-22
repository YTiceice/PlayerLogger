package tech.ice.plugins.PlayerLogger.commands;

import static tech.ice.plugins.PlayerLogger.config.LoadConfig.*;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.Arrays;
import java.util.List;

public class TabCMD implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
        if (args.length == 1 & Enable) {
            return Arrays.stream(Bukkit.getOfflinePlayers()).map(OfflinePlayer::getName).toList();
        }
        return null;
    }
}