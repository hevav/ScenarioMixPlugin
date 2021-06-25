package net.elytrium.elytramix.scenarios.gameplay.pusher;

import net.elytrium.elytramix.scenarios.Scenario;
import org.bukkit.entity.Player;

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
