package ru.elytrium.elytramix.cui.essentials;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.elytrium.elytramix.Plugin;

public class Heal implements CommandExecutor {
    private Plugin plugin;

    public Heal(Plugin plugin){ this.plugin = plugin; }

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
            commandSender.sendMessage(plugin.getMessageString("elytramix.heal-healed"));
        }
        if(strings.length == 1) {
            if(strings[0].equals("*")){
                for(Player p:Bukkit.getOnlinePlayers()){
                    p.setHealth(20);
                    p.setFoodLevel(20);
                    p.sendMessage(plugin.getMessageString("elytramix.heal-healed"));
                }
            } else {
                Player p = Bukkit.getPlayer(strings[0]);
                if(p == null) {
                    commandSender.sendMessage(plugin.getMessageString("not-found"));
                    return true;
                }
                p.setHealth(20);
                p.setFoodLevel(20);
                commandSender.sendMessage(plugin.getMessageString("elytramix.heal-healed-target")
                        .replace("{player}", p.getName()));
                p.sendMessage(plugin.getMessageString("elytramix.heal-healed"));
            }
        }

        return true;
    }
}
