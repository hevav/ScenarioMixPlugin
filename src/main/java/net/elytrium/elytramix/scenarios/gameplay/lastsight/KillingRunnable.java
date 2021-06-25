package net.elytrium.elytramix.scenarios.gameplay.lastsight;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.HashSet;
import java.util.Set;

public class KillingRunnable extends BukkitRunnable {
    public KillingRunnable() {
    }

    public void run() {
        Bukkit.getOnlinePlayers().stream().filter((player) -> {
            return player.getScoreboardTags().contains("last_sight");
        }).forEach((player) -> {
            this.getEntities(player).forEach((entity) -> {
                entity.damage(100.0D, player);
            });
        });
    }

    private Set<LivingEntity> getEntities(Player player) {
        Set<LivingEntity> entities = new HashSet();

        for (Entity entity : player.getNearbyEntities(50.0D, 100.0D, 50.0D)) {
            if (entity instanceof LivingEntity && isLookingAt(player, (LivingEntity) entity) && isCloser(player, (LivingEntity) entity)) {
                entities.add((LivingEntity) entity);
            }
        }

        return entities;
    }

    private boolean isLookingAt(Player player, LivingEntity livingEntity) {
        Location eyes = livingEntity.getEyeLocation();
        Vector toPlayer = player.getEyeLocation().toVector().subtract(eyes.toVector());
        double dot = toPlayer.normalize().dot(eyes.getDirection());
        Vector toPlayer2 = player.getLocation().toVector().subtract(eyes.toVector());
        dot = Math.max(toPlayer2.normalize().dot(eyes.getDirection()), dot);
        return dot > 0.99D;
    }

    private boolean isCloser(Player player, LivingEntity livingEntity) {
        double toBlock = livingEntity.getEyeLocation().distance(livingEntity.getTargetBlock(null, 30).getLocation());
        double toPlayer = Math.min(player.getLocation().distance(livingEntity.getEyeLocation()), player.getEyeLocation().distance(livingEntity.getEyeLocation()));
        return toPlayer <= toBlock;
    }
}