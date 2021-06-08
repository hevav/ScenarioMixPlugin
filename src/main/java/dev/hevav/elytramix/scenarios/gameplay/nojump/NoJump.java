package dev.hevav.elytramix.scenarios.gameplay.nojump;

import dev.hevav.elytramix.scenarios.Scenario;

public class NoJump extends Scenario {

    public NoJump() {
        super("Не прыгать", "no_jump", "FEATHER", "Прыгать нельзя", "я серьёзно, не прыгай");
        addListener(new JumpListener());
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }
}
