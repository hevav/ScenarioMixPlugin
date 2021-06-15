package ru.elytrium.elytramix.utils;

import org.bukkit.Material;
import ru.elytrium.elytramix.Plugin;
import ru.elytrium.elytramix.scenarios.config.Configuration;

import java.util.NoSuchElementException;

public class Parser {
    public static Boolean parseBoolean(String string) throws IllegalArgumentException {
        if (string.equalsIgnoreCase("true") || string.equalsIgnoreCase("allow") || string.equalsIgnoreCase("yes") || string.equalsIgnoreCase("вкл"))
            return true;
        if (string.equalsIgnoreCase("false") || string.equalsIgnoreCase("none") || string.equalsIgnoreCase("deny") || string.equalsIgnoreCase("no") || string.equalsIgnoreCase("выкл"))
            return false;
        throw new IllegalArgumentException(string);
    }

    public static Material parseMaterial(String string) throws NoSuchElementException {
        Material material = ItemUtils.getMaterial(string.toUpperCase());
        if (material != null) return material;
        else throw new NoSuchElementException(string);
    }

    @SuppressWarnings("rawtypes")
    public static String getConfigCommand(Configuration configuration) {
        return "/" + Plugin.getCommand() + " " + configuration.getScenario().getConfigName() + " " + configuration.getName() + " <" + createTypeMessage(configuration) + ">";
    }

    @SuppressWarnings("rawtypes")
    public static String createTypeMessage(Configuration configuration) {
        switch (configuration.getValueType()) {
            case INTEGER:
                return Plugin.getInstance().getMessageString("scenariomix.args.integer");
            case BOOLEAN:
                return Plugin.getInstance().getMessageString("scenariomix.args.boolean");
            case MATERIAL:
                return Plugin.getInstance().getMessageString("scenariomix.args.material");
            case STRING:
                return Plugin.getInstance().getMessageString("scenariomix.args.string");
            default:
                return Plugin.getInstance().getMessageString("scenariomix.args.default");
        }
    }
}
