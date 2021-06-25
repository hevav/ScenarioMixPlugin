package net.elytrium.elytramix.scenarios.commands.collision;

import net.elytrium.elytramix.scenarios.Scenario;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class Collision extends Scenario {
    public Collision() {
        super("Правило коллизии", "collision_toggle", "MAGMA_CREAM", "tool","Изменяет значение", "Collision Rule");
        addListener(new JoinListener());
    }

    Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();

    @Override
    public void start(Player player) {
        Team team = scoreboard.getTeam("em-collision");

        if(team == null){
            team = scoreboard.registerNewTeam("em-collision");
            team.setOption(Team.Option.COLLISION_RULE , Team.OptionStatus.NEVER);
        }

        for(Player p : Bukkit.getOnlinePlayers()){
            team.addPlayer(p);
        } for(OfflinePlayer p : Bukkit.getOfflinePlayers()){
            team.addPlayer(p);
        }
    }

    @Override
    public void stop() {
        Team team = scoreboard.getTeam("em-collision");

        if(team == null){
            team = scoreboard.registerNewTeam("em-collision");
            team.setOption(Team.Option.COLLISION_RULE , Team.OptionStatus.NEVER);
        }

        for(Player p : Bukkit.getOnlinePlayers()){
            team.removePlayer(p);
        } for(OfflinePlayer p : Bukkit.getOfflinePlayers()){
            team.removePlayer(p);
        }
    }
}
