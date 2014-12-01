package ru.shadam.initializer.plugin.groovy;

import ru.shadam.initializer.archive.File;
import ru.shadam.initializer.plugin.Plugin;
import ru.shadam.initializer.plugin.gradle.GradleConfig;
import ru.shadam.initializer.plugin.gradle.GradlePlugin;
import ru.shadam.initializer.plugin.gradle.config.Dependency;
import ru.shadam.initializer.plugin.gradle.config.Repository;
import ru.shadam.initializer.plugin.groovy.config.GroovyClass;
import ru.shadam.initializer.plugin.jvm.config.Resource;
import ru.shadam.initializer.project.Project;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sala
 */
public class GroovyPlugin extends Plugin {
    public static final String GROOVY_CONFIG_KEY = "compileGroovy";

    public static GroovyConfig getConfig(Project project) {
        return (GroovyConfig) project.getConfig(GROOVY_CONFIG_KEY);
    }

    @Override
    public void register(Project project) {
        if(!project.isPluginRegistered(GradlePlugin.class)) {
            throw new IllegalStateException("Gradle plugin should be applied!");
        }
        project.registerConfig(GROOVY_CONFIG_KEY, GroovyConfig.class);
    }

    @Override
    public void configure(Project project) {
        final GradleConfig config = project.getConfig(GradlePlugin.GRADLE_CONFIG_KEY);
        config.getRepositories()
                .add(Repository.jcenter());
        config.getPlugins()
                .add(new ru.shadam.initializer.plugin.gradle.config.Plugin("groovy"));
        config.getDependencies()
                .add(new Dependency("org.codehaus.groovy", "groovy-all", "2.3.6"));
    }

    @Override
    public List<File> generateFiles(Project project) {
        final GroovyConfig groovyConfig = project.getConfig(GROOVY_CONFIG_KEY);
        //
        final List<File> files = new ArrayList<>();
        for (GroovyClass groovyClass : groovyConfig.getClasses()) {
            final String fileName = groovyClass.getPath(groovyConfig.getSourceDirectory());
            files.add(new File(fileName, project.renderFile(groovyClass.getTemplateName(), groovyClass)));
        }
        for(Resource resource: groovyConfig.getResources()) {
            final String fileName = resource.getPath(groovyConfig.getResourceDirectory());
            files.add(new File(fileName, project.renderFile(resource.getTemplateName(), resource)));
        }
        return files;
    }
}
