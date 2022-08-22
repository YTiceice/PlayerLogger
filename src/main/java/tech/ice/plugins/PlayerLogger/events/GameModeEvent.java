package tech.ice.plugins.PlayerLogger.events;

import tech.ice.plugins.PlayerLogger.manager.PlayerManger;
import tech.ice.plugins.PlayerLogger.data.SaveData;

import org.bukkit.GameMode;
import org.bukkit.event.Listener;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.EventHandler;

import java.util.UUID;

public class GameModeEvent implements Listener {
    @EventHandler
    public void onPlayerGameModeChange(PlayerGameModeChangeEvent event) {
        Player player = event.getPlayer();
        UUID uuid = event.getPlayer().getUniqueId();
        GameMode gamemode = event.getNewGameMode();
        if(!PlayerManger.PlayerDataMap.containsKey(uuid)){
            SaveData SaveData = new SaveData(player);
            PlayerManger.PlayerDataMap.put(uuid, SaveData);
        }
        SaveData SaveData = PlayerManger.PlayerDataMap.get(uuid);
        SaveData.UpdateGM(gamemode);
        SaveData.saveConfig();
    }
}
