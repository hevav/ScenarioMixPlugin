package net.elytrium.elytramix.scenarios.gameplay.apocalypse;

import org.bukkit.entity.FallingBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.ItemSpawnEvent;

import java.util.Random;

public class MeteorFallListener implements Listener {
    /*
    Сорри за такую глупую дубликацию кода, но я делал это всё в быстром темпе, и
    CTRL + C, CTRL + V был намного быстрее выноса всего в отдельные методы и продумывания структуры.
    Главное, что работает, и работает нормально
    */

    @EventHandler
    public void onFall(EntityChangeBlockEvent event) {
        if (event.getEntity() instanceof FallingBlock) {
            FallingBlock fallingBlock = (FallingBlock) event.getEntity();
            String material = fallingBlock.getMaterial().toString();
            if (material.equals("MAGMA") || material.equals("MAGMA_BLOCK")) {
                event.setCancelled(true);
                int power = new Random().nextInt(8) + 3;
                if (fallingBlock.getScoreboardTags().contains("super"))
                    power = 20;
                fallingBlock.getWorld().createExplosion(fallingBlock.getLocation(), power);
            }
        }
    }

    @EventHandler
    public void onItemSpawn(ItemSpawnEvent event) {
        event.getEntity().getNearbyEntities(0.75, 4, 0.75).forEach(entity -> {
            if (entity instanceof FallingBlock) {
                FallingBlock fallingBlock = (FallingBlock) entity;
                String material = fallingBlock.getMaterial().toString();
                if (material.equals("MAGMA") || material.equals("MAGMA_BLOCK")) {
                    event.setCancelled(true);
                    int power = new Random().nextInt(8) + 3;
                    if (fallingBlock.getScoreboardTags().contains("super"))
                        power = 20;
                    fallingBlock.getWorld().createExplosion(fallingBlock.getLocation(), power);
                }
            }
        });
    }

}
