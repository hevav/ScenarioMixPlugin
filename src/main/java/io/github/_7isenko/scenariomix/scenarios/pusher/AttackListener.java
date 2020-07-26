package io.github._7isenko.scenariomix.scenarios.pusher;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.util.Vector;

public class AttackListener implements Listener {
    @EventHandler(ignoreCancelled = true)
    public void onAttack(EntityDamageByEntityEvent event) {
        if (event.getFinalDamage() != 0)
            event.getEntity().setVelocity(event.getEntity().getVelocity().add(new Vector(0, 0.5, 0)));
    }
}
