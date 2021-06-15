package ru.elytrium.elytramix.scenarios.gameplay.playerswap;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import ru.elytrium.elytramix.Plugin;
import ru.elytrium.elytramix.scenarios.Scenario;
import ru.elytrium.elytramix.scenarios.config.Configuration;

public class PlayerSwap extends Scenario {
    public PlayerSwap() {
        super("Свап игроков", "player_swap", "LEATHER_BOOTS", "scenario","Меняет местоположение игроков", "местами по кд");
        addConfig(interval);
    }

    private final Configuration<Integer> interval = new Configuration<>("interval", 300, "WATCH", this, "Интервал телепортации в секундах");

    private BukkitRunnable runnable = new SwapRunnable();

    public void start(Player player) {
        runnable.runTaskTimer(Plugin.getInstance(), 0L, interval.getValue() * 20L);
    }

    public void stop() {
        runnable.cancel();
        runnable = new SwapRunnable();
    }
}
