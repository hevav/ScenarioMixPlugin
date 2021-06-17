package net.elytrium.elytramix.scenarios.tools.autorespawn;

import net.elytrium.elytramix.scenarios.Scenario;
import org.bukkit.entity.Player;

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
