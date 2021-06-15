package ru.elytrium.elytramix.scenarios.gameplay.lastsight;

import org.bukkit.entity.Player;
import ru.elytrium.elytramix.scenarios.Scenario;

public class LastSight extends Scenario {

    public LastSight() {
        super("Последний взгляд", "last_sight", "GLASS", "scenario","Взгляд на человека с тегом", "last_sight вас мгновенно убьёт", "/scoreboard players tag <nick> add last_sight");
        addBukkitRunnable(new KillingRunnable(), 2);
    }

    public void start(Player player) {

    }

    public void stop() {
    }

}
