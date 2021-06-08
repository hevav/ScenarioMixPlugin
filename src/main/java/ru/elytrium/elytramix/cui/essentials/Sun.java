package ru.elytrium.elytramix.cui.essentials;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Sun implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender instanceof Player)) {
            commandSender.sendMessage(ChatColor.RED + "Данная команда доступна только игрокам!");
            return true;
        }

        World world = ((Player) commandSender).getWorld();

        world.setStorm(false);
        commandSender.sendMessage("§8§l[§5§lElytrium§8§l] §7Установлена §cсолнечная " +
                "§7погода в мире §c"+world.getName());

        return true;
    }
}
