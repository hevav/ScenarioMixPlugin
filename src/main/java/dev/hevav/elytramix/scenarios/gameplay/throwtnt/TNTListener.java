package dev.hevav.elytramix.scenarios.gameplay.throwtnt;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class TNTListener implements Listener {
    private final ThrowTNT scenario;

    public TNTListener(ThrowTNT scenario) {
        this.scenario = scenario;
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onThrowTNT(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (player.hasPermission("scenariomix.tools")
                && player.getInventory().getItemInMainHand().getType() == scenario.getItem()) {

            event.setCancelled(true);

            Location location = player.getLocation();
            TNTPrimed tnt = (TNTPrimed) location.getWorld().spawnEntity(location.clone().add(0, 0.3, 0), EntityType.PRIMED_TNT);
            tnt.setFuseTicks(scenario.getDelay() * 20);

            tnt.setVelocity(location.getDirection().multiply(scenario.getVelocity() * 0.3f));
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onTNTExplode(EntityExplodeEvent event) {
        if (event.getEntityType() == EntityType.PRIMED_TNT) {
            event.setCancelled(true);

            int radius = scenario.getKillRadius();

            Location location = event.getLocation();
            location.getWorld().getNearbyEntities(location, radius, radius, radius).forEach(entity -> {
                if (entity instanceof Player) {
                    entity.setVelocity(
                        entity.getLocation()
                            .subtract(event.getEntity().getLocation())
                            .toVector()
                            .multiply(scenario.getPlayerVelocity()));
                }
            });
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onTNTDamage(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof TNTPrimed) {
            event.setCancelled(true);
        }
    }
}
