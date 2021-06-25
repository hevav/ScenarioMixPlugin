package net.elytrium.elytramix.cui.essentials;

import net.elytrium.elytramix.Plugin;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Day implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender instanceof Player)){
            commandSender.sendMessage(ChatColor.RED+"Данная команда доступна только игрокам!");
            return true;
        }

        Player p = (Player) commandSender;
        World world = p.getWorld();
        world.setTime(0); // Устанавливаем время на 0 тиков

        String msg = Plugin.getInstance().getMessageString("elytramix.day")
                .replace("{world}", world.getName());

        p.sendMessage(msg);

        return true;
    }
}
