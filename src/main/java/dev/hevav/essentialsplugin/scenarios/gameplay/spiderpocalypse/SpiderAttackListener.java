package dev.hevav.essentialsplugin.scenarios.gameplay.spiderpocalypse;

import dev.hevav.essentialsplugin.utils.ItemUtils;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.Random;

public class SpiderAttackListener implements Listener {
    Random random = new Random();

    @EventHandler(ignoreCancelled = true)
    public void onAttack(EntityDamageByEntityEvent event) {
        if (event.getDamager().getType() == EntityType.SPIDER || event.getDamager().getType() == EntityType.CAVE_SPIDER) {
            Entity victim = event.getEntity();
            Material type = victim.getLocation().getBlock().getType();
            if (random.nextFloat() <= 0.05f && (type == Material.AIR || type == Material.GRASS || type == ItemUtils.getMaterial("LONG_GRASS") || type == Material.DEAD_BUSH))
                victim.getLocation().getBlock().setType(ItemUtils.getMaterial("WEB"));
        }
    }
}
