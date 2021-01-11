package io.github._7isenko.scenariomix.scenarios.gameplay.pusher;

import io.github._7isenko.scenariomix.scenarios.Scenario;

public class Pusher extends Scenario {

    public Pusher() {
        super("Толкатель", "pusher", "PAPER", "Каждую секунду толкает всех", "игроков в случайную сторону");
        addBukkitRunnable(new PusherRunnable(), 20);
        addListener(new AttackListener());
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }
}
