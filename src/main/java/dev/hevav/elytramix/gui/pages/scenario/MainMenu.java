package dev.hevav.elytramix.gui.pages.scenario;

import dev.hevav.elytramix.gui.Menu;
import dev.hevav.elytramix.gui.MenuHandler;
import dev.hevav.elytramix.gui.MenuItem;
import dev.hevav.elytramix.scenarios.ScenarioManager;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryInteractEvent;

import java.util.List;
import java.util.stream.Collectors;

public class MainMenu extends Menu {
    public MainMenu() {
        super("Выберите тип сценариев", true, 1);
    }

    @Override
    public List<MenuItem> getItems() {
        return ScenarioManager.getInstance().getCategories().stream().map(category ->
            new MenuItem(
                category.getDisplayMaterial(),
                category.getDisplayName(),
                (e) -> MenuHandler.getInstance()
                    .getScenarioMenu(category.getName())
                    .open((Player) e.getWhoClicked(), true))
        ).collect(Collectors.toList());
    }

    @Override
    public void onClick(InventoryInteractEvent event) {

    }

}
