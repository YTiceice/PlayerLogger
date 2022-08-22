package tech.ice.plugins.PlayerLogger.data;

import tech.ice.plugins.PlayerLogger.PlayerLogger;

import org.bukkit.entity.Player;
import org.bukkit.GameMode;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.UUID;

public class SaveData {

    FileConfiguration config;
    Player player;
    UUID uuid;

    public SaveData(Player player) {
        this.player = player;
        uuid = player.getUniqueId();

        setConfig();
    }

    public void setConfig() {
        File file = new File(PlayerLogger.PlayerLogger.getDataFolder()+"/userdata");
        if (!file.exists()) {
            file.mkdir();
        }

        File data = new File(PlayerLogger.PlayerLogger.getDataFolder()+"/userdata/"+uuid+".yml");
        if (!data.exists()) {
            try {
                data.createNewFile();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        config = YamlConfiguration.loadConfiguration(file);
    }

    public void saveConfig() {
        File data = new File(PlayerLogger.PlayerLogger.getDataFolder()+"/userdata/"+uuid+".yml");
        try {
            config.save(data);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void setData (Player player, UUID uuid, InetSocketAddress address, Double health, int hunger, float food, float exp, int level, String world, Double x, Double y, Double z, GameMode gamemode) {
        config.set ("NAME", player.getName());
        config.set ("UUID", uuid.toString());
        config.set ("health", health.toString());
        config.set ("hunger", hunger);
        config.set ("food", food);
        config.set ("exp", exp);
        config.set ("level", level);
        config.set ("world", world);
        config.set ("IP", address.getHostString());
        config.set ("x", x.intValue());
        config.set ("y", y.intValue());
        config.set ("z", z.intValue());
        if (gamemode.toString() == "ADVENTURE") {
            config.set ("gamemode", "冒險模式");
        } else if (gamemode.toString() == "CREATIVE") {
            config.set ("gamemode", "創造模式");
        } else if (gamemode.toString() == "SPECTATOR") {
            config.set ("gamemode", "旁觀者模式");
        } else if (gamemode.toString() == "SURVIVAL") {
            config.set ("gamemode", "生存模式");
        } else {
            config.set ("gamemode", "未知");
        }
    }

    public void UpdateData (Double health, int hunger, float food, float exp, int level, String world, Double x, Double y, Double z) {
        config.set ("health", health.toString());
        config.set ("hunger", hunger);
        config.set ("food", food);
        config.set ("exp", exp);
        config.set ("level", level);
        config.set ("world", world);
        config.set ("x", x.intValue());
        config.set ("y", y.intValue());
        config.set ("z", z.intValue());
    }

    public void UpdateGM (GameMode gamemode) {
        if (gamemode.toString() == "ADVENTURE") {
            config.set ("gamemode", "冒險模式");
        } else if (gamemode.toString() == "CREATIVE") {
            config.set ("gamemode", "創造模式");
        } else if (gamemode.toString() == "SPECTATOR") {
            config.set ("gamemode", "旁觀者模式");
        } else if (gamemode.toString() == "SURVIVAL") {
            config.set ("gamemode", "生存模式");
        } else {
            config.set ("gamemode", "未知");
        }
    }
}
