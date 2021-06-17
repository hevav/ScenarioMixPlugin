package net.elytrium.elytramix.scenarios.gameplay.collideath;

import net.elytrium.elytramix.utils.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collection;
import java.util.HashSet;

public class CollideRunnable extends BukkitRunnable {
    @Override
    public void run() {
        Bukkit.getOnlinePlayers().stream().filter(PlayerUtils::isValid).forEach(player -> {
            Location location = player.getLocation();
            HashSet<Player> near = new HashSet<>();
            if (player.isSneaking()) {
                location.add(0, 0.75, 0);
                putPlayers(near, player.getWorld().getNearbyEntities(location, 0.6, 1.5, 0.6));
            } else {
                location.add(0, 0.9, 0);
                putPlayers(near, player.getWorld().getNearbyEntities(location, 0.6, 1.8, 0.6));
            }
            near.remove(player);
            if (!near.isEmpty()) {
                player.setHealth(0);
                near.forEach(player1 -> player1.setHealth(0));
            }
        });
    }

    private void putPlayers(Collection<Player> players, Collection<Entity> entities) {
        entities.stream().filter(entity -> entity instanceof Player).filter(entity -> PlayerUtils.isValid(((Player) entity))).forEach(entity -> {
            players.add((Player) entity);
        });
    }
}
