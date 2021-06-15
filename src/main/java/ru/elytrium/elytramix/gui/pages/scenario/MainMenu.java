package ru.elytrium.elytramix.gui.pages.scenario;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryInteractEvent;
import ru.elytrium.elytramix.gui.Menu;
import ru.elytrium.elytramix.gui.MenuHandler;
import ru.elytrium.elytramix.gui.MenuItem;
import ru.elytrium.elytramix.scenarios.ScenarioManager;

import java.util.List;
import java.util.stream.Collectors;

public class MainMenu extends Menu {
    public MainMenu() {
        super("Выберите тип сценариев", true, 1);
    }

    @Override
    public List<MenuItem> getItems(Player player) {
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
