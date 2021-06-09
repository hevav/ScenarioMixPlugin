package ru.elytrium.elytramix.cui.essentials;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.elytrium.elytramix.Plugin;

public class Rain implements CommandExecutor {
    private Plugin plugin;

    public Rain(Plugin plugin){ this.plugin = plugin; }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender instanceof Player)) {
            commandSender.sendMessage(ChatColor.RED + "Данная команда доступна только игрокам!");
            return true;
        }

        World world = ((Player) commandSender).getWorld();

        world.setStorm(true);
        commandSender.sendMessage(plugin.getMessageString("elytramix.rain")
                .replace("{world}", world.getName()));

        return true;
    }
}
