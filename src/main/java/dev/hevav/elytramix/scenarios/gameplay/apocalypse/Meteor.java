package dev.hevav.elytramix.scenarios.gameplay.apocalypse;

import dev.hevav.elytramix.Plugin;
import org.bukkit.Particle;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Fireball;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Random;

public class Meteor {
    private final BukkitRunnable runnable;
    private final Random random;

    public Meteor(final FallingBlock fallingBlock) {
        this.random = new Random();
        this.runnable = new BukkitRunnable() {
            @Override
            public void run() {
                if (fallingBlock == null || !fallingBlock.isValid()) {
                    this.cancel();
                    return;
                }
                fallingBlock.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, fallingBlock.getLocation(), 2);
                if (random.nextFloat() <= 0.02) {
                    fallingBlock.getWorld().spawnParticle(Particle.SMOKE_LARGE, fallingBlock.getLocation(), 100);
                    Fireball fireball = fallingBlock.getWorld().spawn(fallingBlock.getLocation().add(0,-8, 0), Fireball.class);
                    fireball.setDirection(new Vector(random.nextInt(3) - 1, -8, random.nextInt(3) - 1));
                    fireball.setYield(random.nextFloat()*8 - 3);
                }
            }
        };
        runnable.runTaskTimer(Plugin.plugin, 0, 1);
    }
}
