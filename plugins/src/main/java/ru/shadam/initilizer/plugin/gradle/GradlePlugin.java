package ru.shadam.initilizer.plugin.gradle;

import ru.shadam.initializer.archive.File;
import ru.shadam.initializer.project.Project;
import ru.shadam.initializer.renderer.FreemarkerRenderer;
import ru.shadam.initializer.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class GradlePlugin extends Plugin {
    public static final String GRADLE_CONFIG_KEY = "gradle";

    public GradlePlugin(FreemarkerRenderer renderer) {
        super(renderer);
    }

    @Override
    public void register(Project project) {
        project.registerConfig(GRADLE_CONFIG_KEY, GradleConfig.class);
    }

    @Override
    public List<File> generateFiles(Project project) {
        final GradleConfig gradle = project.getConfig("gradle");
        //
        final List<File> files = new ArrayList<>();
        files.add(new File("build.gradle", renderFile("templates/build.gradle.ftl", gradle)));
        return files;
    }
}