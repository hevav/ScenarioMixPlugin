package dev.hevav.essentialsplugin.scenarios.gameplay.lastsight;

import dev.hevav.essentialsplugin.scenarios.Scenario;

public class LastSight extends Scenario {

    public LastSight() {
        super("Последний взгляд", "last_sight", "GLASS", "Взгляд на человека с тегом", "last_sight вас мгновенно убьёт", "/scoreboard players tag <nick> add last_sight");
        addBukkitRunnable(new KillingRunnable(), 2);
    }

    public void start() {

    }

    public void stop() {
    }

}
