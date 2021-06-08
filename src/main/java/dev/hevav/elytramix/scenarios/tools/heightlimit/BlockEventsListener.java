package dev.hevav.elytramix.scenarios.tools.heightlimit;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockEventsListener implements Listener {
    private final HeightLimit limit;

    public BlockEventsListener(HeightLimit scenario) {
        this.limit = scenario;
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        if (limit.isBreakAllowed())
            return;
        if (!checkHeight(event.getBlock().getY()) && !checkPlayer(event.getPlayer())) {
            event.setCancelled(true);
            event.getPlayer().sendMessage("Тут нельзя ломать блоки");
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        if (limit.isBuildAllowed())
            return;
        if (!checkHeight(event.getBlock().getY()) && !checkPlayer(event.getPlayer())) {
            event.setCancelled(true);
            event.getPlayer().sendMessage("Тут нельзя ставить блоки");
        }
    }

    /**
     * @param player
     * @return true if he can place anyway
     */
    private boolean checkPlayer(Player player) {
        return limit.isIgnoreCreative() && (player.getGameMode() == GameMode.CREATIVE || player.getGameMode() == GameMode.SPECTATOR);
    }

    /**
     * @param height
     * @return true if block may be placed
     */
    private boolean checkHeight(int height) {
        return height >= limit.getMin() && height <= limit.getMax();
    }
}
