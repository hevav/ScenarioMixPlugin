package net.elytrium.elytramix.scenarios.commands.clearWhitelist;

import net.elytrium.elytramix.Plugin;
import net.elytrium.elytramix.scenarios.Scenario;
import net.minecraft.server.v1_16_R3.WhiteList;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_16_R3.CraftServer;
import org.bukkit.entity.Player;

public class ClearWhitelist extends Scenario {
    public ClearWhitelist() {
        super("Очистить белый список", "clear-whitelist", "CLAY_BALL", "fast-command", "Очищает", "белый список");
    }

    @Override
    public void start(Player player) {
        WhiteList whitelist = ((CraftServer) Bukkit.getServer()).getHandle().getWhitelist();
        whitelist.getValues().clear();
        player.sendMessage(Plugin.getInstance().getPrefixString()+"Белый список очищен!");
    }

    @Override
    public void stop() {

    }
}
