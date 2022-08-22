package tech.ice.plugins.PlayerLogger.events;

import tech.ice.plugins.PlayerLogger.manager.PlayerManger;
import tech.ice.plugins.PlayerLogger.data.SaveData;

import org.bukkit.entity.Player;
import org.bukkit.GameMode;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.EventHandler;

import java.net.InetSocketAddress;
import java.util.UUID;

public class JoinEvent implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        UUID uuid = event.getPlayer().getUniqueId();
        InetSocketAddress address = event.getPlayer().getAddress();
        Double health = event.getPlayer().getHealth();
        int hunger = event.getPlayer().getFoodLevel();
        float food = event.getPlayer().getSaturation();
        float exp = event.getPlayer().getExp();
        int level = event.getPlayer().getExpToLevel();
        String world = event.getPlayer().getWorld().getName();
        Double x = event.getPlayer().getLocation().getX();
        Double y = event.getPlayer().getLocation().getY();
        Double z = event.getPlayer().getLocation().getZ();
        GameMode gamemode = event.getPlayer().getGameMode();
        if(!PlayerManger.PlayerDataMap.containsKey(uuid)){
            SaveData SaveData = new SaveData(player);
            PlayerManger.PlayerDataMap.put(uuid, SaveData);
        }
        SaveData SaveData = PlayerManger.PlayerDataMap.get(uuid);
        SaveData.setData(player, uuid, address, health, hunger, food, exp, level, world, x, y, z, gamemode);
        SaveData.saveConfig();
    }
}
