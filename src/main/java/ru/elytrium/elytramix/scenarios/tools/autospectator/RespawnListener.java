package ru.elytrium.elytramix.scenarios.tools.autospectator;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.scheduler.BukkitRunnable;
import ru.elytrium.elytramix.Plugin;

class RespawnListener implements Listener {
    @EventHandler(ignoreCancelled = true)
    public void onRespawn(PlayerRespawnEvent event) {
        if (event.getPlayer().getGameMode() == GameMode.SURVIVAL || event.getPlayer().getGameMode() == GameMode.ADVENTURE) {
            // It is buggy
            new BukkitRunnable() {
                @Override
                public void run() {
                    event.getPlayer().setGameMode(GameMode.SPECTATOR);
                }
            }.runTask(Plugin.plugin);
        }
    }
}
