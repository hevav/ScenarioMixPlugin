package net.elytrium.elytramix.cui.essentials;

import net.elytrium.elytramix.Plugin;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KickAll implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        String reason = String.join(" ", strings);

        reason = reason.replace("&", "\u00a7");

        for(Player p: Bukkit.getOnlinePlayers()){
            if(p != commandSender){
                p.kickPlayer(reason);
            }
        }

        commandSender.sendMessage(Plugin.getInstance().getMessageString("elytramix.kickall"));

        return true;
    }
}
