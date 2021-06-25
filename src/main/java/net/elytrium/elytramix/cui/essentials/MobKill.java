package net.elytrium.elytramix.cui.essentials;

import net.elytrium.elytramix.Plugin;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;

public class MobKill implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender instanceof Player)){
            commandSender.sendMessage(ChatColor.RED + "Данная команда доступна только игрокам!");
            return true;
        }

        if(strings[0] == null){
            return false;
        }

        Player sender = (Player) commandSender;
        int radius = Integer.parseInt(strings[0]);
        int count = 0;

        World world = sender.getWorld();
        for (Entity entity :
                world.getNearbyEntities(sender.getLocation(), radius, radius, radius)) {
            if(!(entity instanceof Mob)) continue;
            ((Mob) entity).setHealth(0);
            count++;
        }


        sender.sendMessage(Plugin.getInstance().getMessageString("elytramix.mobkill")
                .replace("{count}", String.valueOf(count))
                .replace("{radius}", String.valueOf(radius)));

        return true;
    }
}
