package dev.hevav.elytramix.scenarios.gameplay.lowestkiller;

import dev.hevav.elytramix.Plugin;
import dev.hevav.elytramix.scenarios.Scenario;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

public class LowestKiller extends Scenario {

    BukkitTask task;

    public LowestKiller() {
        super("Низшему не жить", "lowest_killer", "BRICK_STAIRS", "Каждую минуту самый", "нижний игрок умирает");
    }

    @Override
    public void start() {
       task = Bukkit.getScheduler().runTaskTimer(Plugin.plugin, new KillerRunnable(), 20, 1200);
    }

    @Override
    public void stop() {
        task.cancel();
        task = null;
    }
}
