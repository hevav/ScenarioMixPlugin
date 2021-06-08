package ru.elytrium.elytramix.scenarios.tools.autospectator;

import ru.elytrium.elytramix.scenarios.Scenario;

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
