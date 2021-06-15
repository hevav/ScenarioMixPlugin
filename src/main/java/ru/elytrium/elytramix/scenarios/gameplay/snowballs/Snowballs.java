package ru.elytrium.elytramix.scenarios.gameplay.snowballs;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import ru.elytrium.elytramix.scenarios.Scenario;

public class Snowballs extends Scenario {

    public Snowballs() {
        super("Снежки", "snowballs", "SNOW_BALL", "scenario","Теперь снежки могут", "наносить урон");
        addListener(new SnowballHitListener());
    }

    @Override
    public void start(Player player) {
        Bukkit.getOnlinePlayers().forEach(p -> p.getInventory().addItem(new ItemStack(Material.PUMPKIN, 1), new ItemStack(Material.SNOW_BLOCK, 2)));

    }

    @Override
    public void stop() {

    }
}
