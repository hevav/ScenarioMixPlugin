package dev.hevav.essentialsplugin;

import dev.hevav.essentialsplugin.cui.ScenarioMixCommand;
import dev.hevav.essentialsplugin.cui.ConfigurationTabCompleter;
import dev.hevav.essentialsplugin.scenarios.ScenarioCategory;
import dev.hevav.essentialsplugin.scenarios.ScenarioManager;
import dev.hevav.essentialsplugin.scenarios.gameplay.apocalypse.Apocalypse;
import dev.hevav.essentialsplugin.scenarios.gameplay.blockshuffle.BlockShuffle;
import dev.hevav.essentialsplugin.scenarios.gameplay.collideath.Collideath;
import dev.hevav.essentialsplugin.scenarios.tools.fill.Fill;
import dev.hevav.essentialsplugin.scenarios.gameplay.lastsight.LastSight;
import dev.hevav.essentialsplugin.scenarios.gameplay.lowestkiller.LowestKiller;
import dev.hevav.essentialsplugin.scenarios.gameplay.nojump.NoJump;
import dev.hevav.essentialsplugin.scenarios.tools.playerride.PlayerRide;
import dev.hevav.essentialsplugin.scenarios.gameplay.playerswap.PlayerSwap;
import dev.hevav.essentialsplugin.scenarios.gameplay.pusher.Pusher;
import dev.hevav.essentialsplugin.scenarios.gameplay.randomgive.RandomGive;
import dev.hevav.essentialsplugin.scenarios.gameplay.security.Security;
import dev.hevav.essentialsplugin.scenarios.gameplay.snowballs.Snowballs;
import dev.hevav.essentialsplugin.scenarios.gameplay.snowfall.Snowfall;
import dev.hevav.essentialsplugin.scenarios.gameplay.spiderpocalypse.Spiderpocalypse;
import dev.hevav.essentialsplugin.scenarios.gameplay.throwtnt.ThrowTNT;
import dev.hevav.essentialsplugin.scenarios.tools.autorespawn.AutoRespawn;
import dev.hevav.essentialsplugin.scenarios.tools.autospectator.AutoSpectator;
import dev.hevav.essentialsplugin.scenarios.tools.fightme.FightMe;
import dev.hevav.essentialsplugin.scenarios.tools.heightlimit.HeightLimit;
import dev.hevav.essentialsplugin.scenarios.tools.randomteam.RandomTeam;
import dev.hevav.essentialsplugin.utils.ItemUtils;
import org.bukkit.plugin.java.JavaPlugin;

public class Plugin extends JavaPlugin {
    public static org.bukkit.plugin.Plugin plugin;
    public static final String command = "mix";

    @Override
    public void onEnable() {
        plugin = this;
        loadScenarioManager();
        this.getCommand(command).setExecutor(new ScenarioMixCommand());
        this.getCommand(command).setTabCompleter(new ConfigurationTabCompleter());
    }

    @Override
    public void onDisable() {
        ScenarioManager.getInstance().disableAll();
    }

    private void loadScenarioManager() {
        final ScenarioCategory CATEGORY_GAMEPLAY = new ScenarioCategory("gameplay", "Геймплей", ItemUtils.getMaterial("NOTE_BLOCK"));
        final ScenarioCategory CATEGORY_TOOL = new ScenarioCategory("tools", "Инструменты", ItemUtils.getMaterial("STONE_SWORD"));
        final ScenarioCategory CATEGORY_ADMIN = new ScenarioCategory("admin", "Административные", ItemUtils.getMaterial("DIAMOND_SWORD"));


        CATEGORY_TOOL.addScenario(new AutoSpectator());
        CATEGORY_TOOL.addScenario(new AutoRespawn());
        CATEGORY_TOOL.addScenario(new FightMe());
        CATEGORY_TOOL.addScenario(new HeightLimit());
        CATEGORY_TOOL.addScenario(new RandomTeam());
        CATEGORY_TOOL.addScenario(new Fill());
        CATEGORY_TOOL.addScenario(new PlayerRide());

        CATEGORY_GAMEPLAY.addScenario(new Pusher());
        CATEGORY_GAMEPLAY.addScenario(new LowestKiller());
        CATEGORY_GAMEPLAY.addScenario(new Snowballs());
        CATEGORY_GAMEPLAY.addScenario(new Snowfall());
        CATEGORY_GAMEPLAY.addScenario(new LastSight());
        CATEGORY_GAMEPLAY.addScenario(new Spiderpocalypse());
        CATEGORY_GAMEPLAY.addScenario(new Apocalypse());
        CATEGORY_GAMEPLAY.addScenario(new Collideath());
        CATEGORY_GAMEPLAY.addScenario(new NoJump());
        CATEGORY_GAMEPLAY.addScenario(new Security());
        CATEGORY_GAMEPLAY.addScenario(new RandomGive());
        CATEGORY_GAMEPLAY.addScenario(new ThrowTNT());
        CATEGORY_GAMEPLAY.addScenario(new PlayerSwap());
        CATEGORY_GAMEPLAY.addScenario(new BlockShuffle());

        ScenarioManager scenarioManager = ScenarioManager.getInstance();
        scenarioManager.addCategory(CATEGORY_GAMEPLAY);
        scenarioManager.addCategory(CATEGORY_TOOL);
        scenarioManager.addCategory(CATEGORY_ADMIN);
    }
}