package tech.ice.plugins.PlayerLogger.commands;

import tech.ice.plugins.PlayerLogger.PlayerLogger;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.UUID;

public class Lookup implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.isOp()) {
            sender.sendMessage("§c你沒有權限使用這個指令");
        } else if (args.length == 0) {
            sender.sendMessage("§c用法：/lookup <玩家ID>");
        } else {
            String PlayerName = args[0];
            OfflinePlayer player = Bukkit.getServer().getOfflinePlayer(PlayerName);
            UUID uuid = player.getUniqueId();
            File file = new File(PlayerLogger.PlayerLogger.getDataFolder() + "/userdata/" + uuid + ".yml");
            FileConfiguration data = YamlConfiguration.loadConfiguration(file);
            if (!file.exists()) {
                sender.sendMessage("§c指定的玩家不存在或未加入過伺服器");
            } else {
                sender.sendMessage("§7 ====== §7" + " " + data.getString("NAME") + " " + "§7的資料======");
                sender.sendMessage("§7[§6玩家查詢§7]§7 - UUID：§7" + data.getString("UUID"));
                sender.sendMessage("§7[§6玩家查詢§7]§7 - 生命值：§7" + data.getString("health"));
                sender.sendMessage("§7[§6玩家查詢§7]§7 - 飢餓值：§7" + data.getString("hunger") + "（" + "+" + data.getString("food") + "飽食度" + "）");
                sender.sendMessage("§7[§6玩家查詢§7]§7 - 經驗值：§7" + data.getString("exp") + "（" + "等級" + data.getString("level") + "）");
                sender.sendMessage("§7[§6玩家查詢§7]§7 - 座標：§7（" + data.getString("world") + "," + " " + data.getString("x") + "," + " " + data.getString("y") + "," + " " + data.getString("z") + "）");
                sender.sendMessage("§7[§6玩家查詢§7]§7 - IP 位址：§7" + data.getString("IP"));
                sender.sendMessage("§7[§6玩家查詢§7]§7 - 遊戲模式：§7" + data.getString("gamemode"));
                return true;
                }
        }
        return true;
    }
}