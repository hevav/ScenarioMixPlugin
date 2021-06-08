package dev.hevav.elytramix.scenarios.tools.autorespawn;

import dev.hevav.elytramix.scenarios.Scenario;

public class AutoRespawn extends Scenario {
    public AutoRespawn() {
        super("Авто-возрожение", "auto_respawn", "FENCE_GATE", "При смерти игрок будет", "автоматически возрождён");
        addListener(new DeathListener());
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }
}