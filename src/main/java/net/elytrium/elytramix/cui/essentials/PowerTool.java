package net.elytrium.elytramix.cui.essentials;

import net.elytrium.elytramix.Plugin;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;
import java.util.HashMap;

public class PowerTool implements CommandExecutor {

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
            Plugin.getInstance().getPowertoolConfig().set(uuid+"."+itemID, null);
            savePowertool();
            commandSender.sendMessage(Plugin.getInstance().getMessageString("elytramix.powertool-clean")
                    .replace("{item}", itemID));
            return true;
        }

        if(!Plugin.getInstance().getPowertoolConfig().contains(uuid)){
            createColumn(toolCommand, itemStack, player);
        } else if(!Plugin.getInstance().getPowertoolConfig().contains(uuid+"."+itemID)){
            Plugin.getInstance().getPowertoolConfig().set(uuid+"."+itemID, toolCommand);
            savePowertool();
        } else if(Plugin.getInstance().getPowertoolConfig().contains(uuid+"."+itemID)){
            Plugin.getInstance().getPowertoolConfig().set(uuid+"."+itemID, toolCommand);
            savePowertool();
        }

        commandSender.sendMessage(Plugin.getInstance().getMessageString("elytramix.powertool-bind")
                .replace("{item}", itemID));

        return true;
    }

    private void createColumn(String toolCommand, ItemStack itemStack, Player player){
        HashMap<String, String> tool = new HashMap<>();
        tool.put(itemStack.getType().name(), toolCommand);

        Plugin.getInstance().getPowertoolConfig().set(player.getUniqueId().toString(), tool);
        savePowertool();
    }

    private void savePowertool(){
        try {
            Plugin.getInstance().getPowertoolConfig().save(Plugin.getInstance().powertoolFile);
            Plugin.getInstance().createConfigs(); //Reloading config
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
