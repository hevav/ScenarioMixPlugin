package io.github._7isenko.scenariomix.scenarios.gameplay.spiderpocalypse;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Creature;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlaceListener implements Listener {
    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        Material material = event.getBlockPlaced().getType();
        if (material == Material.WORKBENCH || material == Material.FURNACE) {
            Block block = event.getBlockPlaced().getRelative(BlockFace.UP);
            if (block.getType() == Material.AIR)
                ((Creature)block.getWorld().spawnEntity(block.getLocation().add(0.5, 0, 0.5), EntityType.CAVE_SPIDER)).setTarget(event.getPlayer());
        }
    }

}
