package net.elytrium.elytramix.cui.essentials;

import net.elytrium.elytramix.Plugin;
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
            commandSender.sendMessage(Plugin.getInstance().getMessageString("elytramix.heal-healed"));
        }
        if(strings.length == 1) {
            if(strings[0].equals("*")){
                Bukkit.getOnlinePlayers().forEach(player -> {
                    player.setHealth(20);
                    player.setFoodLevel(20);
                    player.sendMessage(Plugin.getInstance().getMessageString("elytramix.heal-healed"));
                });
            } else {
                Player p = Bukkit.getPlayer(strings[0]);
                if(p == null) {
                    commandSender.sendMessage(Plugin.getInstance().getMessageString("not-found"));
                    return true;
                }
                p.setHealth(20);
                p.setFoodLevel(20);
                commandSender.sendMessage(Plugin.getInstance().getMessageString("elytramix.heal-healed-target")
                        .replace("{player}", p.getName()));
                p.sendMessage(Plugin.getInstance().getMessageString("elytramix.heal-healed"));
            }
        }

        return true;
    }
}
