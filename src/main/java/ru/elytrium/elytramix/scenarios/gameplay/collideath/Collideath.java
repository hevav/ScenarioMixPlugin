package ru.elytrium.elytramix.scenarios.gameplay.collideath;

import ru.elytrium.elytramix.scenarios.Scenario;

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
