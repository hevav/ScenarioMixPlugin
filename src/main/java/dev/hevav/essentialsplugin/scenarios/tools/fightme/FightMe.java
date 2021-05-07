package dev.hevav.essentialsplugin.scenarios.tools.fightme;

import dev.hevav.essentialsplugin.scenarios.Scenario;

public class FightMe extends Scenario {
    public FightMe() {
        super("Все на меня!", "fight_me", "ARMOR_STAND", "Все игроки могут бить только тех,", "у кого есть тег fight_me", "/scoreboard players tag <nick> add fight_me");
        this.addListener(new AttackListener());}

    public void start() {

    }

    public void stop() {
    }
}
