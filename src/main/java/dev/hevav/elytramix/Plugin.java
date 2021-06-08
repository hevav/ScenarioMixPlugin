package dev.hevav.elytramix;

import dev.hevav.elytramix.cui.ConfigurationTabCompleter;
import dev.hevav.elytramix.cui.ScenarioMixCommand;
import dev.hevav.elytramix.cui.essentials.*;
import dev.hevav.elytramix.events.PowerToolUse;
import dev.hevav.elytramix.scenarios.ScenarioCategory;
import dev.hevav.elytramix.scenarios.ScenarioManager;
import dev.hevav.elytramix.scenarios.gameplay.apocalypse.Apocalypse;
import dev.hevav.elytramix.scenarios.gameplay.blockshuffle.BlockShuffle;
import dev.hevav.elytramix.scenarios.gameplay.collideath.Collideath;
import dev.hevav.elytramix.scenarios.gameplay.lastsight.LastSight;
import dev.hevav.elytramix.scenarios.gameplay.lowestkiller.LowestKiller;
import dev.hevav.elytramix.scenarios.gameplay.nojump.NoJump;
import dev.hevav.elytramix.scenarios.gameplay.playerswap.PlayerSwap;
import dev.hevav.elytramix.scenarios.gameplay.pusher.Pusher;
import dev.hevav.elytramix.scenarios.gameplay.randomgive.RandomGive;
import dev.hevav.elytramix.scenarios.gameplay.security.Security;
import dev.hevav.elytramix.scenarios.gameplay.snowballs.Snowballs;
import dev.hevav.elytramix.scenarios.gameplay.snowfall.Snowfall;
import dev.hevav.elytramix.scenarios.gameplay.spiderpocalypse.Spiderpocalypse;
import dev.hevav.elytramix.scenarios.gameplay.throwtnt.ThrowTNT;
import dev.hevav.elytramix.scenarios.tools.autorespawn.AutoRespawn;
import dev.hevav.elytramix.scenarios.tools.autospectator.AutoSpectator;
import dev.hevav.elytramix.scenarios.tools.fightme.FightMe;
import dev.hevav.elytramix.scenarios.tools.fill.Fill;
import dev.hevav.elytramix.scenarios.tools.heightlimit.HeightLimit;
import dev.hevav.elytramix.scenarios.tools.playerride.PlayerRide;
import dev.hevav.elytramix.scenarios.tools.randomteam.RandomTeam;
import dev.hevav.elytramix.utils.ItemUtils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class Plugin extends JavaPlugin {
    public static org.bukkit.plugin.Plugin plugin;
    public static final String command = "mix";

    public File powertoolFile;
    private FileConfiguration powertoolData;

    @Override
    public void onEnable() {
        plugin = this;
        loadScenarioManager();
        this.getCommand(command).setExecutor(new ScenarioMixCommand());
        this.getCommand(command).setTabCompleter(new ConfigurationTabCompleter());

        // Creating configuration files
        createPowertoolFile();

        // Essentials commands
        this.getCommand("bc").setExecutor(new Broadcast());
        this.getCommand("day").setExecutor(new Day());
        this.getCommand("fly").setExecutor(new Fly());
        this.getCommand("gm").setExecutor(new Gamemode());
        this.getCommand("heal").setExecutor(new Heal());
        this.getCommand("kickall").setExecutor(new KickAll());
        this.getCommand("mobkill").setExecutor(new MobKill());
        this.getCommand("night").setExecutor(new Night());
        this.getCommand("rain").setExecutor(new Rain());
        this.getCommand("sun").setExecutor(new Sun());
        this.getCommand("powertool").setExecutor(new PowerTool(this));

        Bukkit.getPluginManager().registerEvents(new PowerToolUse(this), this);
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

    public FileConfiguration powertoolData() {
        return this.powertoolData;
    }

    private void createPowertoolFile() {
        powertoolFile = new File(getDataFolder(), "powertool.yml");
        if (!powertoolFile.exists()) {
            powertoolFile.getParentFile().mkdirs();
            saveResource("powertool.yml", false);
        }

        powertoolData = new YamlConfiguration();
        try {
            powertoolData.load(powertoolFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

}