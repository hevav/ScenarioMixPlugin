package ru.elytrium.elytramix.scenarios.gameplay.randomgive;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import ru.elytrium.elytramix.utils.PlayerUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class GiveRunnable extends BukkitRunnable {
    private final RandomGive scenario;
    private static final Random random = new Random();

    public GiveRunnable(RandomGive scenario){
        this.scenario = scenario;
    }

    public void run() {
        try {
            Collection<? extends Player> players = Bukkit.getOnlinePlayers();
            int limit = scenario.getLimit();
            boolean multiply = scenario.isMiddleMultiplier();

            players.forEach(player -> {
                if (PlayerUtils.isValid(player)){
                    List<ItemStack> randomItemStack = getRandomItemStack(limit, multiply, player.getLocation());
                    randomItemStack.forEach(itemStack ->
                        player.getInventory().addItem(itemStack)
                    );
                }
            });
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<ItemStack> getRandomItemStack(int limit, boolean multiply, Location location){
        List<ItemStack> randomItemStack = new ArrayList<>();
        for(int i = 0; i < multiplyLimit(limit, multiply, location); i++){
            Material[] all = Material.values();
            Material material = all[random.nextInt(all.length - 1)];
            ItemStack itemStack = new ItemStack(material);
            randomItemStack.add(RandomNBT.addRandomNBT(itemStack));
        }
        return randomItemStack;
    }

    private int multiplyLimit(int limit, boolean multiply, Location playerLocation){
        if (multiply){
            WorldBorder border = playerLocation.getWorld().getWorldBorder();
            Location middleLocation = border.getCenter();

            double amountMultiplier = (middleLocation.distance(playerLocation) / border.getSize());
            limit *= amountMultiplier;
        }

        return Math.max(0, Math.min(limit, 192));
    }
}