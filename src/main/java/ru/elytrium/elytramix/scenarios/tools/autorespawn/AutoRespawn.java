package ru.elytrium.elytramix.scenarios.tools.autorespawn;

import org.bukkit.entity.Player;
import ru.elytrium.elytramix.scenarios.Scenario;

public class AutoRespawn extends Scenario {
    public AutoRespawn() {
        super("Авто-возрожение", "auto_respawn", "FENCE_GATE", "tool","При смерти игрок будет", "автоматически возрождён");
        addListener(new DeathListener());
    }

    @Override
    public void start(Player player) {

    }

    @Override
    public void stop() {

    }
}
