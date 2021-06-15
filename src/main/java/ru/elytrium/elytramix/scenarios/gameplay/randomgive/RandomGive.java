package ru.elytrium.elytramix.scenarios.gameplay.randomgive;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import ru.elytrium.elytramix.Plugin;
import ru.elytrium.elytramix.scenarios.Scenario;
import ru.elytrium.elytramix.scenarios.config.Configuration;

public class RandomGive extends Scenario {
    public RandomGive() {
        super("Рандомная выдача предметов", "random_give", "DIAMOND", "scenario","Выдает определенное количество рандомных блоков", "за определенное время и из определенного", "списка (по умолчанию все блоки и предметы)");
        addConfig(block_limit);
        addConfig(interval);
        addConfig(middle_multiplier);
    }

    private final Configuration<Integer> block_limit = new Configuration<>("block_limit", 1, "SKULL_ITEM", this, "Количество блоков за раз");
    private final Configuration<Integer> interval = new Configuration<>("interval", 10, "WATCH", this, "Интервал выдачи блоков в секундах");
    private final Configuration<Boolean> middle_multiplier = new Configuration<>("middle_multiplier", false, "COMPASS", this, "Выдавать ли больше блоков", "в центре зоны");
    private final Configuration<Material[]> whitelist = new Configuration<>("whitelist", new Material[]{Material.AIR}, "BARRIER", this, "Белый список выдаваемых", "блоков");

    private BukkitRunnable runnable = new GiveRunnable(this);

    public void start(Player player) {
        runnable.runTaskTimer(Plugin.getInstance(), 0L, interval.getValue() * 20L);
    }

    public void stop() {
        runnable.cancel();
        runnable = new GiveRunnable(this);
    }

    public int getLimit(){
        return block_limit.getValue();
    }

    public boolean isMiddleMultiplier(){
        return middle_multiplier.getValue();
    }
}
