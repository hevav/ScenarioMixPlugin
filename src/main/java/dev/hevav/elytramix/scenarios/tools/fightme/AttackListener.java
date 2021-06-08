package dev.hevav.elytramix.scenarios.tools.fightme;

import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class AttackListener implements Listener {
    public AttackListener() {
    }

    @EventHandler(
            ignoreCancelled = true
    )
    public void onAttack(EntityDamageByEntityEvent event) {
        if (event.getCause() == DamageCause.PROJECTILE && event.getEntity() instanceof Player && !event.getEntity().getScoreboardTags().contains("fight_me") && ((Projectile)event.getDamager()).getShooter() instanceof Player) {
            event.setCancelled(true);
        }

        if (event.getEntity() instanceof Player && (event.getCause() == DamageCause.ENTITY_ATTACK || event.getCause() == DamageCause.ENTITY_SWEEP_ATTACK) && !event.getEntity().getScoreboardTags().contains("fight_me") && event.getDamager() instanceof Player) {
            event.setCancelled(true);
        }

    }
}
