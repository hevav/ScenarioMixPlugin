package net.elytrium.elytramix.scenarios.gameplay.lastsight;

import net.elytrium.elytramix.scenarios.Scenario;
import org.bukkit.entity.Player;

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
