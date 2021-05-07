package dev.hevav.essentialsplugin.scenarios.gameplay.security;

import dev.hevav.essentialsplugin.scenarios.Scenario;

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
