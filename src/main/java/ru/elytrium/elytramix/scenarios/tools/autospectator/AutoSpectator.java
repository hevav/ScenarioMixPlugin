package ru.elytrium.elytramix.scenarios.tools.autospectator;

import org.bukkit.entity.Player;
import ru.elytrium.elytramix.scenarios.Scenario;

public class AutoSpectator extends Scenario {

    public AutoSpectator() {
        super("Авто-gm 3", "auto_spectator", "FEATHER", "tool","Когда игрок возрождается, он", "автоматически получает гм 3");
        addListener(new RespawnListener());}

    @Override
    public void start(Player player) {

    }

    @Override
    public void stop() {

    }
}
