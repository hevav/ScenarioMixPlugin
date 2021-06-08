package ru.elytrium.elytramix.scenarios.tools.fill;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import ru.elytrium.elytramix.Plugin;
import ru.elytrium.elytramix.scenarios.Scenario;
import ru.elytrium.elytramix.scenarios.config.Configuration;

public class Fill extends Scenario {
    public Fill() {
        super("Заполнение", "fill", "LAVA_BUCKET", "Постепенно заполняет мир", "выбранным блоком");
        addConfig(material);
        addConfig(delay);
        addConfig(amount);
        addConfig(current);
        addConfig(replace);
        addConfig(upside);
    }

    private final Configuration<Material> material = new Configuration<>("material", Material.LAVA, "BOWL", this, "Чем заполнять");
    private final Configuration<Integer> delay = new Configuration<>("delay", 60, "FEATHER", this, "Время между заполнениями", "(секунды)");
    private final Configuration<Integer> amount = new Configuration<>("amount", 2, "COBBLESTONE_STAIRS", this, "Толщина блоков, заполняемых ", "за один раз");
    private final Configuration<Integer> current = new Configuration<>("current", 2, "SIGN", this, "Текущая высота", "заполнения");
    private final Configuration<Boolean> replace = new Configuration<>("replace", false, "IRON_SPADE", this, "Заменять блоки", "любого типа");
    private final Configuration<Boolean> upside = new Configuration<>("upside", false, "SNOW_BALL", this, "Заполнение идёт сверху");
    private BukkitRunnable task = null;

    @Override
    public void start() {
        task = new BukkitRunnable() {
            @Override
            public void run() {
                if (!isStarted()) {
                    task.cancel();
                    return;
                }
                World world;
                try {
                    world = ((Player) Bukkit.getOnlinePlayers().toArray()[0]).getWorld();
                } catch (IndexOutOfBoundsException e) {
                    Bukkit.broadcastMessage("никого нет...");
                    return;
                }
                int borderSize = (int) Math.ceil(world.getWorldBorder().getSize());
                if (borderSize > 1000) {
                    Bukkit.broadcast(ChatColor.RED + "Размер границ мира больше 1000, заполнение отменено. Нужен перезапуск", "scenariomix.debug");
                    task.cancel();
                    task = null;
                    return;
                }
                fill(world, borderSize);
                start();
            }
        };
        task.runTaskLater(Plugin.plugin, delay.value() * 20);
    }

    @Override
    public void stop() {
        if (task != null && !task.isCancelled()) {
            task.cancel();
            task = null;
        }
    }

    private void fill(World world, int size) {
        if (current.value() >= 256 || current.value() < 0)
            return;
        Location center = world.getWorldBorder().getCenter();
        for (int y = 0; y < amount.value(); y++) {
            for (int x = center.getBlockX() - size / 2; x <= center.getBlockX() + size / 2; x++) {
                for (int z = center.getBlockZ() - size / 2; z <= center.getBlockZ() + size / 2; z++) {
                    Block block = world.getBlockAt(x, current.value() + y, z);
                    if (!replace.value() && block.getType() != Material.AIR)
                        continue;
                    block.setType(material.value(), false);
                }
            }
        }
        if (upside.getValue()) {
            current.setValue(current.getValue() - amount.value());
        } else
            current.setValue(current.getValue() + amount.value());
    }
}
