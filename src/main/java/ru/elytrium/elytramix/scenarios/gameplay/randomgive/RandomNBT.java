package ru.elytrium.elytramix.scenarios.gameplay.randomgive;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class RandomNBT {
    private static final Random random = new Random();

    public static ItemStack addRandomNBT(ItemStack prevItemStack){
        switch (prevItemStack.getType()){
            case ENCHANTED_BOOK:
                EnchantmentStorageMeta enchantments = (EnchantmentStorageMeta) prevItemStack.getItemMeta();
                enchantments.addStoredEnchant(getRandomEnchantment(), random.nextInt(3), false);

                prevItemStack.setItemMeta(enchantments);
                return prevItemStack;
            case TIPPED_ARROW:
            case POTION:
            case LINGERING_POTION:
            case SPLASH_POTION:
                PotionMeta potionElements = (PotionMeta) prevItemStack.getItemMeta();
                potionElements.addCustomEffect(new PotionEffect(getRandomEffectType(), random.nextInt(2000), random.nextInt(3)), true);

                prevItemStack.setItemMeta(potionElements);
                return prevItemStack;
            default:
                return prevItemStack;
        }
    }

    private static Enchantment getRandomEnchantment(){
        Enchantment[] all = Enchantment.values();
        return all[random.nextInt(all.length - 1)];
    }

    private static PotionEffectType getRandomEffectType(){
        PotionEffectType[] all = PotionEffectType.values();
        return all[random.nextInt(all.length - 1)];
    }
}
