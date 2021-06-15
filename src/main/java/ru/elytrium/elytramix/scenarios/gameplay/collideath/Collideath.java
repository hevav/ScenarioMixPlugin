package ru.elytrium.elytramix.scenarios.gameplay.collideath;

import org.bukkit.entity.Player;
import ru.elytrium.elytramix.scenarios.Scenario;

public class Collideath extends Scenario {
    public Collideath() {
        super("Тактильная смерть", "collideath", "CACTUS", "scenario", "Обнимашки запрещены");
        addBukkitRunnable(new CollideRunnable(), 1);
    }

    @Override
    public void start(Player player) {

    }

    @Override
    public void stop() {

    }
}
