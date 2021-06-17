package net.elytrium.elytramix.cui;

import net.elytrium.elytramix.Plugin;
import net.elytrium.elytramix.gui.MenuHandler;
import net.elytrium.elytramix.scenarios.Scenario;
import net.elytrium.elytramix.scenarios.ScenarioManager;
import net.elytrium.elytramix.scenarios.config.Configuration;
import net.elytrium.elytramix.utils.Parser;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@SuppressWarnings("rawtypes")
public class ScenarioMixCommand implements CommandExecutor {
    private final Plugin plugin;

    public ScenarioMixCommand(Plugin plugin){ this.plugin = plugin; }

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (strings.length == 0) {
            if (commandSender instanceof Player) {
                MenuHandler.getInstance().getMainMenu().open(((Player) commandSender));
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
                    commandSender.sendMessage(plugin.getMessageString("scenariomix.unknown-arg")
                            .replace("{command}", Parser.getConfigCommand(configuration)));
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
                        commandSender.sendMessage(plugin.getMessageString("scenariomix.config-edit")
                                .replace("{scenario}", scenario.getName())
                                .replace("{value}", configuration.value().toString()));
                    } catch (IllegalArgumentException e) {
                        commandSender.sendMessage(plugin.getMessageString("scenariomix.unknown-arg")
                                .replace("{command}", Parser.getConfigCommand(configuration)));
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
                commandSender.sendMessage(plugin.getMessageString("scenariomix.no-configuration"));
                return false;
            } else
                return true;
        }
        commandSender.sendMessage(plugin.getMessageString("scenariomix.unknown-scenario"));
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
