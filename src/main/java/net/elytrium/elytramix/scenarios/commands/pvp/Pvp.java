package net.elytrium.elytramix.scenarios.commands.pvp;

import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import net.elytrium.elytramix.scenarios.Scenario;
import org.bukkit.entity.Player;

import static net.elytrium.elytramix.utils.WorldGuardUtil.getLocatedRegion;
import static net.elytrium.elytramix.utils.WorldGuardUtil.toggleRegionFlag;

public class Pvp extends Scenario {
    public Pvp(){
        super("PVP", "pvp_toggle", "IRON_SWORD", "fast-command", "Переключает флаг §ePVP", "§oв текущем регионе");
    }

    @Override
    public void start(Player player) {
        ProtectedRegion region = getLocatedRegion(player.getLocation());
        toggleRegionFlag(region, Flags.PVP, player);
    }

    @Override
    public void stop() {

    }
}
