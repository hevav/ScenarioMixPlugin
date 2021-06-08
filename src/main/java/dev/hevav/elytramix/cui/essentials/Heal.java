package dev.hevav.elytramix.cui.essentials;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Heal implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender,  Command command, String s, String[] strings) {

        if(strings.length == 0) {
            if(!(commandSender instanceof Player)) {
                commandSender.sendMessage(ChatColor.RED + "Данная команда доступна только игрокам!");
                return true;
            }
            Player p = (Player) commandSender;
            p.setHealth(20);
            p.setFoodLevel(20);
            commandSender.sendMessage("§8§l[§5§lElytrium§8§l] §6Вы были исцелены");
        }
        if(strings.length == 1) {
            if(strings[0].equals("*")){
                commandSender.sendMessage("§8§l[§5§lElytrium§8§l] §7Вы исцелили всех игроков!");
                for(Player p:Bukkit.getOnlinePlayers()){
                    p.setHealth(20);
                    p.setFoodLevel(20);
                    p.sendMessage("§8§l[§5§lElytrium§8§l] §6Вы были исцелены");
                }
            } else {
                Player p = Bukkit.getPlayer(strings[0]);
                if(p == null) {
                    commandSender.sendMessage("§8§l[§5§lElytrium§8§l] §7Игрок §c"+strings[0]+"§7не найден!");
                    return true;
                }
                p.setHealth(20);
                p.setFoodLevel(20);
                commandSender.sendMessage("§8§l[§5§lElytrium§8§l] §7Игрок §c"+p.getName()+" §7был исцелён");
                p.sendMessage("§8§l[§5§lElytrium§8§l] §6Вы были исцелены");
            }
        }

        return true;
    }
}
