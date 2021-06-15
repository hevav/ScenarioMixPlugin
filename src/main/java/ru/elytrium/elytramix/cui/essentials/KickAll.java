package ru.elytrium.elytramix.cui.essentials;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.elytrium.elytramix.Plugin;

public class KickAll implements CommandExecutor {
    private final Plugin plugin;

    public KickAll(Plugin plugin){ this.plugin = plugin; }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        String reason = String.join(" ", strings);

        reason = reason.replace("&", "\u00a7");

        for(Player p: Bukkit.getOnlinePlayers()){
            if(p != commandSender){
                p.kickPlayer(reason);
            }
        }

        commandSender.sendMessage(plugin.getMessageString("elytramix.kickall"));

        return true;
    }
}
