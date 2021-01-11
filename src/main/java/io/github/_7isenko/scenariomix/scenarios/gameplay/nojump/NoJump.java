package io.github._7isenko.scenariomix.scenarios.gameplay.nojump;

import io.github._7isenko.scenariomix.scenarios.Scenario;

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
