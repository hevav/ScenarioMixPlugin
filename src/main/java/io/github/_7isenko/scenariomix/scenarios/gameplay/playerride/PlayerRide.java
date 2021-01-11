package io.github._7isenko.scenariomix.scenarios.gameplay.playerride;

import io.github._7isenko.scenariomix.scenarios.Scenario;
import io.github._7isenko.scenariomix.scenarios.config.Configuration;

public class PlayerRide extends Scenario {
    private final Configuration<Boolean> allowMany = new Configuration<>("allowMany", false, "LEATHER_BOOTS", this, "Разрешает игрокам сидеть", "большой кучей");
    private final Configuration<Boolean> allowLeave = new Configuration<>("allowLeave", true, "IRON_BOOTS", this, "Разрешает слазить");
    private final Configuration<Boolean> onlyTeam = new Configuration<>("onlyTeam", false, "EMPTY_MAP", this, "Разрешает садиться только", "игрокам одной команды");
    private final Configuration<Boolean> killOnLeave = new Configuration<>("killOnLeave", false, "DIAMOND_SWORD", this, "Убивать когда кто-то встает");

    public PlayerRide() {
        super("Погнали", "playerride", "SADDLE", "Теперь можно ездить на игроках");
        addConfig(allowMany);
        addConfig(allowLeave);
        addConfig(onlyTeam);
        addConfig(killOnLeave);
        addListener(new PlayerRidingListener(this));
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    public boolean isAllowMany(){
        return allowMany.getValue();
    }

    public boolean isAllowLeave(){
        return allowLeave.getValue();
    }

    public boolean allowOnlyTeam(){
        return onlyTeam.getValue();
    }

    public boolean needKillOnLeave(){
        return killOnLeave.getValue();
    }
}
