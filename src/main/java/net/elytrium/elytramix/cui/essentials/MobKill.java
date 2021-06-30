package net.elytrium.elytramix.cui.essentials;

import net.elytrium.elytramix.Plugin;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;

import java.util.concurrent.atomic.AtomicInteger;

public class MobKill implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender instanceof Player)){
            commandSender.sendMessage(ChatColor.RED + "Данная команда доступна только игрокам!");
            return true;
        }

        if(strings[0] == null) return false;

        Player sender = (Player) commandSender;
        Location loc = sender.getLocation();
        int radius = Integer.parseInt(strings[0]);

        AtomicInteger count = new AtomicInteger();

        World world = sender.getWorld();
        world.getNearbyEntities(loc, radius, radius, radius).stream().filter(e -> e.getType() != EntityType.PLAYER)
                .forEach(entity -> {
                    entity.remove();
                    count.getAndIncrement();
                });

        sender.sendMessage(Plugin.getInstance().getMessageString("elytramix.mobkill")
                .replace("{count}", String.valueOf(count.get()))
                .replace("{radius}", String.valueOf(radius)));

        return true;
    }
}
