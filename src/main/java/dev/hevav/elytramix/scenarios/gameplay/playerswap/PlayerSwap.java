package dev.hevav.elytramix.scenarios.gameplay.playerswap;

import dev.hevav.elytramix.Plugin;
import dev.hevav.elytramix.scenarios.Scenario;
import dev.hevav.elytramix.scenarios.config.Configuration;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerSwap extends Scenario {
    public PlayerSwap() {
        super("Свап игроков", "player_swap", "LEATHER_BOOTS", "Меняет местоположение игроков", "местами по кд");
        addConfig(interval);
    }

    private final Configuration<Integer> interval = new Configuration<>("interval", 300, "WATCH", this, "Интервал телепортации в секундах");

    private BukkitRunnable runnable = new SwapRunnable();

    public void start() {
        runnable.runTaskTimer(Plugin.plugin, 0L, interval.getValue() * 20L);
    }

    public void stop() {
        runnable.cancel();
        runnable = new SwapRunnable();
    }
}
