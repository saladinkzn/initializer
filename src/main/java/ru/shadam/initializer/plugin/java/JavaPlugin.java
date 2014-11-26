package ru.shadam.initializer.plugin.java;

import ru.shadam.initializer.archive.File;
import ru.shadam.initializer.plugin.Plugin;
import ru.shadam.initializer.plugin.gradle.GradleConfig;
import ru.shadam.initializer.plugin.gradle.GradlePlugin;
import ru.shadam.initializer.plugin.gradle.config.Extension;
import ru.shadam.initializer.plugin.java.config.JavaClass;
import ru.shadam.initializer.project.Project;
import ru.shadam.initializer.renderer.FreemarkerRenderer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 */
public class JavaPlugin extends Plugin {
    public JavaPlugin(FreemarkerRenderer renderer) {
        super(renderer);
    }

    @Override
    public void register(Project project) {
        if(!project.isPluginRegistered(GradlePlugin.class)) {
            throw new IllegalStateException("Gradle plugin should be applied!");
        }
        project.registerConfig("compileJava", JavaConfig.class);
    }

    @Override
    public void configure(Project project) {
        final JavaConfig javaConfig = project.getConfig("compileJava");
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
        final JavaConfig javaConfig = project.getConfig("compileJava");
        //
        final List<File> files = new ArrayList<>();
        for (JavaClass javaClass : javaConfig.getClasses()) {
            files.add(new File(javaClass.getPath(javaConfig.getSourceDirectory()), renderFile("templates/program.java.ftl", javaClass)));
        }
        return files;
    }
}
