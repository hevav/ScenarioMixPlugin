package net.elytrium.elytramix.scenarios.commands.whitelist;

import net.elytrium.elytramix.Plugin;
import net.elytrium.elytramix.scenarios.Scenario;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Whitelist extends Scenario {
    public Whitelist(){
        super("Белый список", "whitelist_toggle", "PAPER", "fast-command", "Переключает", "состояние белого списка");
    }

    @Override
    public void start(Player player) {
        boolean enabled = Bukkit.hasWhitelist();

        if(enabled){
            Bukkit.setWhitelist(false);
            player.sendMessage(Plugin.getInstance().getPrefixString()+"Белый список §cвыключен");
        } else {
            Bukkit.setWhitelist(true);
            player.sendMessage(Plugin.getInstance().getPrefixString()+"Белый список §cвключен");
        }
    }

    @Override
    public void stop() {

    }
}
