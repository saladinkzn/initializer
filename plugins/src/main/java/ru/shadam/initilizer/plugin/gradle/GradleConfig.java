package ru.shadam.initilizer.plugin.gradle;

import ru.shadam.initilizer.plugin.gradle.config.Dependency;
import ru.shadam.initilizer.plugin.gradle.config.Extension;
import ru.shadam.initilizer.plugin.gradle.config.Plugin;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class GradleConfig {
    private String gradleVersion;

    private List<Dependency> dependencies = new ArrayList<>();
    private List<Extension> extensions = new ArrayList<>();
    private List<Plugin> plugins = new ArrayList<>();

    public String getGradleVersion() {
        return gradleVersion;
    }

    public void setGradleVersion(String gradleVersion) {
        this.gradleVersion = gradleVersion;
    }

    public List<Dependency> getDependencies() {
        return dependencies;
    }

    public void setDependencies(List<Dependency> dependencies) {
        this.dependencies = dependencies;
    }

    public List<Extension> getExtensions() {
        return extensions;
    }

    public void setExtensions(List<Extension> extensions) {
        this.extensions = extensions;
    }

    public List<Plugin> getPlugins() {
        return plugins;
    }

    public void setPlugins(List<Plugin> plugins) {
        this.plugins = plugins;
    }
}
