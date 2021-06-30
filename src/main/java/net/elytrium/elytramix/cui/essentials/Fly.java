package net.elytrium.elytramix.cui.essentials;

import net.elytrium.elytramix.Plugin;
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
                commandSender.sendMessage(Plugin.getInstance().getMessageString("elytramix.fly-disable"));
                return true;
            }
            p.setAllowFlight(true);
            commandSender.sendMessage(Plugin.getInstance().getMessageString("elytramix.fly-enable"));
        }


        if(strings.length >= 1) {
            if (strings[0].equals("*")){
                if(strings[1].equals("disable")){
                    commandSender.sendMessage(Plugin.getInstance().getMessageString("elytramix.fly-disable-multiple"));
                    Bukkit.getOnlinePlayers().forEach(player -> {
                        if(player != commandSender){
                            player.setAllowFlight(false);
                        }
                    });
                    return true;
                } else if(strings[1].equals("enable")){
                    commandSender.sendMessage(Plugin.getInstance().getMessageString("elytramix.fly-enable-multiple"));
                    Bukkit.getOnlinePlayers().forEach(player -> {
                        if(player != commandSender){
                            player.setAllowFlight(true);
                        }
                    });
                    return true;
                } else return false;
            } else {
                String name = strings[0];
                Player p = Bukkit.getPlayer(name);

                if(p == null){
                    commandSender.sendMessage(Plugin.getInstance().getMessageString("not-found"));
                    return true;
                }

                if(p.getAllowFlight()) {
                    p.setAllowFlight(false);
                    p.sendMessage(Plugin.getInstance().getMessageString("elytramix.fly-disable"));
                    return true;
                }
                p.setAllowFlight(true);
                p.sendMessage(Plugin.getInstance().getMessageString("elytramix.fly-enable"));
                return true;
            }
        }

        return true;
    }
}
