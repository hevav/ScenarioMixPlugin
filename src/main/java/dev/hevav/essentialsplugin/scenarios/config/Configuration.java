package dev.hevav.essentialsplugin.scenarios.config;

import dev.hevav.essentialsplugin.utils.ItemUtils;
import dev.hevav.essentialsplugin.scenarios.Scenario;
import dev.hevav.essentialsplugin.utils.Parser;
import org.bukkit.Material;

import java.util.Arrays;

public class Configuration<T> {
    private final String name;
    private final String[] description;
    private Material icon;
    private T value;
    private final Scenario scenario;

    public Configuration(String name, T defaultValue, String icon, Scenario scenario, String... description) {
        this.name = name;
        this.value = defaultValue;
        this.icon = ItemUtils.getMaterial(icon);
        this.scenario = scenario;
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public String[] getDescription() {
        return this.description;
    }

    public Material getIcon() {
        return this.icon;
    }

    public void setIcon(Material icon) {
        this.icon = icon;
    }

    public T value() {
        return value;
    }

    public void setStringValue(String string) throws IllegalArgumentException {
        ValueType type = getValueType();
        String[] strings = new String[]{string};

        if (isArray())
            strings = string.split(",");

        switch (type) {
            case STRING:
                setValue(strings);
                break;
            case BOOLEAN:
                setValue(Arrays.stream(strings).map(Parser::parseBoolean).toArray());
                break;
            case INTEGER:
                setValue(Arrays.stream(strings).map(Integer::parseInt).toArray());
                break;
            case MATERIAL:
                setValue(Arrays.stream(strings).map(Parser::parseMaterial).toArray());
                break;
            default:
                throw new IllegalArgumentException("Такой тип данных не поддерживается.");
        }
    }

    public T getValue() {
        return value;
    }

    public ValueType getValueType() {
        Object checkValue = value;

        if (checkValue instanceof Integer)
            return ValueType.INTEGER;
        else if (checkValue instanceof Boolean)
            return ValueType.BOOLEAN;
        else if (checkValue instanceof Material)
            return ValueType.MATERIAL;
        else if (checkValue instanceof String)
            return ValueType.STRING;
        else return ValueType.UNKNOWN;
    }

    public boolean isArray() {
        return value.getClass().isArray();
    }

    public void setValue(Object value) {
        if (isArray())
            this.value = (T) value;
        else
            this.value = ((T[]) value)[0];
    }

    public Scenario getScenario() {
        return scenario;
    }

}
