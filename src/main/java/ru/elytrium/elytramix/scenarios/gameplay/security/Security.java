package ru.elytrium.elytramix.scenarios.gameplay.security;

import org.bukkit.entity.Player;
import ru.elytrium.elytramix.scenarios.Scenario;

public class Security extends Scenario {
    public Security() {
        super("Security", "security", "SHIELD", "scenario","weak - не может бить;\nstrong - не может ломать");
        addListener(new WeakAttackListener());
        addListener(new BreakListener());
    }

    @Override
    public void start(Player player) {

    }

    @Override
    public void stop() {

    }
}
