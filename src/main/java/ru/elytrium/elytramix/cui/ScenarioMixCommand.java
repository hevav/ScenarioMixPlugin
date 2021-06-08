package ru.elytrium.elytramix.cui;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.elytrium.elytramix.gui.MenuHandler;
import ru.elytrium.elytramix.scenarios.Scenario;
import ru.elytrium.elytramix.scenarios.ScenarioManager;
import ru.elytrium.elytramix.scenarios.config.Configuration;
import ru.elytrium.elytramix.utils.Parser;

@SuppressWarnings("rawtypes")
public class ScenarioMixCommand implements CommandExecutor {
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (strings.length == 0) {
            if (commandSender instanceof Player) {
                MenuHandler.getInstance().getMainMenu().open(((Player) commandSender), false);
            } else commandSender.sendMessage(ChatColor.RED + "Эта команда доступна только для игроков");
            return true;
        }

        Scenario scenario = ScenarioManager.getInstance().getAnyScenario(strings[0]);

        if (strings.length == 1) {
            if (checkScenario(scenario, commandSender))
                sendConfigsNames(scenario, commandSender);
            return true;
        }

        if (strings.length == 2) {
            if (checkScenario(scenario, commandSender)) {
                Configuration configuration = scenario.getConfig(strings[1]);
                if (configuration != null) {
                    commandSender.sendMessage("Введите " + Parser.getConfigCommand(configuration));
                } else {
                    sendConfigsNames(scenario, commandSender);
                }
            }
            return true;
        }

        if (strings.length == 3) {
            if (checkScenario(scenario, commandSender)) {
                Configuration configuration = scenario.getConfig(strings[1]);
                if (configuration != null) {
                    try {
                        configuration.setStringValue(strings[2]);
                        commandSender.sendMessage(ChatColor.BLUE + "Теперь значение " + ChatColor.GOLD + configuration.getName() + ChatColor.BLUE + " у " + ChatColor.GOLD + scenario.getName() + ChatColor.BLUE + " равно " + ChatColor.BOLD + ChatColor.WHITE + configuration.value().toString());
                    } catch (IllegalArgumentException e) {
                        commandSender.sendMessage("Введите " + Parser.getConfigCommand(configuration));
                    } catch (Exception e) {
                        commandSender.sendMessage(ChatColor.RED + e.getMessage());
                    }
                } else {
                    sendConfigsNames(scenario, commandSender);
                }
            }
            return true;
        }

        return false;
    }

    private boolean checkScenario(Scenario scenario, CommandSender commandSender) {
        if (scenario != null) {
            if (!scenario.isConfigurable()) {
                commandSender.sendMessage(ChatColor.RED + "У этого сценария нет возможных конфигураций");
                return false;
            } else
                return true;
        }
        commandSender.sendMessage(ChatColor.RED + "Вы ошиблись в названии сценария");
        sendScenariosNames(commandSender);
        return false;
    }

    private void sendScenariosNames(CommandSender commandSender) {
        commandSender.sendMessage(ChatColor.BLUE + "Список сценариев с конфигурациями:");
        for (Scenario scenario1 : ScenarioManager.getInstance().getConfigurableScenarios()) {
            String string = ChatColor.GRAY + scenario1.getConfigName() + " - " + ChatColor.YELLOW + scenario1.getName() + ": " + String.join(" ", scenario1.getDescription());
            commandSender.sendMessage(string);
        }
    }

    private void sendConfigsNames(Scenario scenario, CommandSender commandSender) {
        commandSender.sendMessage(ChatColor.BLUE + "Возможные параметры: " + String.join(", ", scenario.getConfigStrings()));
    }
}