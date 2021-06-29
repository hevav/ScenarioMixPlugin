package net.elytrium.elytramix.gui.pages.scenario;

import net.elytrium.elytramix.Plugin;
import net.elytrium.elytramix.gui.Menu;
import net.elytrium.elytramix.gui.MenuItem;
import net.elytrium.elytramix.gui.pages.values.BooleanMenu;
import net.elytrium.elytramix.gui.pages.values.IntegerMenu;
import net.elytrium.elytramix.scenarios.Scenario;
import net.elytrium.elytramix.scenarios.config.Configuration;
import net.elytrium.elytramix.scenarios.config.ValueType;
import net.elytrium.elytramix.utils.Parser;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ConfigurationsMenu extends Menu {
    private final Map<String, Configuration> configs;
    private final Menu previous;
    private final Scenario scenario;
    public final Menu instance = this;

    public ConfigurationsMenu(Scenario scenario, Menu previousMenu) {
        super(String.format("Конфигурация для сценария \"%s\"", scenario.getName()), false, 0);
        this.previous = previousMenu;
        this.scenario = scenario;
        configs = scenario.getConfigs();
    }
    @Override
    public List<MenuItem> getItems(Player player) {
        return scenario.getConfigs().values().stream().map((config) -> {
            ItemStack item = new ItemStack(config.getIcon());
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(ChatColor.YELLOW + config.getName());
            ArrayList<String> lore = new ArrayList<>(Arrays.asList(config.getDescription()));
            lore.add(ChatColor.YELLOW + "Текущее значение:");
            if (config.isArray())
                for (Object value : ((Object[]) config.getValue()))
                    lore.add(ChatColor.BLUE + value.toString());
            else
                lore.add(ChatColor.BLUE + config.getValue().toString());
            lore.add(ChatColor.GRAY + Parser.getConfigCommand(config));
            meta.setLore(lore);
            item.setItemMeta(meta);
            return new MenuItem(item, (e) -> onClickItem(e, config));
        }).collect(Collectors.toList());
    }

    private void onClickItem(InventoryClickEvent event, Configuration config) {
        HumanEntity player = event.getWhoClicked();

        if(config.getValueType() == ValueType.INTEGER){
            new IntegerMenu(scenario, instance, config.getName()).open((Player) player);
        } else if(config.getValueType() == ValueType.BOOLEAN){
            new BooleanMenu(scenario, instance, config.getName()).open((Player) player);
        } else {
            player.sendMessage(ChatColor.AQUA + "Для смены параметра введите следующую команду (кликабельно): ");
            TextComponent message = new TextComponent(ChatColor.GREEN + Parser.getConfigCommand(configs.get(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()))));
            message.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, Parser.getConfigCommand(configs.get(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName())))));
            player.spigot().sendMessage(message);
        }
    }

    @Override
    public void onClick(InventoryInteractEvent event) {
        previous.open((Player) event.getWhoClicked());
    }
}
