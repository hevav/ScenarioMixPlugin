package dev.hevav.essentialsplugin.scenarios.gameplay.apocalypse;

import dev.hevav.essentialsplugin.utils.ItemUtils;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.FallingBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.material.MaterialData;

public class MeteorSpawner implements Listener {
    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (event.getMaterial() == Material.BLAZE_ROD && event.getPlayer().isOp()) {
                event.setCancelled(true);
                Location location = event.getPlayer().getTargetBlock(null, 100).getLocation().clone().add(0.5D, 0.0D, 0.5D);
                location.setY(255);
                FallingBlock fblock = location.getBlock().getWorld().spawnFallingBlock(location, new MaterialData(ItemUtils.getMaterial("MAGMA")));
                new Meteor(fblock);
                if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
                    fblock.addScoreboardTag("super");
                    event.getPlayer().sendMessage(ChatColor.RED + "Супер " + ChatColor.GOLD + "метеор отправлен в X: " + location.getBlockX() + " Z: " + location.getBlockZ());
                    return;
                }
                event.getPlayer().sendMessage(ChatColor.GOLD + "Метеор отправлен в X: " + location.getBlockX() + " Z: " + location.getBlockZ());
            }
        }
    }
}
