package ru.shadam.initializer.plugin.groovy;

import ru.shadam.initializer.plugin.groovy.config.GroovyClass;
import ru.shadam.initializer.plugin.jvm.config.Resource;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sala
 */
public class GroovyConfig {
    private String groovyVersion;

    private List<GroovyClass> classes = new ArrayList<>();
    private String sourceDirectory = Paths.get("src", "main", "groovy").toString();

    private List<Resource> resources = new ArrayList<>();
    private String resourceDirectory = Paths.get("src", "main", "resources").toString();

    public String getGroovyVersion() {
        return groovyVersion;
    }

    public void setGroovyVersion(String groovyVersion) {
        this.groovyVersion = groovyVersion;
    }

    public List<GroovyClass> getClasses() {
        return classes;
    }

    public void setClasses(List<GroovyClass> classes) {
        this.classes = classes;
    }

    public String getSourceDirectory() {
        return sourceDirectory;
    }

    public void setSourceDirectory(String sourceDirectory) {
        this.sourceDirectory = sourceDirectory;
    }

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }

    public String getResourceDirectory() {
        return resourceDirectory;
    }

    public void setResourceDirectory(String resourceDirectory) {
        this.resourceDirectory = resourceDirectory;
    }
}
