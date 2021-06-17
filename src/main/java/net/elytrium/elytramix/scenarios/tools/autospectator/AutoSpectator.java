package net.elytrium.elytramix.scenarios.tools.autospectator;

import net.elytrium.elytramix.scenarios.Scenario;
import org.bukkit.entity.Player;

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
