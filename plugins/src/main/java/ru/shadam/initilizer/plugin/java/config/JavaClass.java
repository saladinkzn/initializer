package ru.shadam.initilizer.plugin.java.config;

/**
 *
 */
public class JavaClass {
    private String packageName;
    private String className;

    private String bodyString;

    public JavaClass() {
    }

    public JavaClass(String packageName, String className) {
        this.packageName = packageName;
        this.className = className;
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

    public String getBodyString() {
        return bodyString;
    }

    public void setBodyString(String bodyString) {
        this.bodyString = bodyString;
    }

    public String getPath(String sourceDirectory) {
        return sourceDirectory + "/" + packageName.replace(".", "/") + "/" + className + ".java";
    }
}