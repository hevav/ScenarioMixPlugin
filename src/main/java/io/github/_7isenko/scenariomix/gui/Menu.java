package io.github._7isenko.scenariomix.gui;

import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.Inventory;

public abstract class Menu {
    protected String name;
    protected Inventory inventory;

    public Menu(String name){
        this.name = name;
    }

    public void open(HumanEntity player) {
        player.openInventory(inventory);
    }

}
