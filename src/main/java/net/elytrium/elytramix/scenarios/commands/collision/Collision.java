package net.elytrium.elytramix.scenarios.commands.collision;

import net.elytrium.elytramix.scenarios.Scenario;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.Arrays;

public class Collision extends Scenario {
    public Collision() {
        super("Правило коллизии", "collision_toggle", "MAGMA_CREAM", "tool","Изменяет значение", "Collision Rule");
        addListener(new JoinListener());
    }

    Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();

    @Override
    public void start(Player player) {
        Bukkit.getOnlinePlayers().forEach(getCollisionTeam()::addPlayer);
        Arrays.stream(Bukkit.getOfflinePlayers()).forEach(getCollisionTeam()::addPlayer);
    }

    @Override
    public void stop() {
        Bukkit.getOnlinePlayers().forEach(getCollisionTeam()::removePlayer);
        Arrays.stream(Bukkit.getOfflinePlayers()).forEach(getCollisionTeam()::removePlayer);
    }

    public static Team getCollisionTeam(){
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();

        Team team = scoreboard.getTeam("em-collision");

        if(team == null){
            team = scoreboard.registerNewTeam("em-collision");
            team.setOption(Team.Option.COLLISION_RULE , Team.OptionStatus.NEVER);
        }

        return team;
    }
}
