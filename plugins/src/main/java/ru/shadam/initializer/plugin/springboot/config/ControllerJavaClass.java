package ru.shadam.initializer.plugin.springboot.config;

import ru.shadam.initializer.plugin.java.config.JavaClass;

/**
 * @author sala
 */
public class ControllerJavaClass extends JavaClass {
    private TemplateEngineConfig templateEngineConfig;

    public ControllerJavaClass(String packageName, String className, String templateName) {
        super(packageName, className, templateName);
    }

    public TemplateEngineConfig getTemplateEngineConfig() {
        return templateEngineConfig;
    }

    public void setTemplateEngineConfig(TemplateEngineConfig templateEngineConfig) {
        this.templateEngineConfig = templateEngineConfig;
    }

    public static ControllerJavaClass fromJavaClass(JavaClass javaClass) {
        return new ControllerJavaClass(
                javaClass.getPackageName(),
                javaClass.getClassName(),
                javaClass.getTemplateName()
        );
    }
}
