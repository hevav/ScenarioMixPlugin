package ru.elytrium.elytramix.scenarios.gameplay.lowestkiller;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import ru.elytrium.elytramix.Plugin;

class KillerRunnable implements Runnable {
    @Override
    public void run() {
        Bukkit.broadcastMessage(ChatColor.GREEN + "3...");

        new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage(ChatColor.YELLOW + "2...");
            }
        }.runTaskLater(Plugin.plugin, 20);

        new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage(ChatColor.RED + "1...");
            }
        }.runTaskLater(Plugin.plugin, 40);

        new BukkitRunnable() {
            @Override
            public void run() {
                double lowest = 257D;
                Player player = null;
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (p.getGameMode() == GameMode.SURVIVAL && !p.isDead() && p.getLocation().getY() < lowest) {
                        player = p;
                        lowest = p.getLocation().getY();
                    }
                }
                if (player != null) {
                    player.setHealth(0);
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1, 1);
                    Bukkit.broadcastMessage(ChatColor.YELLOW + player.getName() + " оказался ниже всех. Его высота - " + ChatColor.RED + (int) lowest);
                } else Bukkit.broadcastMessage(ChatColor.YELLOW + "Все мертвы...");
            }
        }.runTaskLater(Plugin.plugin, 60);

    }
}
