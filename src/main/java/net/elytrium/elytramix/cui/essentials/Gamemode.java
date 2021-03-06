package net.elytrium.elytramix.cui.essentials;

import net.elytrium.elytramix.Plugin;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Gamemode implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(strings.length == 0) {
            return false;
        }

        if(strings.length == 1) {
            if(!(commandSender instanceof Player)) {
                commandSender.sendMessage(ChatColor.RED + "Данная команда доступна только игрокам!");
                return true;
            }

            changeMode((Player) commandSender, strings[0]);
            return true;
        }


        else if(strings.length == 2) {
            if(!strings[0].equals("0") && !strings[0].equals("1")
            && !strings[0].equals("2") && !strings[0].equals("3")){ return false; }

            Player target = Bukkit.getPlayer(strings[1]);

            if(strings[1].equals("*")){
                commandSender.sendMessage(Plugin.getInstance().getMessageString("elytramix.gamemode-change-multiple"));
                Bukkit.getOnlinePlayers().forEach(player -> {
                    if(player != commandSender){
                        changeMode(player, strings[0]);
                    }
                });
                return true;
            } else {
                if(target == null){
                    commandSender.sendMessage(Plugin.getInstance().getMessageString("not-found"));
                    return true;
                } else{
                    changeMode(target, strings[0]);
                    return true;
                }
            }
        }
        return true;
    }

    private void changeMode(Player player, String mode){
        switch (mode) {
            case "1":
            case "creative":
                player.setGameMode(GameMode.CREATIVE);
                player.sendMessage(Plugin.getInstance().getMessageString("elytramix.gamemode-change")
                        .replace("{mode}", GameMode.CREATIVE.name()));
                break;
            case "0":
            case "survival":
                player.setGameMode(GameMode.SURVIVAL);
                player.sendMessage(Plugin.getInstance().getMessageString("elytramix.gamemode-change")
                        .replace("{mode}", GameMode.SURVIVAL.name()));
                break;
            case "2":
            case "adventure":
                player.setGameMode(GameMode.ADVENTURE);
                player.sendMessage(Plugin.getInstance().getMessageString("elytramix.gamemode-change")
                        .replace("{mode}", GameMode.ADVENTURE.name()));
                break;
            case "3":
            case "spectator":
                player.setGameMode(GameMode.SPECTATOR);
                player.sendMessage(Plugin.getInstance().getMessageString("elytramix.gamemode-change")
                        .replace("{mode}", GameMode.SPECTATOR.name()));
                break;
            default:
                player.sendMessage("§8§l[§5§lElytrium§8§l] §cНеизвестный игровой режим!");
                break;
        }
    }
}
