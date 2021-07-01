package net.elytrium.elytramix.scenarios.commands.nametagVisibility;

import net.elytrium.elytramix.scenarios.Scenario;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.Arrays;

public class NametagVisibility extends Scenario {
    public NametagVisibility() {
        super("Видимость ников", "nametag_toggle", "NAME_TAG", "tool","Изменяет видимость", "никнеймов игроков");
        addListener(new JoinListener());
    }

    @Override
    public void start(Player player) {
        Bukkit.getOnlinePlayers().forEach(getNametagTeam()::addPlayer);
        Arrays.stream(Bukkit.getOfflinePlayers()).forEach(getNametagTeam()::addPlayer);
    }

    @Override
    public void stop() {
        Bukkit.getOnlinePlayers().forEach(getNametagTeam()::removePlayer);
        Arrays.stream(Bukkit.getOfflinePlayers()).forEach(getNametagTeam()::removePlayer);
    }

    public static Team getNametagTeam(){
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
        Team team = scoreboard.getTeam("em-nametag");

        if(team == null){
            team = scoreboard.registerNewTeam("em-nametag");
            team.setOption(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.NEVER);
        }

        return team;
    }
}
