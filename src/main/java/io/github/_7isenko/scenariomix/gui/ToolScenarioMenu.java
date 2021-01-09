package io.github._7isenko.scenariomix.gui;

import io.github._7isenko.scenariomix.scenarios.ScenarioManager;

public class ToolScenarioMenu extends ScenarioMenu {

    public ToolScenarioMenu() {
        name = "Доступные инструменты";
        scenarios = ScenarioManager.getInstance().getToolScenarios();
        inventory = createInventory(name, ScenarioManager.getInstance().getToolScenarios());

        // Эта строчка тут не нужна. На нас уже работает наследуемый Listener.
        // Bukkit.getPluginManager().registerEvents(new InventoryListener(), ScenarioMix.plugin);
    }

    protected boolean switchScenario(int slot) {
        return ScenarioManager.getInstance().switchToolScenario(slot);
    }

}
