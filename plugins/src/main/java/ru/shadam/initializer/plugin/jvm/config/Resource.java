package ru.shadam.initializer.plugin.jvm.config;

import java.io.File;
import java.nio.file.Paths;

/**
 * @author sala
 */
public class Resource {
    private String packageName;
    private String fileName;
    private String templateName;

    public Resource() {
    }

    public Resource(String packageName, String fileName, String templateName) {
        this.packageName = packageName;
        this.fileName = fileName;
        this.templateName = templateName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getPath(String sourceDirectory) {
        return Paths.get(sourceDirectory, packageName.replace(".", File.separator), fileName).toString();
    }
}
