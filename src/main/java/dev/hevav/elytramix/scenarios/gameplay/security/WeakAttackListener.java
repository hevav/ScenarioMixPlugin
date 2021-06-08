package dev.hevav.elytramix.scenarios.gameplay.security;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class WeakAttackListener implements Listener {
    @EventHandler
    public void onAttack(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();
            if (player.getScoreboardTags().contains("weak"))
                event.setCancelled(true);
        }
    }
}
