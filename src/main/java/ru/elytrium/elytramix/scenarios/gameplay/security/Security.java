package ru.elytrium.elytramix.scenarios.gameplay.security;

import ru.elytrium.elytramix.scenarios.Scenario;

public class Security extends Scenario {
    public Security() {
        super("Security", "security", "SHIELD", "weak - не может бить;\nstrong - не может ломать");
        addListener(new WeakAttackListener());
        addListener(new BreakListener());
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }
}
