package ru.shadam.initilizer.plugin.springboot;

import ru.shadam.initializer.archive.File;
import ru.shadam.initializer.plugin.Plugin;
import ru.shadam.initializer.project.Project;
import ru.shadam.initializer.renderer.FreemarkerRenderer;
import ru.shadam.initilizer.plugin.gradle.GradleConfig;
import ru.shadam.initilizer.plugin.gradle.GradlePlugin;
import ru.shadam.initilizer.plugin.gradle.config.Dependency;
import ru.shadam.initilizer.plugin.gradle.config.Repository;
import ru.shadam.initilizer.plugin.java.JavaConfig;
import ru.shadam.initilizer.plugin.java.JavaPlugin;
import ru.shadam.initilizer.plugin.java.config.JavaClass;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sala
 */
public class SpringBootPlugin extends Plugin {

    public static final String SPRINGBOOT_CONFIG_KEY = "springboot";

    public SpringBootPlugin(FreemarkerRenderer renderer) {
        super(renderer);
    }

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
//        final JavaClass program = new JavaClass("org.example", "Program");

        //
//        javaConfig.getClasses()
//                .add(program);

    }

    @Override
    public List<File> generateFiles(Project project) {
        final JavaConfig javaConfig = project.getConfig(JavaPlugin.JAVA_CONFIG_KEY);
        final JavaClass entryPoint = new JavaClass("org.example", "Program");
        final JavaClass controller = new JavaClass("org.example", "HelloController");
        //
        final List<File> files = new ArrayList<>();
        files.add(new File(entryPoint.getPath(javaConfig.getSourceDirectory()), renderFile("templates/springboot/entrypoint.java.ftl", entryPoint)));
        files.add(new File(controller.getPath(javaConfig.getSourceDirectory()), renderFile("templates/springboot/controller.java.ftl", controller)));
        return files;

    }
}
