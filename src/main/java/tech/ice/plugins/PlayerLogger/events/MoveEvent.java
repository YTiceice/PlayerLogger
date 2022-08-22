package tech.ice.plugins.PlayerLogger.events;

import tech.ice.plugins.PlayerLogger.manager.PlayerManger;
import tech.ice.plugins.PlayerLogger.data.SaveData;

import org.bukkit.event.Listener;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.EventHandler;

import java.util.UUID;

public class MoveEvent implements Listener {
    @EventHandler
    public void onPlayerMoving(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        UUID uuid = event.getPlayer().getUniqueId();
        Double health = event.getPlayer().getHealth();
        int hunger = event.getPlayer().getFoodLevel();
        float food = event.getPlayer().getSaturation();
        float exp = event.getPlayer().getExp();
        int level = event.getPlayer().getExpToLevel();
        String world = event.getPlayer().getWorld().getName();
        Double x = event.getPlayer().getLocation().getX();
        Double y = event.getPlayer().getLocation().getY();
        Double z = event.getPlayer().getLocation().getZ();
        if(!PlayerManger.PlayerDataMap.containsKey(uuid)){
            SaveData SaveData = new SaveData(player);
            PlayerManger.PlayerDataMap.put(uuid, SaveData);
        }
        SaveData SaveData = PlayerManger.PlayerDataMap.get(uuid);
        SaveData.UpdateData(health, hunger, food, exp, level, world, x, y, z);
        SaveData.saveConfig();
    }
}
