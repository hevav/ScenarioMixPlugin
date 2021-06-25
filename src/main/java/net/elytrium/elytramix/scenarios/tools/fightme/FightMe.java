package net.elytrium.elytramix.scenarios.tools.fightme;

import net.elytrium.elytramix.scenarios.Scenario;
import org.bukkit.entity.Player;

public class FightMe extends Scenario {
    public FightMe() {
        super("Все на меня!", "fight_me", "ARMOR_STAND", "tool","Все игроки могут бить только тех,", "у кого есть тег fight_me", "/scoreboard players tag <nick> add fight_me");
        this.addListener(new AttackListener());}

    @Override
    public void start(Player player) {

    }

    @Override
    public void stop() {

    }
}
