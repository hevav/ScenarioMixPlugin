package io.github._7isenko.scenariomix.gui;

import io.github._7isenko.scenariomix.ScenarioMix;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class MainMenu extends Menu {

    public MainMenu() {
        super("Выберите тип сценариев");
        inventory = createInventory(name);
        Bukkit.getPluginManager().registerEvents(new MainMenu.MenuListener(), ScenarioMix.plugin);
    }

    private Inventory createInventory(String name) {
        Inventory inventory = Bukkit.createInventory(null, 9, name);

        ItemStack gameplay = new ItemStack(Material.BEACON);
        ItemMeta meta = gameplay.getItemMeta();
        meta.setDisplayName(ChatColor.YELLOW + "Игровые сценарии");
        gameplay.setItemMeta(meta);
        inventory.setItem(3, gameplay);

        ItemStack tools = new ItemStack(Material.ANVIL);
        ItemMeta mt = tools.getItemMeta();
        mt.setDisplayName(ChatColor.YELLOW + "Инструменты");
        tools.setItemMeta(mt);
        inventory.setItem(5, tools);

        return inventory;
    }

    private class MenuListener implements Listener {
        @EventHandler(priority = EventPriority.HIGHEST)
        public void onClick(InventoryClickEvent event) {
            if (!event.getView().getTitle().equals(name))
                return;
            if (event.getCurrentItem() == null) {
                event.getWhoClicked().closeInventory();
                return;
            }
            if (event.getCurrentItem().getType() == Material.AIR)
                return;
            event.setCancelled(true);
            int slot = event.getSlot();

            switch (slot) {
                case 3:
                    open(event.getWhoClicked(), MenuHandler.getInstance().getScenarioMenu());
                    break;

                case 5:
                    open(event.getWhoClicked(), MenuHandler.getInstance().getToolMenu());
                    break;
            }
        }

        @EventHandler
        public void onInventoryClick(final InventoryDragEvent e) {
            if (e.getInventory() == inventory) {
                e.setCancelled(true);
            }
        }

        private void open(HumanEntity player, Menu menu) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    menu.open(player);
                }
            }.runTaskLater(ScenarioMix.plugin, 1);
        }
    }

}
