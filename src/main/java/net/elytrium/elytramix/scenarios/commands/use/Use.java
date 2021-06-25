package net.elytrium.elytramix.scenarios.commands.use;

import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import net.elytrium.elytramix.scenarios.Scenario;
import org.bukkit.entity.Player;

import static net.elytrium.elytramix.utils.WorldGuardUtil.getLocatedRegion;
import static net.elytrium.elytramix.utils.WorldGuardUtil.toggleRegionFlag;

public class Use extends Scenario {
    public Use(){
        super("Использование", "use_toggle", "FISHING_ROD", "fast-command", "Переключает флаг §eUSE", "§oв текущем регионе");
    }

    @Override
    public void start(Player player) {
        ProtectedRegion region = getLocatedRegion(player.getLocation());
        toggleRegionFlag(region, Flags.USE, player);
    }

    @Override
    public void stop() {

    }
}
