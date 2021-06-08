package ru.elytrium.elytramix.utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemUtils {
    public static void enchantItem(ItemStack item, boolean removeAll) {
        ItemMeta meta = item.getItemMeta();
        if (removeAll)
            meta.getEnchants().forEach((enchantment, integer) -> meta.removeEnchant(enchantment));
        else
            meta.addEnchant(Enchantment.VANISHING_CURSE, 1, true);
        item.setItemMeta(meta);
    }

    public static Material getMaterial(String materialName){
        Material material = Material.getMaterial(materialName); //в 1.12- нету параметра legacyName
        if (material == null) material = Material.getMaterial(materialName, true); //1.13+
        return material;
    }

    public static ItemStack withName(Material material, String name) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.YELLOW + name);
        item.setItemMeta(meta);

        return item;
    }
}
