package ru.elytrium.elytramix.scenarios.commands.nametagVisibility;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import ru.elytrium.elytramix.scenarios.Scenario;

public class NametagVisibility extends Scenario {
    public NametagVisibility() {
        super("Видимость ников", "nametag_toggle", "NAME_TAG", "tool","Изменяет видимость", "никнеймов игроков");
        addListener(new JoinListener());
    }

    @Override
    public void start(Player player) {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
        Team team = scoreboard.getTeam("em-nametag");

        if(team == null){
            team = scoreboard.registerNewTeam("em-nametag");
            team.setOption(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.NEVER);
        }

        for(Player p : Bukkit.getOnlinePlayers()){
            team.addPlayer(p);
        } for(OfflinePlayer p : Bukkit.getOfflinePlayers()){
            team.addPlayer(p);
        }
    }

    @Override
    public void stop() {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
        Team team = scoreboard.getTeam("em-nametag");

        if(team == null){
            team = scoreboard.registerNewTeam("em-nametag");
            team.setOption(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.NEVER);
        }

        for(Player p : Bukkit.getOnlinePlayers()){
            team.removePlayer(p);
        } for(OfflinePlayer p : Bukkit.getOfflinePlayers()){
            team.removePlayer(p);
        }
    }
}
