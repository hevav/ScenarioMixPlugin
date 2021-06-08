package ru.elytrium.elytramix.scenarios.gameplay.blockshuffle;

import org.bukkit.Material;
import ru.elytrium.elytramix.scenarios.Scenario;
import ru.elytrium.elytramix.scenarios.config.Configuration;

public class BlockShuffle extends Scenario {
    public BlockShuffle() {
        super("BlockShuffle", "block_shuffle", "IRON_BOOTS", "Игроки должны за определенное", "время встать на блок, имя", "которого они увидят на", "экране.");
        addConfig(materials);
    }

    private final Configuration<Material[]> materials = new Configuration<>("materials", new Material[]{
            Material.ANDESITE_SLAB, Material.ANDESITE, Material.ANVIL, Material.BARREL, Material.BEDROCK, Material.BAMBOO, Material.BEEHIVE, Material.BELL, Material.BLACK_CARPET, Material.BLACK_CONCRETE, Material.BLACK_BED, Material.BLACK_STAINED_GLASS, Material.BLACK_WOOL, Material.RED_CARPET, Material.RED_CONCRETE, Material.RED_BED, Material.RED_STAINED_GLASS, Material.RED_WOOL, Material.GREEN_CARPET, Material.GREEN_CONCRETE, Material.GREEN_BED, Material.GREEN_STAINED_GLASS, Material.GREEN_WOOL, Material.BLUE_CARPET, Material.BLUE_CONCRETE, Material.BLUE_BED, Material.BLUE_STAINED_GLASS, Material.BLUE_WOOL, Material.YELLOW_CARPET, Material.YELLOW_CONCRETE, Material.YELLOW_BED, Material.YELLOW_STAINED_GLASS, Material.YELLOW_WOOL, Material.BIRCH_PLANKS, Material.DARK_OAK_PLANKS, Material.JUNGLE_PLANKS, Material.OAK_PLANKS, Material.SPRUCE_PLANKS, Material.IRON_BLOCK, Material.JUKEBOX, Material.DIAMOND_ORE, Material.IRON_BARS, Material.IRON_BLOCK, Material.GOLD_BLOCK, Material.GOLD_ORE, Material.DAYLIGHT_DETECTOR, Material.HOPPER, Material.HONEYCOMB_BLOCK, Material.FLOWER_POT, Material.DRIED_KELP_BLOCK, Material.GLOWSTONE, Material.MAGMA_BLOCK, Material.DISPENSER, Material.EMERALD_ORE, Material.GRANITE, Material.GLASS, Material.GLASS_PANE, Material.LANTERN, Material.ICE, Material.PACKED_ICE, Material.JACK_O_LANTERN, Material.FARMLAND, Material.DROPPER, Material.FROSTED_ICE, Material.CUT_SANDSTONE, Material.ENCHANTING_TABLE, Material.COMPOSTER, Material.CHISELED_QUARTZ_BLOCK, Material.ENDER_CHEST, Material.PUMPKIN, Material.HAY_BLOCK, Material.BRICK_SLAB, Material.BOOKSHELF, Material.CLAY, Material.CAMPFIRE, Material.DRIED_KELP_BLOCK, Material.MELON, Material.CARVED_PUMPKIN, Material.SOUL_SAND, Material.REDSTONE_ORE, Material.LAPIS_ORE, Material.SMOOTH_STONE, Material.TNT, Material.PISTON, Material.NOTE_BLOCK, Material.LECTERN, Material.BROWN_MUSHROOM_BLOCK, Material.RED_MUSHROOM_BLOCK, Material.LILY_PAD, Material.SMITHING_TABLE, Material.GRINDSTONE, Material.LOOM, Material.LIGHT_BLUE_WOOL, Material.BIRCH_LEAVES, Material.SNOW_BLOCK
    }, "DIRT", this, "Предметы, куда встать");

    public void start() {

    }

    public void stop() {

    }

    public Material[] getItems(){
        return materials.value();
    }
}
