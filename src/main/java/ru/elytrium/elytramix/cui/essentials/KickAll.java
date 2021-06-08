package ru.elytrium.elytramix.cui.essentials;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KickAll implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        String reason = String.join(" ", strings);

        if(reason == null){ reason = "Вы были кикнуты с сервера! Не ведите себя плохо :)"; }

        reason = reason.replace("&", "\u00a7");

        for(Player p: Bukkit.getOnlinePlayers()){
            if(p != commandSender){
                p.kickPlayer(reason);
            }
        }

        commandSender.sendMessage("§8§l[§5§lElytrium§8§l] §7Всех игроков смело ветром...");

        return true;
    }
}
