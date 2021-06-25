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

        Bukkit.getOnlinePlayers().forEach(player -> {
            if(player != commandSender){
                player.kickPlayer(Plugin.getInstance().getMessageString("elytramix.kickall"));
            }
        });

        commandSender.sendMessage(Plugin.getInstance().getMessageString("elytramix.kickall"));

        return true;
    }
}
