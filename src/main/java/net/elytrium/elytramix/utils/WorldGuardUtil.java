package net.elytrium.elytramix.utils;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.flags.Flag;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import net.elytrium.elytramix.Plugin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.List;

public class WorldGuardUtil {

    public static ProtectedRegion getLocatedRegion(Location loc) {
        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionManager mgr = container.get(BukkitAdapter.adapt(loc.getWorld()));
        List<String> regions = mgr.getApplicableRegionsIDs(BlockVector3.at(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ()));

        try{
            return mgr.getRegion(regions.get(0));
        } catch(IndexOutOfBoundsException e){
            return mgr.getRegion("__global__");
        }
    }

    public static void toggleRegionFlag(ProtectedRegion region, Flag flag, Player player){
        if(region.getFlag(flag) == StateFlag.State.ALLOW){
            region.setFlag(flag, StateFlag.State.DENY);
            player.sendMessage(Plugin.getInstance().getPrefixString()+"Значение флага §c"+
                    flag.getName()+" §fустановлено на §eDENY §fв регионе §c"+region.getId());
        } else if(region.getFlag(flag) == StateFlag.State.DENY){
            region.setFlag(flag, StateFlag.State.ALLOW);
            player.sendMessage(Plugin.getInstance().getPrefixString()+"Значение флага §c"+
                    flag.getName()+" §fустановлено на §eALLOW §fв регионе §c"+region.getId());
        } else {
            region.setFlag(flag, StateFlag.State.DENY);
            player.sendMessage(Plugin.getInstance().getPrefixString()+"Значение флага §c"+
                    flag.getName()+" §fустановлено на §eDENY §fв регионе §c"+region.getId());
        }
    }
}
