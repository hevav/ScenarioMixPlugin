package ru.elytrium.elytramix.scenarios;

import java.util.*;
import java.util.stream.Collectors;

public class ScenarioManager {
    private static ScenarioManager instance;
    private final Map<String, ScenarioCategory> scenarioCategories;

    public void addCategory(ScenarioCategory category) {
        scenarioCategories.put(category.getName(), category);
    }

    public Collection<Scenario> getScenarios(String category) {
        return scenarioCategories.get(category).getScenarios().values();
    }

    public Scenario getAnyScenario(String name) {
        for (Scenario scenario : getAllScenarios()) {
            if (scenario.getConfigName().equalsIgnoreCase(name)) {
                return scenario;
            }
        }
        return null;
    }

    public Collection<Scenario> getAllScenarios() {
        Collection<Scenario> scenarios = new HashSet<>();
        scenarioCategories.values().forEach(category -> scenarios.addAll(category.getScenarios().values()));
        return scenarios;
    }

    public Collection<ScenarioCategory> getCategories() {
        return scenarioCategories.values();
    }

    public List<Scenario> getConfigurableScenarios() {
        return getAllScenarios().stream()
                .filter(Scenario::isConfigurable)
                .collect(Collectors.toList());
    }

    public List<String> getAllScenariosNames() {
        return getAllScenarios().stream()
                .map(Scenario::getConfigName)
                .collect(Collectors.toList());
    }

    public List<String> getAllConfigurableScenariosNames() {
        return getAllScenarios().stream()
                .filter(Scenario::isConfigurable)
                .map(Scenario::getConfigName)
                .collect(Collectors.toList());
    }

    private ScenarioManager() {
        this.scenarioCategories = new HashMap<>();
    }

    public static ScenarioManager getInstance() {
        if (instance == null)
            instance = new ScenarioManager();
        return instance;
    }

    public void disableAll() {
        scenarioCategories.values().forEach(category -> category.getScenarios().values().forEach(Scenario::disable));
    }
}
