package net.elytrium.elytramix.scenarios.gameplay.lowestkiller;

import net.elytrium.elytramix.Plugin;
import net.elytrium.elytramix.scenarios.Scenario;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

public class LowestKiller extends Scenario {

    BukkitTask task;

    public LowestKiller() {
        super("Низшему не жить", "lowest_killer", "BRICK_STAIRS", "scenario","Каждую минуту самый", "нижний игрок умирает");
    }

    @Override
    public void start(Player player) {
       task = Bukkit.getScheduler().runTaskTimer(Plugin.getInstance(), new KillerRunnable(), 20, 1200);
    }

    @Override
    public void stop() {
        task.cancel();
        task = null;
    }
}
