package net.elytrium.elytramix.scenarios.gameplay.collideath;

import net.elytrium.elytramix.scenarios.Scenario;
import org.bukkit.entity.Player;

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
