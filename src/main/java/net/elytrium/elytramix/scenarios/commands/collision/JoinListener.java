package net.elytrium.elytramix.scenarios.commands.collision;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import static net.elytrium.elytramix.scenarios.commands.collision.Collision.getCollisionTeam;

public class JoinListener implements Listener {
    Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
    Team team = scoreboard.getTeam("em-nametag");

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        getCollisionTeam().addPlayer(e.getPlayer());
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e){
        getCollisionTeam().removePlayer(e.getPlayer());
    }
}
