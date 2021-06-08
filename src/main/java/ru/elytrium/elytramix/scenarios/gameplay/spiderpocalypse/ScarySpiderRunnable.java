package ru.elytrium.elytramix.scenarios.gameplay.spiderpocalypse;

import org.bukkit.Bukkit;
import org.bukkit.entity.Creature;
import org.bukkit.entity.EntityType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class ScarySpiderRunnable extends BukkitRunnable {
    Random random = new Random();

    @Override
    public void run() {
        Bukkit.getOnlinePlayers().forEach(
                player -> player.getNearbyEntities(5, 5, 5).stream().filter(entity -> entity.getType() == EntityType.SPIDER || entity.getType() == EntityType.CAVE_SPIDER).forEach(entity -> {
                    if (random.nextFloat() <= 0.05f) {
                        ((Creature) entity).setTarget(player);
                        entity.setVelocity(player.getEyeLocation().toVector().subtract(entity.getLocation().add(0, 10, 0).toVector()).normalize().multiply(4));
                    }
                })
        );
    }
}
