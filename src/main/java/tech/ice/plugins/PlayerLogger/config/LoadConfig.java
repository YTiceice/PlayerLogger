package tech.ice.plugins.PlayerLogger.config;

import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.YamlConfiguration;
import tech.ice.plugins.PlayerLogger.PlayerLogger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class LoadConfig {

    public static boolean Enable;

    public static void load() throws IOException {

        if (!PlayerLogger.PlayerLogger.getDataFolder().exists()) {
            PlayerLogger.PlayerLogger.getDataFolder().mkdir();
        }

        File file = new File (PlayerLogger.PlayerLogger.getDataFolder(), "config.yml");

        if(!file.exists()) {
            FileOutputStream outputStream = new FileOutputStream(file);
            InputStream in = PlayerLogger.PlayerLogger.getResource("config.yml");
            in.transferTo(outputStream);
        }

        Configuration config = YamlConfiguration.loadConfiguration(file);

        Enable = config.getBoolean("show-offline");
    }
}
