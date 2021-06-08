package dev.hevav.elytramix.cui.essentials;

import dev.hevav.elytramix.Plugin;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PowerTool implements CommandExecutor {

    private Plugin plugin;

    public PowerTool(Plugin plugin){ this.plugin = plugin; }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender instanceof Player)) {
            commandSender.sendMessage(ChatColor.RED + "Данная команда доступна только игрокам!");
            return true;
        }

        Player player = (Player) commandSender;
        ItemStack itemStack = player.getItemInHand();

        String itemID = itemStack.getType().name();
        String uuid = player.getUniqueId().toString();

        String toolCommand = String.join(" ", strings);

        if(strings.length == 0){
            plugin.powertoolData().set(uuid+"."+itemID, null);
            savePowertool();
            commandSender.sendMessage("§8§l[§5§lElytrium§8§l] §7Все команды были сняты с предмета §c"+itemID);
            return true;
        }

        if(!plugin.powertoolData().contains(uuid)){
            createColumn(toolCommand, itemStack, player);
            commandSender.sendMessage("Создана новая ячейка");
        } else if(!plugin.powertoolData().contains(uuid+"."+itemID)){
            plugin.powertoolData().set(uuid+"."+itemID, toolCommand);
            commandSender.sendMessage("Данные созданы");
            savePowertool();
        } else if(plugin.powertoolData().contains(uuid+"."+itemID)){
            plugin.powertoolData().set(uuid+"."+itemID, toolCommand);
            commandSender.sendMessage("Данные перезаписаны");
            savePowertool();
        }

        commandSender.sendMessage("§8§l[§5§lElytrium§8§l] §7Команда §c"+toolCommand+" " +
                "§7была установлена на предмет §c"+itemStack.getType().name());

        return true;
    }

    private void createColumn(String toolCommand, ItemStack itemStack, Player player){
        HashMap<String, String> tool = new HashMap<>();
        tool.put(itemStack.getType().name(), toolCommand);

        plugin.powertoolData().set(player.getUniqueId().toString(), tool);
        savePowertool();
    }

    private void savePowertool(){
        try {
            plugin.powertoolData().save(plugin.powertoolFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
