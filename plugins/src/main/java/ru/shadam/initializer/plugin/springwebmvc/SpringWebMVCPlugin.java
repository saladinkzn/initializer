package ru.shadam.initializer.plugin.springwebmvc;

import ru.shadam.initializer.archive.File;
import ru.shadam.initializer.plugin.Plugin;
import ru.shadam.initializer.plugin.gradle.GradleConfig;
import ru.shadam.initializer.plugin.gradle.GradlePlugin;
import ru.shadam.initializer.plugin.gradle.config.Dependency;
import ru.shadam.initializer.plugin.gradle.config.Repository;
import ru.shadam.initializer.plugin.groovy.GroovyPlugin;
import ru.shadam.initializer.plugin.java.JavaConfig;
import ru.shadam.initializer.plugin.java.JavaPlugin;
import ru.shadam.initializer.plugin.java.config.JavaClass;
import ru.shadam.initializer.plugin.jvm.config.Resource;
import ru.shadam.initializer.plugin.war.WarConfig;
import ru.shadam.initializer.plugin.war.WarPlugin;
import ru.shadam.initializer.plugin.war.config.Filter;
import ru.shadam.initializer.plugin.war.config.Servlet;
import ru.shadam.initializer.project.Project;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sala
 */
public class SpringWebMVCPlugin extends Plugin {

    public static final String SPRINGWEBMVC_CONFIG_KEY = "springwebmvc";

    @Override
    public void register(Project project) {
        if(!project.isPluginRegistered(GradlePlugin.class)) {
            throw new IllegalStateException("SpringWebMVC depends on Gradle");
        }
        if(!project.isAnyPluginRegistered(JavaPlugin.class, GroovyPlugin.class)) {
            throw new IllegalStateException("SpringWebMVC depends on java or groovy");
        }
        if(!project.isPluginRegistered(WarPlugin.class)) {
            throw new IllegalStateException("SpringWebMVC depends on War");
        }
        //
        project.registerConfig(SPRINGWEBMVC_CONFIG_KEY, SpringWebMVCConfig.class);
    }

    @Override
    public void configure(Project project) {
        final GradleConfig gradleConfig = project.getConfig(GradlePlugin.GRADLE_CONFIG_KEY);
        final JavaConfig javaConfig = project.getConfig(JavaPlugin.JAVA_CONFIG_KEY);
        final WarConfig warConfig = WarPlugin.getWarConfig(project);
        final SpringWebMVCConfig springWebMVCConfig = project.getConfig(SPRINGWEBMVC_CONFIG_KEY);
        //
        gradleConfig.getRepositories()
                .add(Repository.jcenter());
        gradleConfig.getDependencies()
                .add(new Dependency("org.springframework", "spring-webmvc", springWebMVCConfig.getSpringVersion()));
        gradleConfig.getDependencies()
                .add(new Dependency("org.springframework", "spring-context-support", springWebMVCConfig.getSpringVersion()));
        gradleConfig.getDependencies()
                .add(new Dependency("org.freemarker", "freemarker", "2.3.20"));
        //
        javaConfig.getClasses()
                .add(new JavaClass("org.example.controllers", "DefaultController", "templates/springwebmvc/defaultcontroller.java.ftl"));
        //
        warConfig.getServlets()
                .add(new Servlet("main", "org.springframework.web.servlet.DispatcherServlet", "/"));
        warConfig.getFilters()
                .add(Filter.withUrlPattern("encodingFilter", "org.springframework.web.filter.CharacterEncodingFilter", "/"));
        warConfig.getResources()
                .add(new Resource("WEB-INF", "main-servlet.xml", "templates/springwebmvc/main-servlet.xml.ftl"));
        warConfig.getResources()
                .add(new Resource("WEB-INF/views", "index.ftl", "templates/springwebmvc/index.ftl.ftl"));

    }

    @Override
    public List<File> generateFiles(Project project) {
        return new ArrayList<>();
    }
}
