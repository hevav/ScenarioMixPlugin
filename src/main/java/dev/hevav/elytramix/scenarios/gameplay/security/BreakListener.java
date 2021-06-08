package dev.hevav.elytramix.scenarios.gameplay.security;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BreakListener implements Listener {
    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        if (event.getPlayer().getScoreboardTags().contains("strong"))
            event.setCancelled(true);
    }
}
