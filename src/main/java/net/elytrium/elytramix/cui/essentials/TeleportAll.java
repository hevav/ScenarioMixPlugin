package net.elytrium.elytramix.cui.essentials;

import net.elytrium.elytramix.Plugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class TeleportAll implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender instanceof Player)){
            commandSender.sendMessage(ChatColor.RED+"Данная команда доступна только игрокам!");
            return true;
        }

        Bukkit.getOnlinePlayers().forEach(player -> player.teleport((Entity) commandSender));

        commandSender.sendMessage(Plugin.getInstance().
                getMessageString("elytramix.tpall"));
        return true;
    }
}
