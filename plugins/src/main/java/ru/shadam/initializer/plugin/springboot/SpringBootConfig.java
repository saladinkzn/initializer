package ru.shadam.initializer.plugin.springboot;

import ru.shadam.initializer.plugin.java.config.JavaClass;
import ru.shadam.initializer.plugin.springboot.config.TemplateEngineConfig;

/**
 * @author sala
 */
public class SpringBootConfig {
    private String springBootVersion = "1.1.9.RELEASE";

    private JavaClass entryPoint = new JavaClass("org.example", "Program", "templates/springboot/entrypoint.java.ftl");
    private JavaClass controller = new JavaClass("org.example", "HelloController", "templates/springboot/controller.java.ftl");

    private TemplateEngineConfig templateEngineConfig = new TemplateEngineConfig(false, false);

    public String getSpringBootVersion() {
        return springBootVersion;
    }

    public void setSpringBootVersion(String springBootVersion) {
        this.springBootVersion = springBootVersion;
    }

    public JavaClass getEntryPoint() {
        return entryPoint;
    }

    public void setEntryPoint(JavaClass entryPoint) {
        this.entryPoint = entryPoint;
    }

    public JavaClass getController() {
        return controller;
    }

    public void setController(JavaClass controller) {
        this.controller = controller;
    }

    public TemplateEngineConfig getTemplateEngineConfig() {
        return templateEngineConfig;
    }

    public void setTemplateEngineConfig(TemplateEngineConfig templateEngineConfig) {
        this.templateEngineConfig = templateEngineConfig;
    }
}
