package ru.shadam.initializer.plugin.java.config;

/**
 *
 */
public class JavaClass {
    private String packageName;
    private String className;
    private String templateName;

    public JavaClass(String packageName, String className, String templateName) {
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
        return sourceDirectory + "/" + packageName.replace(".", "/") + "/" + className + ".java";
    }
}
