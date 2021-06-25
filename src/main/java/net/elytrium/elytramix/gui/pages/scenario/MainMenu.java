package net.elytrium.elytramix.gui.pages.scenario;

import net.elytrium.elytramix.gui.Menu;
import net.elytrium.elytramix.gui.MenuHandler;
import net.elytrium.elytramix.gui.MenuItem;
import net.elytrium.elytramix.scenarios.ScenarioManager;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryInteractEvent;

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
                category.getName(),
                (e) -> MenuHandler.getInstance()
                    .getScenarioMenu(category.getName())
                    .open((Player) e.getWhoClicked()))
        ).collect(Collectors.toList());
    }

    @Override
    public void onClick(InventoryInteractEvent event) {

    }

}
