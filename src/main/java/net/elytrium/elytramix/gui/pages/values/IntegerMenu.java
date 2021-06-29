package net.elytrium.elytramix.gui.pages.values;

import net.elytrium.elytramix.Plugin;
import net.elytrium.elytramix.gui.Menu;
import net.elytrium.elytramix.gui.MenuItem;
import net.elytrium.elytramix.scenarios.Scenario;
import net.elytrium.elytramix.scenarios.config.Configuration;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class IntegerMenu extends Menu {
    private Menu previous;
    private Configuration config;

    public IntegerMenu(Scenario scenario, Menu previousMenu, String parameter) {
        super("Выберите значение", true, 0);

        this.previous = previousMenu;
        this.config = scenario.getConfig(parameter);
    }
    @Override
    public List<MenuItem> getItems(Player player) {
        FileConfiguration pluginConfig = Plugin.getInstance().getConfig();
        List<Integer> defaults = pluginConfig.getIntegerList("default-values.integers");

        return defaults.stream()
                .map(integer -> new MenuItem(integerItem(integer), e -> {
                    config.setStringValue(String.valueOf(integer));
                    this.previous.open(player);
                }))
                .collect(Collectors.toList());
    }

    private ItemStack integerItem(int integer){
        ItemStack value = new ItemStack(Material.GOLD_NUGGET);

        ItemMeta valueMeta = value.getItemMeta();
        valueMeta.setDisplayName(String.valueOf(integer));

        ArrayList<String> lore = new ArrayList<>();
        lore.add("Установить значение §l§e"+String.valueOf(integer));
        valueMeta.setLore(lore);
        value.setItemMeta(valueMeta);

        return value;
    }

    @Override
    public void onClick(InventoryInteractEvent event) {

    }
}
