package ru.shadam.initializer.project;

import ru.shadam.initializer.archive.File;
import ru.shadam.initializer.plugin.Plugin;
import ru.shadam.initializer.util.InstantiateUtil;

import java.util.*;

// TODO : Introduce separate interfaces for plugins and users?
/**
 *
 */
public class Project {
    private final Map<Class<? extends Plugin>, Plugin> plugins;
    private final Map<String, Object> configs;

    public Project() {
        plugins = new LinkedHashMap<>();
        configs = new HashMap<>();
    }

    public boolean isPluginRegistered(Class<?> pluginClass) {
        return plugins.containsKey(pluginClass);
    }

    public <T extends Plugin> void registerPlugin(T plugin) {
        plugins.put(plugin.getClass(), plugin);
        //
        plugin.register(this);
    }

    public void registerPlugins(Plugin... plugins) {
        for(Plugin plugin: plugins) {
            registerPlugin(plugin);
        }
    }

    public void registerConfig(String name, Class<?> configClass) {
        configs.put(name, InstantiateUtil.newInstance(configClass));
    }

    public <T> T getConfig(String name) {
        // TODO: check before cast
        return (T) configs.get(name);
    }

    public List<File> execute() {
        for (Plugin plugin : plugins.values()) {
            plugin.configure(this);
        }
        //
        final List<File> files = new ArrayList<>();
        for (Plugin plugin : plugins.values()) {
            files.addAll(plugin.generateFiles(this));
        }
        return files;
    }
}

