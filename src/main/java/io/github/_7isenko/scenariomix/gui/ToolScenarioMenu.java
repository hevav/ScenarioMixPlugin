package io.github._7isenko.scenariomix.gui;

import io.github._7isenko.scenariomix.scenarios.ScenarioManager;

public class ToolScenarioMenu extends ScenarioMenu {

    public ToolScenarioMenu() {
        name = "Доступные инструменты";
        scenarios = ScenarioManager.getInstance().getToolScenarios();
        inventory = createInventory(name, ScenarioManager.getInstance().getToolScenarios());
    }

    protected boolean switchScenario(int slot) {
        return ScenarioManager.getInstance().switchToolScenario(slot);
    }

}
