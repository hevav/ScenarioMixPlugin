package net.elytrium.elytramix.scenarios.gameplay.pusher;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Random;

public class PusherRunnable extends BukkitRunnable {
    @Override
    public void run() {
        Random r = new Random();
        Bukkit.getOnlinePlayers().forEach(
                p -> {
                    if (p.getGameMode() == GameMode.SURVIVAL || p.getGameMode() == GameMode.ADVENTURE) {
                        Vector vector = new Vector(r.nextDouble()-0.5, 0, r.nextDouble()-0.5);
                        p.setVelocity(p.getVelocity().add(vector));
                    }
                });
    }
}


