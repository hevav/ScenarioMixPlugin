package ru.elytrium.elytramix.scenarios.commands.collision;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class JoinListener implements Listener {
    Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
    Team team = scoreboard.getTeam("em-nametag");

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        if(team == null){
            team = scoreboard.registerNewTeam("em-nametag");
            team.setOption(Team.Option.COLLISION_RULE, Team.OptionStatus.NEVER);
        }

        team.addPlayer(e.getPlayer());
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e){
        if(team == null){
            team = scoreboard.registerNewTeam("em-nametag");
            team.setOption(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.NEVER);
        }

        team.removePlayer(e.getPlayer());
    }
}
