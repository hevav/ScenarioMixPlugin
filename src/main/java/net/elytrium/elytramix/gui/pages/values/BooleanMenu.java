package net.elytrium.elytramix.gui.pages.values;

import net.elytrium.elytramix.gui.Menu;
import net.elytrium.elytramix.gui.MenuItem;
import net.elytrium.elytramix.scenarios.Scenario;
import net.elytrium.elytramix.scenarios.config.Configuration;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BooleanMenu extends Menu {
    private Menu previous;
    private Configuration config;


    public BooleanMenu(Scenario scenario, Menu previousMenu, String parameter) {
        super("Выберите значение", true, 0);

        this.previous = previousMenu;
        this.config = scenario.getConfig(parameter);
    }

    @Override
    public List<MenuItem> getItems(Player player) {
        List<MenuItem> values = new ArrayList<>();

        values.add(new MenuItem(booleanItem(true), e -> {
            config.setStringValue(String.valueOf(true));
            this.previous.open(player);
        }));
        values.add(new MenuItem(new ItemStack(Material.AIR), null)); //Empty space between items
        values.add(new MenuItem(booleanItem(false), e -> {
            config.setStringValue(String.valueOf(false));
            this.previous.open(player);
        }));

        return values;
    }

    private ItemStack booleanItem(Boolean bool){
        ItemStack value;

        if(bool){ value = new ItemStack(Material.GREEN_SHULKER_BOX); }
        else { value = new ItemStack(Material.RED_SHULKER_BOX); }

        ItemMeta valueMeta = value.getItemMeta();

        String boolStr = String.valueOf(bool).substring(0, 1).toUpperCase()+String.valueOf(bool).substring(1);

        valueMeta.setDisplayName("§a"+boolStr);

        ArrayList<String> lore = new ArrayList<>();
        lore.add("Установить значение "+boolStr);
        valueMeta.setLore(lore);
        value.setItemMeta(valueMeta);
        return value;
    }

    @Override
    public void onClick(InventoryInteractEvent event) {

    }
}
