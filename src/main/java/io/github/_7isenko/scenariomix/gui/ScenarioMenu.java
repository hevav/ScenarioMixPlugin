package io.github._7isenko.scenariomix.gui;

import io.github._7isenko.scenariomix.scenarios.ScenarioManager;
import io.github._7isenko.scenariomix.ScenarioMix;
import io.github._7isenko.scenariomix.scenarios.Scenario;
import io.github._7isenko.scenariomix.utils.Calculator;
import io.github._7isenko.scenariomix.utils.Enchanter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Map;

public class ScenarioMenu extends Menu {
    protected Map<Integer, Scenario> scenarios;
    private final Menu menuInstance;

    public ScenarioMenu() {
        super("Доступные сценарии");
        scenarios = ScenarioManager.getInstance().getScenarios();
        inventory = createInventory(name, ScenarioManager.getInstance().getScenarios());
        menuInstance = this;
        Bukkit.getPluginManager().registerEvents(new InventoryListener(), ScenarioMix.plugin);
    }

    protected boolean switchScenario(int slot) {
        return ScenarioManager.getInstance().switchScenario(slot);
    }

    public class InventoryListener implements Listener {
        @EventHandler(priority = EventPriority.HIGHEST)
        public void onClick(InventoryClickEvent event) {
            if (!event.getView().getTitle().equals(name))
                return;
            event.setCancelled(true);
            final ItemStack clickedItem = event.getCurrentItem();
            if (clickedItem == null || clickedItem.getType() == Material.AIR) {
                MenuHandler.getInstance().getMainMenu().open(event.getWhoClicked());
                return;
            }
            int slot = event.getSlot();
            Scenario scenario = scenarios.get(slot);
            if (event.getClick() == ClickType.LEFT || event.getClick() == ClickType.SHIFT_LEFT) {
                boolean enabled = switchScenario(slot);
                String name = scenario.getName();
                if (enabled)
                    event.getWhoClicked().sendMessage(ChatColor.GREEN + "Вы включили сценарий " + ChatColor.BLUE + ChatColor.BOLD + name);
                else
                    event.getWhoClicked().sendMessage(ChatColor.YELLOW + "Вы отключили сценарий " + ChatColor.RED + ChatColor.BOLD + name);
                Enchanter.enchantItem(inventory.getItem(slot), !enabled);
            } else if (event.getClick() == ClickType.RIGHT || event.getClick() == ClickType.SHIFT_RIGHT) {
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        new ConfigurationsMenu(scenario, menuInstance).open(event.getWhoClicked());
                    }
                }.runTaskLater(ScenarioMix.plugin, 1);
            }
        }

        @EventHandler
        public void onInventoryClick(final InventoryDragEvent e) {
            if (e.getInventory() == inventory) {
                e.setCancelled(true);
            }
        }
    }

    public Inventory createInventory(String name, Map<Integer, Scenario> scenarioSet) {
        Inventory inventory = Bukkit.createInventory(null, Calculator.calculateInventorySize(scenarioSet.size()), name);
        scenarioSet.forEach((number, scenario) -> {
            ItemStack item = new ItemStack(scenario.getIcon());
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(ChatColor.YELLOW + scenario.getName());
            ArrayList<String> lore = new ArrayList<>(scenario.getDescription());
            lore.add(ChatColor.GRAY + scenario.getConfigName());
            meta.setLore(lore);
            item.setItemMeta(meta);
            inventory.setItem(number, item);
        });
        return inventory;
    }

}
