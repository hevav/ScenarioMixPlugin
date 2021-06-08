package ru.elytrium.elytramix.scenarios.gameplay.nojump;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import ru.elytrium.elytramix.utils.PlayerUtils;

public class JumpListener implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        if (PlayerUtils.isValid(event.getPlayer()))
            if (!event.getPlayer().isOnGround() && event.getFrom().getY() + 0.419 < event.getTo().getY()) {
                event.getPlayer().setHealth(0);
            }
    }
}
