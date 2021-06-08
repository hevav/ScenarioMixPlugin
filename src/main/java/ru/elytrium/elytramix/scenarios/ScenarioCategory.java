package ru.elytrium.elytramix.scenarios;

import org.bukkit.Material;

import java.util.HashMap;
import java.util.Map;

public class ScenarioCategory {
    private final String name;
    private final String displayName;
    private final Material displayMaterial;
    private final Map<String, Scenario> scenarios;

    public ScenarioCategory(String name, String displayName, Material displayMaterial) {
        this.name = name;
        this.displayName = displayName;
        this.displayMaterial = displayMaterial;
        this.scenarios = new HashMap<>();
    }

    public final String getName() {
        return name;
    }

    public final String getDisplayName() {
        return displayName;
    }

    public final Material getDisplayMaterial() {
        return displayMaterial;
    }

    public final Map<String, Scenario> getScenarios() {
        return scenarios;
    }

    public void addScenario(Scenario scenario) {
        scenarios.put(scenario.getName(), scenario);
    }
}
