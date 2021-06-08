package dev.hevav.elytramix.cui.essentials;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Broadcast implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        String message = String.join(" ", strings);
        message = message.replace("&", "\u00a7");

        Bukkit.broadcastMessage("§8§l[§5§lElytrium§8§l] §c"+message);

        return true;
    }
}
