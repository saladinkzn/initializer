package ru.shadam.initilizer.plugin.springboot;

import ru.shadam.initilizer.plugin.java.config.JavaClass;

/**
 * @author sala
 */
public class SpringBootConfig {
    private String springBootVersion = "1.1.9.RELEASE";

    private JavaClass entryPoint = new JavaClass("org.example", "Program", "templates/springboot/entrypoint.java.ftl");
    private JavaClass controller = new JavaClass("org.example", "HelloController", "templates/springboot/controller.java.ftl");

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
}
