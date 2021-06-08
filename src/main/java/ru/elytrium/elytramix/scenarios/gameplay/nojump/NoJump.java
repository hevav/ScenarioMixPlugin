package ru.elytrium.elytramix.scenarios.gameplay.nojump;

import ru.elytrium.elytramix.scenarios.Scenario;

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
