package ru.shadam.initializer.plugin.java;

import ru.shadam.initializer.archive.File;
import ru.shadam.initializer.plugin.Plugin;
import ru.shadam.initializer.project.Project;
import ru.shadam.initializer.plugin.gradle.GradleConfig;
import ru.shadam.initializer.plugin.gradle.GradlePlugin;
import ru.shadam.initializer.plugin.gradle.config.Extension;
import ru.shadam.initializer.plugin.java.config.JavaClass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 */
public class JavaPlugin extends Plugin {
    public static final String JAVA_CONFIG_KEY = "compileJava";

    @Override
    public void register(Project project) {
        if(!project.isPluginRegistered(GradlePlugin.class)) {
            throw new IllegalStateException("Gradle plugin should be applied!");
        }
        project.registerConfig(JAVA_CONFIG_KEY, JavaConfig.class);
    }

    @Override
    public void configure(Project project) {
        final JavaConfig javaConfig = project.getConfig(JAVA_CONFIG_KEY);
        //
        final GradleConfig gradleConfig = project.getConfig("gradle");
        //
        final HashMap<String, String> options = new HashMap<>();
        options.put("sourceCompatibility", Extension.wrapOptionValue(javaConfig.getSource()));
        options.put("targetCompatibility", Extension.wrapOptionValue(javaConfig.getTarget()));
        options.put("options.encoding", Extension.wrapOptionValue("UTF-8"));
        //
        gradleConfig.getExtensions().add(new Extension("compileJava", options));
        gradleConfig.getPlugins().add(new ru.shadam.initializer.plugin.gradle.config.Plugin("java"));
    }

    @Override
    public List<File> generateFiles(Project project) {
        final JavaConfig javaConfig = project.getConfig(JAVA_CONFIG_KEY);
        //
        final List<File> files = new ArrayList<>();
        for (JavaClass javaClass : javaConfig.getClasses()) {
            final String fileName = javaClass.getPath(javaConfig.getSourceDirectory());
            files.add(new File(fileName, project.renderFile(javaClass.getTemplateName(), javaClass)));
        }
        return files;
    }
}
