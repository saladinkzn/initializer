package ru.shadam.initializer.plugin.gradle;

import ru.shadam.initializer.archive.File;
import ru.shadam.initializer.plugin.Plugin;
import ru.shadam.initializer.project.Project;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class GradlePlugin extends Plugin {
    public static final String GRADLE_CONFIG_KEY = "gradle";

    @Override
    public void register(Project project) {
        project.registerConfig(GRADLE_CONFIG_KEY, GradleConfig.class);
    }

    @Override
    public List<File> generateFiles(Project project) {
        final GradleConfig gradle = project.getConfig("gradle");
        //
        final List<File> files = new ArrayList<>();
        files.add(new File("build.gradle", project.renderFile("templates/gradle/build.gradle.ftl", gradle)));
        return files;
    }
}
