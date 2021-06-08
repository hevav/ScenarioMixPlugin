package ru.elytrium.elytramix.scenarios.gameplay.lowestkiller;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;
import ru.elytrium.elytramix.Plugin;
import ru.elytrium.elytramix.scenarios.Scenario;

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
