package ru.shadam.initializer.plugin.springboot.config;

import ru.shadam.initializer.plugin.groovy.config.GroovyClass;

/**
 * @author sala
 */
public class ControllerGroovyClass extends GroovyClass {
    private TemplateEngineConfig templateEngineConfig;

    public ControllerGroovyClass(String packageName, String className, String templateName) {
        super(packageName, className, templateName);
    }

    public TemplateEngineConfig getTemplateEngineConfig() {
        return templateEngineConfig;
    }

    public void setTemplateEngineConfig(TemplateEngineConfig templateEngineConfig) {
        this.templateEngineConfig = templateEngineConfig;
    }

    public static ControllerGroovyClass fromGroovyClass(GroovyClass groovyClass) {
        return new ControllerGroovyClass(
                groovyClass.getPackageName(),
                groovyClass.getClassName(),
                groovyClass.getTemplateName()
        );
    }
}
