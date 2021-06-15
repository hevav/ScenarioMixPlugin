package ru.elytrium.elytramix.cui.essentials;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.elytrium.elytramix.Plugin;

public class Fly implements CommandExecutor {
    private final Plugin plugin;

    public Fly(Plugin plugin){ this.plugin = plugin; }

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
                commandSender.sendMessage(plugin.getMessageString("elytramix.fly-disable"));
                return true;
            }
            p.setAllowFlight(true);
            commandSender.sendMessage(plugin.getMessageString("elytramix.fly-enable"));
        }


        if(strings.length >= 1) {
            if (strings[0].equals("*")){
                if(strings[1].equals("disable")){
                    commandSender.sendMessage(plugin.getMessageString("elytramix.fly-disable-multiple"));
                    for (Player p : Bukkit.getOnlinePlayers()){
                        if(p != commandSender){
                            p.setAllowFlight(false);
                        }
                    }
                    return true;
                } else if(strings[1].equals("enable")){
                    commandSender.sendMessage(plugin.getMessageString("elytramix.fly-enable-multiple"));
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
                    commandSender.sendMessage(plugin.getMessageString("not-found"));
                    return true;
                }

                if(p.getAllowFlight()) {
                    p.setAllowFlight(false);
                    p.sendMessage(plugin.getMessageString("elytramix.fly-disable"));
                    return true;
                }
                p.setAllowFlight(true);
                p.sendMessage(plugin.getMessageString("elytramix.fly-enable"));
                return true;
            }
        }

        return true;
    }
}
