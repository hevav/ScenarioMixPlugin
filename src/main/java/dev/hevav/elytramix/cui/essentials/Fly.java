package dev.hevav.elytramix.cui.essentials;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Fly implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(strings.length == 0) {
            if(!(commandSender instanceof Player)) {
                commandSender.sendMessage(ChatColor.RED+"Данная команда доступна только игрокам!");
                return true;
            }
            Player p = (Player) commandSender;
            if(p.getAllowFlight()) {
                p.setAllowFlight(false);
                commandSender.sendMessage("§8§l[§5§lElytrium§8§l] §7Режим полёта §cвыключен§7!");
                return true;
            }
            p.setAllowFlight(true);
            commandSender.sendMessage("§8§l[§5§lElytrium§8§l] §7Режим полёта §cвключен§7!");
        }


        if(strings.length >= 1) {
            if (strings[0].equals("*")){
                if(strings[1].equals("disable")){
                    commandSender.sendMessage("§8§l[§5§lElytrium§8§l] §7Режим полёта у всех игроков §cвыключен§7!");
                    for (Player p : Bukkit.getOnlinePlayers()){
                        if(p != commandSender){
                            p.setAllowFlight(false);
                        }
                    }
                    return true;
                } else if(strings[1].equals("enable")){
                    commandSender.sendMessage("§8§l[§5§lElytrium§8§l] §7Режим полёта у всех игроков §cвключен§7!");
                    for (Player p : Bukkit.getOnlinePlayers()){
                        if(p != commandSender){
                            p.setAllowFlight(true);
                        }
                    }
                    return true;
                } else {
                    return false;
                }
            } else {
                String name = strings[0];
                Player p = Bukkit.getPlayer(name);

                if(p == null){
                    commandSender.sendMessage(ChatColor.RED+"Игрок "+name+" оффлайн!");
                    return true;
                }

                if(p.getAllowFlight()) {
                    p.setAllowFlight(false);
                    commandSender.sendMessage("§8§l[§5§lElytrium§8§l] §7Режим полёта для игрока §f"+name+" §cвыключен§7!");
                    p.sendMessage("§8§l[§5§lElytrium§8§l] §7Режим полёта §cвыключен§7!");
                    return true;
                }
                p.setAllowFlight(true);
                commandSender.sendMessage("§8§l[§5§lElytrium§8§l] §7Режим полёта для игрока §f"+name+" §cвключен§7!");
                p.sendMessage("§8§l[§5§lElytrium§8§l] §7Режим полёта §cвключен§7!");
                return true;
            }
        }

        return true;
    }
}
