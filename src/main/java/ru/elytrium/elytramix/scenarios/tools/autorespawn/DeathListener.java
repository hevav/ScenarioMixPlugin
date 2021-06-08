package ru.elytrium.elytramix.scenarios.tools.autorespawn;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;
import ru.elytrium.elytramix.Plugin;

class DeathListener implements Listener {
    @EventHandler(ignoreCancelled = true)
    public void onDeath(PlayerDeathEvent event) {
        new BukkitRunnable() {
            @Override
            public void run() {
                event.getEntity().spigot().respawn();
            }
        }.runTaskLater(Plugin.plugin, 1);
    }
}
