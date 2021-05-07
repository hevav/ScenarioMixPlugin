package dev.hevav.essentialsplugin.gui;

import dev.hevav.essentialsplugin.gui.pages.scenario.MainMenu;
import dev.hevav.essentialsplugin.gui.pages.scenario.ScenarioMenu;
import dev.hevav.essentialsplugin.scenarios.ScenarioManager;

import java.util.HashMap;

public class MenuHandler {
    private static MenuHandler instance;

    private final Menu mainMenu;
    private final HashMap<String, Menu> scenarioMenus = new HashMap<>();

    private MenuHandler() {
        this.mainMenu = new MainMenu();
        ScenarioManager scenarioManager = ScenarioManager.getInstance();
        scenarioManager.getCategories().forEach(
                category -> scenarioMenus.put(category.getName(), new ScenarioMenu(category.getName()))
        );
    }

    public Menu getMainMenu() {
        return mainMenu;
    }

    public Menu getScenarioMenu(String category) {
        return scenarioMenus.get(category);
    }

    public static MenuHandler getInstance() {
        if (instance == null)
            instance = new MenuHandler();
        return instance;
    }
}