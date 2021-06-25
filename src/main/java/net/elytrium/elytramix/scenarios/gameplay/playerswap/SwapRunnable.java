package net.elytrium.elytramix.scenarios.gameplay.playerswap;

import net.elytrium.elytramix.utils.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SwapRunnable extends BukkitRunnable {
    public void run() {
        List<? extends Player> players = Bukkit.getOnlinePlayers()
                .stream()
                .filter(PlayerUtils::isValid)
                .collect(Collectors.toList());

        Collections.shuffle(players);

        for (int i = 0; i < players.size() / 2; i++) {
            Player player1 = players.get(i * 2);
            Player player2 = players.get(i * 2 + 1);

            Location p1Location = player1.getLocation();
            Location p2Location = player2.getLocation();

            player1.teleport(p2Location);
            player2.teleport(p1Location);
        }
    }
}