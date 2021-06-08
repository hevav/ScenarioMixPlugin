package dev.hevav.elytramix.gui;

import dev.hevav.elytramix.utils.ItemUtils;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

public class MenuItem {
    private final ItemStack itemStack;
    private final Consumer<InventoryClickEvent> eventConsumer;

    public MenuItem(ItemStack itemStack, Consumer<InventoryClickEvent> eventConsumer) {
        this.itemStack = itemStack;
        this.eventConsumer = eventConsumer;
    }

    public MenuItem(Material material, String name, Consumer<InventoryClickEvent> eventConsumer) {
        this.itemStack = ItemUtils.withName(material, name);
        this.eventConsumer = eventConsumer;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public Consumer<InventoryClickEvent> getEventConsumer() {
        return eventConsumer;
    }
}
