package ru.elytrium.elytramix.scenarios.gameplay.snowfall;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.material.MaterialData;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import ru.elytrium.elytramix.Plugin;
import ru.elytrium.elytramix.scenarios.Scenario;

import java.util.*;

public class Snowfall extends Scenario {
    protected Random random;
    protected float percentage;
    protected Map<Chunk, Float> chunks;
    protected Set<Chunk> loaded;
    protected Material type;
    protected BukkitTask task;
    protected World world;

    public Snowfall() {
        super("Снегопад", "snowfall", "SNOW_BLOCK", "scenario","Запускает сильный снегопад");
    }

    @Override
    public void start(Player player) {
        percentage = 0.05F;
        random = new Random();
        chunks = new HashMap<>();
        loaded = new HashSet<>();
        type = Material.SNOW_BLOCK;
        world = Bukkit.getWorlds().get(0);
        task = (new BukkitRunnable() {
            public void run() {
                percentage = (float) ((double) percentage + 0.01D);


                for (Chunk chunk : world.getLoadedChunks()) {
                    if (isChunkInsideBorder(world.getWorldBorder(), chunk)) {
                        if (loaded.contains(chunk)) continue;
                        startChunk(chunk);
                    }
                }

            }
        }).runTaskTimer(Plugin.plugin, 0L, 1200L);
    }

    @Override
    public void stop() {
        if (task != null) {
            loaded.clear();
            task.cancel();
            task = null;
        }
    }

    private void startChunk(final Chunk chunk) {
        final float chunkPercentage = chunks.getOrDefault(chunk, 0.0F);
        loaded.add(chunk);
        new BukkitRunnable() {
            int counter = 0;
            final int tickMiss = 3;
            final int amount;

            {
                amount = 450 * tickMiss;
            }

            public void run() {
                if (!isStarted()) {
                    cancel();
                } else if (!chunk.isLoaded()) {
                    loaded.remove(chunk);
                    cancel();
                } else if (!isChunkInsideBorder(world.getWorldBorder(), chunk)) {
                    loaded.remove(chunk);
                    cancel();
                } else {
                    if (counter % tickMiss == 0) {
                        for (int i = 0; (float) i < 15360.0F * (percentage - chunkPercentage) / (float) amount; ++i) {
                            Block block = chunk.getBlock(getRandomNumberBetween(0, 15), getRandomNumberBetween(10, 130), getRandomNumberBetween(0, 15));
                            if (block.getType() == Material.AIR) {
                                Location add = block.getLocation().clone().add(0.5D, 0.0D, 0.5D);
                                block.getWorld().spawnFallingBlock(add, new MaterialData(type));
                            }
                        }
                        chunks.put(chunk, percentage);
                    }

                    ++counter;
                }
            }
        }.runTaskTimer(Plugin.plugin, 0L, 1L);

    }

    private int getRandomNumberBetween(int min, int max) {
        return min == max ? min : random.nextInt(max - min + 1) + min;
    }

    private boolean isChunkInsideBorder(WorldBorder border, Chunk chunk) {
        int[] xx = new int[]{0, 15};
        int[] zz = new int[]{0, 15};
        for (int x : xx) {
            for (int z : zz) {
                if (border.isInside(chunk.getBlock(x, 1, z).getLocation()))
                    return true;
            }
        }
        return false;
    }
}