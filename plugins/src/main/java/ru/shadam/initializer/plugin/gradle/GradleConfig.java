package ru.shadam.initializer.plugin.gradle;

import ru.shadam.initializer.plugin.gradle.config.Dependency;
import ru.shadam.initializer.plugin.gradle.config.Extension;
import ru.shadam.initializer.plugin.gradle.config.Plugin;
import ru.shadam.initializer.plugin.gradle.config.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 */
public class GradleConfig {
    private String gradleVersion = "2.2";

    private Set<Repository> repositories = new HashSet<>();
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

    public Set<Repository> getRepositories() {
        return repositories;
    }

    public void setRepositories(Set<Repository> repositories) {
        this.repositories = repositories;
    }
}
