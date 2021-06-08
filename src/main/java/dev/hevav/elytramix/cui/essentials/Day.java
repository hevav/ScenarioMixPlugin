package dev.hevav.elytramix.cui.essentials;

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

        p.sendMessage(ChatColor.GRAY+"§8§l[§5§lElytrium§8§l] Время изменено на §c0 §7тиков в измерении §c"+world.getName());

        return true;
    }
}