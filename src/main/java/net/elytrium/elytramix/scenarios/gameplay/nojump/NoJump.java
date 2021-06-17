package net.elytrium.elytramix.scenarios.gameplay.nojump;

import net.elytrium.elytramix.scenarios.Scenario;
import org.bukkit.entity.Player;

public class NoJump extends Scenario {

    public NoJump() {
        super("Не прыгать", "no_jump", "FEATHER", "scenario","Прыгать нельзя", "я серьёзно, не прыгай");
        addListener(new JumpListener());
    }

    @Override
    public void start(Player player) {

    }

    @Override
    public void stop() {

    }
}
