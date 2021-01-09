package io.github._7isenko.scenariomix.gui;

import io.github._7isenko.scenariomix.ScenarioMix;
import io.github._7isenko.scenariomix.scenarios.config.Configuration;
import io.github._7isenko.scenariomix.scenarios.Scenario;
import io.github._7isenko.scenariomix.utils.Calculator;
import io.github._7isenko.scenariomix.utils.Parser;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class ConfigurationsMenu extends Menu {
    private final Map<String, Configuration> configs;
    private final Menu previous;
    private Listener listener;

    public ConfigurationsMenu(Scenario scenario, Menu previousMenu) {
        super("Конфигурация для сценария \"" + scenario.getName() + "\"");
        this.previous = previousMenu;
        configs = scenario.getConfigs();
        this.inventory = create(scenario);
        if (inventory != null) {
            listener = new ConfigurationsMenu.MenuListener();
            Bukkit.getPluginManager().registerEvents(listener, ScenarioMix.plugin);
        }
    }

    private Inventory create(Scenario scenario) {
        if (scenario.getConfigs().isEmpty())
            return null;
        Inventory inventory = Bukkit.createInventory(null, Calculator.calculateInventorySize(configs.size()), name);
        scenario.getConfigs().forEach((name, config) -> {
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
            inventory.addItem(item);
        });
        return inventory;
    }

    private class MenuListener implements Listener {
        @EventHandler(priority = EventPriority.HIGHEST)
        public void onClick(InventoryClickEvent event) {
            if (!event.getView().getTitle().equals(name))
                return;
            if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR) {
                previous.open(event.getWhoClicked());
                return;
            }
            event.setCancelled(true);

            HumanEntity player = event.getWhoClicked();
            player.sendMessage(ChatColor.AQUA + "Для смены параметра введите следующую команду (кликабельно): ");
            TextComponent message = new TextComponent(ChatColor.GREEN + Parser.getConfigCommand(configs.get(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()))));
            message.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, Parser.getConfigCommand(configs.get(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName())))));
            player.spigot().sendMessage(message);
        }

        @EventHandler
        public void onClose(InventoryCloseEvent event) {
            if (event.getView().getTitle().equals(name)) {
                HandlerList.unregisterAll(listener);
            }
        }

        @EventHandler
        public void onInventoryClick(InventoryDragEvent e) {
            if (e.getView().getTitle().equals(name)) {
                e.setCancelled(true);
            }
        }
    }


    @Override
    public void open(HumanEntity player) {
        if (inventory == null) {
            player.playEffect(EntityEffect.VILLAGER_ANGRY);
            player.getWorld().playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
        } else
            super.open(player);
    }
}
