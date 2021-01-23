package io.github._7isenko.scenariomix.scenarios.gameplay.playerswap;

import io.github._7isenko.scenariomix.ScenarioMix;
import io.github._7isenko.scenariomix.scenarios.Scenario;
import io.github._7isenko.scenariomix.scenarios.config.Configuration;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerSwap extends Scenario {
    public PlayerSwap() {
        super("Свап игроков", "player_swap", "LEATHER_BOOTS", "Меняет местоположение игроков", "местами по кд");
        addConfig(interval);
    }

    private final Configuration<Integer> interval = new Configuration<>("interval", 300, "WATCH", this, "Интервал телепортации в секундах");

    private BukkitRunnable runnable = new SwapRunnable();

    public void start() {
        runnable.runTaskTimer(ScenarioMix.plugin, 0L, interval.getValue() * 20L);
    }

    public void stop() {
        runnable.cancel();
        runnable = new SwapRunnable();
    }
}
