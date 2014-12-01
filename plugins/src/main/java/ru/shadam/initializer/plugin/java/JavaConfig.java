package ru.shadam.initializer.plugin.java;

import ru.shadam.initializer.plugin.java.config.JavaClass;
import ru.shadam.initializer.plugin.jvm.config.Resource;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class JavaConfig {
    private String source = "1.7";
    private String target = "1.7";

    private String sourceDirectory = Paths.get("src", "main", "java").toString();
    private List<JavaClass> classes = new ArrayList<>();

    private String resourceDirectory = Paths.get("src", "main", "resources").toString();
    private List<Resource> resources = new ArrayList<>();

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public List<JavaClass> getClasses() {
        return classes;
    }

    public void setClasses(List<JavaClass> classes) {
        this.classes = classes;
    }

    public String getSourceDirectory() {
        return sourceDirectory;
    }

    public void setSourceDirectory(String sourceDirectory) {
        this.sourceDirectory = sourceDirectory;
    }

    public String getResourceDirectory() {
        return resourceDirectory;
    }

    public void setResourceDirectory(String resourceDirectory) {
        this.resourceDirectory = resourceDirectory;
    }

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }
}
