package ru.elytrium.elytramix.scenarios;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;
import ru.elytrium.elytramix.Plugin;
import ru.elytrium.elytramix.scenarios.config.Configuration;
import ru.elytrium.elytramix.utils.ItemUtils;

import java.util.*;
@SuppressWarnings("rawtypes")
public abstract class Scenario {
    private Plugin plugin;

    public Scenario(Plugin plugin){ this.plugin = plugin; }

    private String name;
    private String configName;
    private String[] description;
    private Material icon;
    private boolean started;
    private Map<BukkitRunnable, Integer> runnables;
    private Set<Listener> listeners;
    private Map<String, Configuration> configs;

    public Scenario(String name, String configName, String icon, String... description) {
        this.name = name;
        this.configName = configName;
        this.description = description;
        this.icon = ItemUtils.getMaterial(icon);
        this.started = false;
        this.runnables = new HashMap<>();
        this.listeners = new HashSet<>();
        this.configs = new HashMap<>();
    }

    public boolean toggle() {
        if (started) disable();
        else enable();
        return started;
    }

    public void enable() {
        Bukkit.broadcastMessage(plugin.getMessageString("scenariomix.enable")
                .replace("{scenario}", name));
        started = true;
        start();
        startListeners();
        startBukkitRunnables();
    }

    public void disable() {
        if (started) {
            Bukkit.broadcastMessage(plugin.getMessageString("scenariomix.disable")
                    .replace("{scenario}", name));
            started = false;
            stop();
            stopListeners();
            stopBukkitRunnables();
        }
    }

    public abstract void start();

    public abstract void stop();

    public void addConfig(Configuration configuration) {
        configs.put(configuration.getName(), configuration);
    }

    public void addBukkitRunnable(BukkitRunnable runnable, int period) {
        runnables.put(runnable, period);
    }

    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    private void startListeners() {
        listeners.forEach((listener) -> Bukkit.getPluginManager().registerEvents(listener, Plugin.plugin));
    }

    private void stopListeners() {
        listeners.forEach(HandlerList::unregisterAll);
    }

    private void startBukkitRunnables() {
        runnables.forEach((runnable, period) -> runnable.runTaskTimer(Plugin.plugin, 20L, (long) period));
    }

    private void stopBukkitRunnables() {
        HashMap<BukkitRunnable, Integer> newRunnables = new HashMap<>();
        runnables.forEach((runnable, integer) -> {
            runnable.cancel();

            try {
                newRunnables.put(runnable.getClass().newInstance(), integer);
            } catch (IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }

        });
        runnables.clear();
        runnables = newRunnables;
    }

    public Configuration getConfig(String name) {
        return configs.get(name);
    }

    public boolean isConfigurable() {
        return !configs.isEmpty();
    }

    public String getName() {
        return name;
    }

    public List<String> getDescription() {
        return Arrays.asList(description);
    }

    public Set<String> getConfigStrings() {
        return configs.keySet();
    }

    public Material getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = ItemUtils.getMaterial(icon);
    }

    public boolean isStarted() {
        return started;
    }

    public String getConfigName() {
        return configName;
    }

    public Map<String, Configuration> getConfigs() {
        return configs;
    }
}
