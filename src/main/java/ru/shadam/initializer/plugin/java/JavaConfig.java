package ru.shadam.initializer.plugin.java;

import ru.shadam.initializer.plugin.java.config.JavaClass;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class JavaConfig {
    private String source = "1.7";
    private String target = "1.7";

    private List<JavaClass> classes = new ArrayList<>();
    private String sourceDirectory = "src/main/java";

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
}
