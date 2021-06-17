package net.elytrium.elytramix.scenarios.gameplay.spiderpocalypse;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class SpiderFallListener implements Listener {
    @EventHandler
    public void onFall(EntityDamageEvent event) {
        if ((event.getEntityType() == EntityType.SPIDER || event.getEntityType() == EntityType.CAVE_SPIDER) && event.getCause() == EntityDamageEvent.DamageCause.FALL)
            event.setCancelled(true);
    }
}
