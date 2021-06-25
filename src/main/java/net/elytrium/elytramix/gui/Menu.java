package net.elytrium.elytramix.gui;

import net.elytrium.elytramix.Plugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

public abstract class Menu implements Listener {
    public final String name;
    private Inventory inventory;
    private final HashMap<ItemStack, Consumer<InventoryClickEvent>> events = new HashMap<>();
    private final int row = 9;
    private final boolean centered;
    private final int margin;

    public Menu(String name, boolean centered, int margin) {
        this.name = name;
        this.centered = centered;
        this.margin = margin;

        Bukkit.getPluginManager().registerEvents(this, Plugin.getInstance());
    }

    @EventHandler(ignoreCancelled = true)
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getWhoClicked() instanceof Player) {
            String name = event.getView().getTitle();

            if (name.equals(this.name)) {
                event.setCancelled(true);
                if (events.containsKey(event.getCurrentItem()))
                    events.get(event.getCurrentItem()).accept(event);
                else
                    onClick(event);
            }
        }
    }

    @EventHandler
    public void onInventoryAction(InventoryInteractEvent event) {
        String name = event.getView().getTitle();

        if (name.equals(this.name)) {
            event.setCancelled(true);
        }
    }

    public void open(Player player) {
        renderInventory(player);
        player.closeInventory();
        player.openInventory(inventory);
    }

    private void renderInventory(Player player) {
        List<MenuItem> toRender = getItems(player);
        toRender.forEach(item -> events.put(item.getItemStack(), item.getEventConsumer()));

        int size = calculateSize(toRender.size() + (margin * 18));
        Inventory renderInventory = Bukkit.createInventory(null, size, name);

        int startPos = (margin * row) + ((centered)? (row - toRender.size()) / 2 : 0);
        for (int i = 0; i < toRender.size(); i++) {
            renderInventory.setItem(startPos + i, toRender.get(i).getItemStack());
        }

        inventory = renderInventory;
    }

    private int calculateSize(int itemsCount) {
        return (int) (Math.ceil(itemsCount / (double) row) * row);
    }

    public abstract List<MenuItem> getItems(Player player);
    public abstract void onClick(InventoryInteractEvent event);
}
