package io.github._7isenko.scenariomix.scenarios.gameplay.playerride;

import io.github._7isenko.scenariomix.ScenarioMix;
import org.bukkit.Bukkit;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import org.spigotmc.event.entity.EntityDismountEvent;

public class PlayerRidingListener implements Listener {

    private final PlayerRide scenario;

    public PlayerRidingListener(PlayerRide scenario) {
        this.scenario = scenario;
    }

    @EventHandler
    public void onClick(PlayerInteractAtEntityEvent event) {
        if (event.getRightClicked() instanceof Player) {
            Player player = event.getPlayer();
            Player player2 = (Player) event.getRightClicked();

            if (player.equals(player2)) return;

            if (scenario.allowOnlyTeam()) {
                Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
                Team team = scoreboard.getPlayerTeam(player);
                Team team2 = scoreboard.getPlayerTeam(player2);

                if (team == null || !team.equals(team2)) return;
            }

            if (scenario.isAllowMany()) {
                getTopPlayer(player2).addPassenger(player);
            } else if (player2.getVehicle() == null || !(player2.getVehicle() instanceof Player)) {
                player2.addPassenger(player);
            }
        }
    }

    @EventHandler
    public void onLeave(EntityDismountEvent event) {
        if (event.getEntity() instanceof Player && event.getDismounted() instanceof Player) {
            if (!scenario.isAllowLeave()) {
                Bukkit.getScheduler().scheduleSyncDelayedTask(ScenarioMix.plugin, () -> {
                    if (!event.getEntity().isInsideVehicle())
                        event.getDismounted().addPassenger(event.getEntity());
                });
            }
            else if (scenario.needKillOnLeave()){
                Bukkit.getScheduler().scheduleSyncDelayedTask(ScenarioMix.plugin, () -> {
                    if (!event.getEntity().isInsideVehicle()){
                        ((Player) event.getEntity()).setHealth(0d);
                        ((Player) event.getDismounted()).setHealth(0d);
                    }
                });
            }
        }
    }

    private Entity getTopPlayer(Entity entity) {
        if (!entity.getPassengers().isEmpty()) {
            return getTopPlayer(entity.getPassengers().get(0));
        }
        return entity;
    }
}
