package dev.hevav.essentialsplugin.cui;

import dev.hevav.essentialsplugin.scenarios.Scenario;
import dev.hevav.essentialsplugin.scenarios.ScenarioManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ConfigurationTabCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            return filter(ScenarioManager.getInstance().getAllConfigurableScenariosNames(), args[0]);
        }

        if (args.length == 2) {
            Scenario scenario = ScenarioManager.getInstance().getAnyScenario(args[0]);
            if (scenario != null && scenario.isConfigurable()) {
                return filter(new ArrayList<>(scenario.getConfigStrings()), args[1]);
            }
            return null;
        }

        return null;
    }

    public List<String> filter(List<String> strings, String word) {
        if (word.equals(""))
            return strings;
        List<String> filtered = strings.stream().filter(s -> s.startsWith(word)).collect(Collectors.toList());
        if (filtered.isEmpty())
            return filter(strings, word.substring(0, word.length() - 1));
        else return filtered;
    }
}
