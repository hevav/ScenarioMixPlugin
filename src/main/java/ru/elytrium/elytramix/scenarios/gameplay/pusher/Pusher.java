package ru.elytrium.elytramix.scenarios.gameplay.pusher;

import org.bukkit.entity.Player;
import ru.elytrium.elytramix.scenarios.Scenario;

public class Pusher extends Scenario {

    public Pusher() {
        super("Толкатель", "pusher", "PAPER", "scenario","Каждую секунду толкает всех", "игроков в случайную сторону");
        addBukkitRunnable(new PusherRunnable(), 20);
        addListener(new AttackListener());
    }

    @Override
    public void start(Player player) {

    }

    @Override
    public void stop() {

    }
}
