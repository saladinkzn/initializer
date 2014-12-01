package ru.shadam.initializer.plugin.springboot;

import ru.shadam.initializer.archive.File;
import ru.shadam.initializer.plugin.Plugin;
import ru.shadam.initializer.plugin.gradle.GradlePlugin;
import ru.shadam.initializer.plugin.groovy.GroovyConfig;
import ru.shadam.initializer.plugin.groovy.GroovyPlugin;
import ru.shadam.initializer.plugin.groovy.config.GroovyClass;
import ru.shadam.initializer.plugin.java.JavaConfig;
import ru.shadam.initializer.plugin.java.JavaPlugin;
import ru.shadam.initializer.plugin.jvm.config.Resource;
import ru.shadam.initializer.plugin.springboot.config.ControllerGroovyClass;
import ru.shadam.initializer.plugin.springboot.config.ControllerJavaClass;
import ru.shadam.initializer.project.Project;
import ru.shadam.initializer.plugin.gradle.GradleConfig;
import ru.shadam.initializer.plugin.gradle.config.Dependency;
import ru.shadam.initializer.plugin.gradle.config.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sala
 */
public class SpringBootPlugin extends Plugin {
    public static final String SPRINGBOOT_CONFIG_KEY = "springboot";

    @Override
    public void register(Project project) {
        if(!project.isPluginRegistered(GradlePlugin.class)) {
            throw new IllegalStateException("Spring-Boot depends on gradle plugin");
        }
        if(!project.isAnyPluginRegistered(JavaPlugin.class, GroovyPlugin.class)) {
            throw new IllegalStateException("Spring-Boot depends on java or groovy plugin");
        }

        project.registerConfig(SPRINGBOOT_CONFIG_KEY, SpringBootConfig.class);

    }

    @Override
    public void configure(Project project) {
        super.configure(project);

        final SpringBootConfig springBootConfig = project.getConfig(SPRINGBOOT_CONFIG_KEY);
        final String springBootVersion = springBootConfig.getSpringBootVersion();
        //
        final GradleConfig gradleConfig = project.getConfig(GradlePlugin.GRADLE_CONFIG_KEY);
        //
        gradleConfig.getRepositories()
                .add(Repository.jcenter());
        gradleConfig.getDependencies()
                .add(new Dependency("org.springframework.boot", "spring-boot-starter-web", springBootVersion));
        //
        if(springBootConfig.getTemplateEngineConfig().isUseFreemarker()) {
            gradleConfig.getDependencies()
                    .add(new Dependency("org.springframework.boot", "spring-boot-starter-freemarker", springBootVersion));
        }
        if(springBootConfig.getTemplateEngineConfig().isUseVelocity()) {
            gradleConfig.getDependencies()
                    .add(new Dependency("org.springframework.boot", "spring-boot-starter-velocity", springBootVersion));
        }

        final GroovyConfig groovyConfig = GroovyPlugin.getConfig(project);
        if(groovyConfig != null) {
            final GroovyClass groovyClass = new GroovyClass(
                    springBootConfig.getController().getPackageName(),
                    springBootConfig.getController().getClassName(),
                    "templates/springboot/controller.groovy.ftl");
            final ControllerGroovyClass controllerGroovyClass = ControllerGroovyClass.fromGroovyClass(groovyClass);
            controllerGroovyClass.setTemplateEngineConfig(springBootConfig.getTemplateEngineConfig());
            groovyConfig.getClasses().add(controllerGroovyClass);
            groovyConfig.getClasses()
                    .add(new GroovyClass(
                            springBootConfig.getEntryPoint().getPackageName(),
                            springBootConfig.getEntryPoint().getClassName(),
                            "templates/springboot/entrypoint.groovy.ftl"));
            if(springBootConfig.getTemplateEngineConfig().isUseFreemarker()) {
                groovyConfig.getResources()
                        .add(new Resource("templates", "freemarker.ftl", "templates/springboot/freemarker.ftl.ftl"));
            }
            if(springBootConfig.getTemplateEngineConfig().isUseVelocity()) {
                groovyConfig.getResources()
                        .add(new Resource("templates", "velocity.vm", "templates/springboot/velocity.vm.ftl"));
            }

            return;
        }
        final JavaConfig javaConfig = project.getConfig(JavaPlugin.JAVA_CONFIG_KEY);
        //
        final ControllerJavaClass controllerJavaClass = ControllerJavaClass.fromJavaClass(springBootConfig.getController());
        controllerJavaClass.setTemplateEngineConfig(springBootConfig.getTemplateEngineConfig());
        javaConfig.getClasses().add(controllerJavaClass);
        javaConfig.getClasses().add(springBootConfig.getEntryPoint());
        if(springBootConfig.getTemplateEngineConfig().isUseFreemarker()) {
            javaConfig.getResources()
                    .add(new Resource("templates", "freemarker.ftl", "templates/springboot/freemarker.ftl.ftl"));
        }
        if(springBootConfig.getTemplateEngineConfig().isUseVelocity()) {
            javaConfig.getResources()
                    .add(new Resource("templates", "velocity.vm", "templates/springboot/velocity.vm.ftl"));
        }
    }

    @Override
    public List<File> generateFiles(Project project) {
        // No op as it uses JavaPlugin to generate files
        return new ArrayList<>();
    }
}
