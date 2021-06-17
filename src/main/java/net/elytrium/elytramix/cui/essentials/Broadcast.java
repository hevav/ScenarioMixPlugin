package net.elytrium.elytramix.cui.essentials;

import net.elytrium.elytramix.Plugin;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Broadcast implements CommandExecutor {
    private final Plugin plugin;

    public Broadcast(Plugin plugin){ this.plugin = plugin; }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        String message = String.join(" ", strings);
        message = message.replace("&", "\u00a7");

        Bukkit.broadcastMessage(plugin.getMessagesConfig().getString("prefix")
                .replace("&", "\u00A7")+message);

        return true;
    }
}
