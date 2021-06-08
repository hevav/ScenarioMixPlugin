package ru.elytrium.elytramix.scenarios.gameplay.throwtnt;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import ru.elytrium.elytramix.utils.PlayerUtils;

public class WaterListener implements Listener {
    @EventHandler(priority = EventPriority.NORMAL)
    public void onEvent(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        String material = event.getTo().getBlock().getType().toString();
        if (PlayerUtils.isValid(player) && (material.equals("STATIONARY_WATER") || material.equals("WATER"))) {
            player.setGameMode(GameMode.SPECTATOR);
            player.sendTitle(
                    ChatColor.GOLD + "Вы попали в воду(",
                    "И проиграли в этом режиме(",
                    10, 40, 10);

            Bukkit.getServer().broadcastMessage(ChatColor.GOLD + String.format("Игрок %s попал в воду(", player.getName()));
        }
    }
}
