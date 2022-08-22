package tech.ice.plugins.PlayerLogger.events;

import tech.ice.plugins.PlayerLogger.manager.PlayerManger;
import tech.ice.plugins.PlayerLogger.data.SaveData;

import org.bukkit.event.Listener;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.EventHandler;

import java.util.UUID;

public class MoveEvent implements Listener {

    public static int getExpToLevelUp(int level){
        if (level <= 15){
            return 2 * level + 7;
        } else if (level <= 30) {
            return 5 * level - 38;
        } else {
            return 9 * level - 158;
        }
    }

    public static int getExpAtLevel(int level){
        if (level <= 16) {
            return (int) (Math.pow(level,2) + 6 * level);
        } else if (level <= 31) {
            return (int) (2.5 * Math.pow(level,2) - 40.5 * level + 360.0);
        } else {
            return (int) (4.5 * Math.pow(level,2) - 162.5 * level + 2220.0);
        }
    }

    public static int getPlayerExp(Player player){
        int exp = 0;
        int level = player.getLevel();

        exp += getExpAtLevel(level);

        exp += Math.round(getExpToLevelUp(level) * player.getExp());

        return exp;
    }

    @EventHandler
    public void onPlayerMoving(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        UUID uuid = event.getPlayer().getUniqueId();
        Double health = event.getPlayer().getHealth();
        int hunger = event.getPlayer().getFoodLevel();
        float food = event.getPlayer().getSaturation();
        int level = event.getPlayer().getLevel();
        int exp = getPlayerExp(player);
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
