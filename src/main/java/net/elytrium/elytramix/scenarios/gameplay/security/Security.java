package net.elytrium.elytramix.scenarios.gameplay.security;

import net.elytrium.elytramix.scenarios.Scenario;
import org.bukkit.entity.Player;

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
