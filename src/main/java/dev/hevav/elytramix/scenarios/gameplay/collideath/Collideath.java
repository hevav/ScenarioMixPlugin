package dev.hevav.elytramix.scenarios.gameplay.collideath;

import dev.hevav.elytramix.scenarios.Scenario;

public class Collideath extends Scenario {
    public Collideath() {
        super("Тактильная смерть", "collideath", "CACTUS", "Обнимашки запрещены");
        addBukkitRunnable(new CollideRunnable(), 1);
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }
}
