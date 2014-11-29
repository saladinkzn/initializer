package ru.shadam.initializer.plugin.groovy.config;

import java.io.File;
import java.nio.file.Paths;

/**
 * @author sala
 */
public class GroovyClass {
    private String packageName;
    private String className;
    private String templateName;

    public GroovyClass(String packageName, String className, String templateName) {
        this.packageName = packageName;
        this.className = className;
        this.templateName = templateName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getPath(String sourceDirectory) {
        return Paths.get(sourceDirectory, packageName.replace(".", File.separator), className + ".groovy").toString();
    }
}
