package ru.shadam.initializer.plugin.war;

import ru.shadam.initializer.archive.File;
import ru.shadam.initializer.plugin.Plugin;
import ru.shadam.initializer.plugin.gradle.GradleConfig;
import ru.shadam.initializer.plugin.gradle.GradlePlugin;
import ru.shadam.initializer.plugin.jvm.config.Resource;
import ru.shadam.initializer.plugin.war.config.WebConfig;
import ru.shadam.initializer.project.Project;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sala
 */
public class WarPlugin extends Plugin {

    public static final String WAR_CONFIG_NAME = "war";

    @Override
    public void register(Project project) {
        if(!project.isPluginRegistered(GradlePlugin.class)) {
            throw new IllegalStateException("War plugin depends on Gradle plugin");
        }
        project.registerConfig(WAR_CONFIG_NAME, WarConfig.class);
    }

    @Override
    public void configure(Project project) {
        final GradleConfig config = (GradleConfig) project.getConfig(GradlePlugin.GRADLE_CONFIG_KEY);
        config.getPlugins()
                .add(new ru.shadam.initializer.plugin.gradle.config.Plugin("war"));

        final WarConfig warConfig = getWarConfig(project);
        if(warConfig.getWebConfig() == WebConfig.WEBXML) {
            warConfig.getResources()
                    .add(warConfig.getWebInfXmlResource());
        }
    }

    @Override
    public List<File> generateFiles(Project project) {
        final WarConfig warConfig = getWarConfig(project);
        //
        final List<File> files = new ArrayList<>();
        for(Resource resource: warConfig.getResources()) {
            final String fileName = resource.getPath(warConfig.getWebAppDir());
            files.add(new File(fileName, project.renderFile(resource.getTemplateName(), resource)));
        }
        return files;
    }

    public static WarConfig getWarConfig(Project project) {
        return project.getConfig(WAR_CONFIG_NAME);
    }
}
