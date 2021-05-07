package dev.hevav.essentialsplugin.utils;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class PlayerUtils {
    /**
     * Gaming meaning of validity
     * @param player - a player
     * @return true if he's in survival or adventure
     */
    public static boolean isValid(Player player) {
        return player.isValid() && !player.isDead() && (player.getGameMode() == GameMode.SURVIVAL || player.getGameMode() == GameMode.ADVENTURE);
    }
}
