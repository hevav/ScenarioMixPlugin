package ru.elytrium.elytramix.utils;

import org.bukkit.Location;
import org.bukkit.World;

import java.util.Random;

public class Border {
    public static Location getRandomLocInBorder(World world, Random random) {
        int size = (int) Math.ceil(world.getWorldBorder().getSize());
        Location center = world.getWorldBorder().getCenter();
        int x = random.nextInt(size + 1) + center.getBlockX() - size / 2;
        int y = random.nextInt(255) + 1;
        int z = random.nextInt(size + 1) + center.getBlockZ() - size / 2;
        return new Location(world, x, y, z);
    }
}
