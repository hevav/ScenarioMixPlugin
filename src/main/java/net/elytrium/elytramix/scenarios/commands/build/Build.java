package net.elytrium.elytramix.scenarios.commands.build;

import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import net.elytrium.elytramix.scenarios.Scenario;
import org.bukkit.entity.Player;

import static net.elytrium.elytramix.utils.WorldGuardUtil.getLocatedRegion;
import static net.elytrium.elytramix.utils.WorldGuardUtil.toggleRegionFlag;

public class Build extends Scenario {

    public Build() {
        super("Строительство", "build_toggle", "GRASS_BLOCK", "fast-command","Переключает флаг §eBUILD", "§oв текущем регионе");
    }

    @Override
    public void start(Player player) {
        ProtectedRegion region = getLocatedRegion(player.getLocation());
        toggleRegionFlag(region, Flags.BUILD, player);
    }

    @Override
    public void stop() {

    }
}
