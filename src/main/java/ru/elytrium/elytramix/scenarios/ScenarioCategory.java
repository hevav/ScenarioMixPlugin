package ru.elytrium.elytramix.scenarios;

import org.bukkit.Material;

import java.util.HashMap;
import java.util.Map;

public class ScenarioCategory {
    private final String name;
    private final Material displayMaterial;
    private final Map<String, Scenario> scenarios;

    public ScenarioCategory(String name, Material displayMaterial) {
        this.name = name;
        this.displayMaterial = displayMaterial;
        this.scenarios = new HashMap<>();
    }

    public final String getName() {
        return name;
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
