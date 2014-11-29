package ru.shadam.initializer.plugin.springboot;

import ru.shadam.initializer.archive.File;
import ru.shadam.initializer.plugin.Plugin;
import ru.shadam.initializer.plugin.gradle.GradlePlugin;
import ru.shadam.initializer.plugin.java.JavaConfig;
import ru.shadam.initializer.plugin.java.JavaPlugin;
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
        if(!project.isPluginRegistered(JavaPlugin.class)) {
            throw new IllegalStateException("Spring-Boot depends on java plugin");
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
        final JavaConfig javaConfig = project.getConfig(JavaPlugin.JAVA_CONFIG_KEY);
        //
        javaConfig.getClasses().add(springBootConfig.getController());
        javaConfig.getClasses().add(springBootConfig.getEntryPoint());
    }

    @Override
    public List<File> generateFiles(Project project) {
        // No op as it uses JavaPlugin to generate files
        return new ArrayList<>();
    }
}
