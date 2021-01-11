package io.github._7isenko.scenariomix.scenarios.tools.autospectator;

import io.github._7isenko.scenariomix.scenarios.Scenario;

public class AutoSpectator extends Scenario {

    public AutoSpectator() {
        super("Авто-gm 3", "auto_spectator", "FEATHER", "Когда игрок возрождается, он", "автоматически получает гм 3");
        addListener(new RespawnListener());}

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }
}
