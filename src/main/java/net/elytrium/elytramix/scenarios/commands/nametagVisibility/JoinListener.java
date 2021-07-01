package net.elytrium.elytramix.scenarios.commands.nametagVisibility;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import static net.elytrium.elytramix.scenarios.commands.nametagVisibility.NametagVisibility.getNametagTeam;

public class JoinListener implements Listener {
    @EventHandler()
    public void onJoin(PlayerJoinEvent e){
        getNametagTeam().addPlayer(e.getPlayer());
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e){
        getNametagTeam().removePlayer(e.getPlayer());
    }
}
