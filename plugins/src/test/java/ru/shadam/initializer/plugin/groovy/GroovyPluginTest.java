package ru.shadam.initializer.plugin.groovy;

import freemarker.template.Configuration;
import org.junit.Assert;
import org.junit.Test;
import ru.shadam.initializer.archive.Emitter;
import ru.shadam.initializer.archive.File;
import ru.shadam.initializer.plugin.gradle.GradlePlugin;
import ru.shadam.initializer.plugin.groovy.config.GroovyClass;
import ru.shadam.initializer.project.Project;
import ru.shadam.initializer.renderer.FreemarkerRenderer;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author sala
 */
public class GroovyPluginTest {
    @Test
    public void testGroovyPlugin() {
        final Configuration configuration = new Configuration();
        configuration.setDefaultEncoding("UTF-8");
        configuration.setOutputEncoding("UTF-8");
        configuration.setClassForTemplateLoading(GroovyPluginTest.class, "/");
        //
        final FreemarkerRenderer freemarkerRenderer = new FreemarkerRenderer(configuration);
        //
        final Project project = new Project(freemarkerRenderer);
        //
        project.registerPlugin(new GradlePlugin());
        project.registerPlugin(new GroovyPlugin());
        //
        final GroovyConfig config = GroovyPlugin.getConfig(project);
        config.getClasses()
                .add(new GroovyClass("org.example", "Program", "program.groovy.ftl"));
        //
        final List<File> execute = project.execute();
        //
        final Set<String> fileNames = new HashSet<>();
        for(File file: execute) {
            fileNames.add(file.getName());
        }
        //
        Assert.assertTrue(fileNames.contains("build.gradle"));
        Assert.assertTrue(fileNames.contains(Paths.get("src", "main", "groovy", "org", "example", "Program.groovy").toString()));
    }

    public static void main(String[] args) throws IOException {
        final Configuration configuration = new Configuration();
        configuration.setDefaultEncoding("UTF-8");
        configuration.setOutputEncoding("UTF-8");
        configuration.setClassForTemplateLoading(GroovyPluginTest.class, "/");
        //
        final FreemarkerRenderer freemarkerRenderer = new FreemarkerRenderer(configuration);
        //
        final Project project = new Project(freemarkerRenderer);
        //
        project.registerPlugin(new GradlePlugin());
        project.registerPlugin(new GroovyPlugin());
        //
        final GroovyConfig config = GroovyPlugin.getConfig(project);
        config.getClasses()
                .add(new GroovyClass("org.example", "Program", "program.groovy.ftl"));
        //
        final List<File> execute = project.execute();

        new Emitter().emitFolder(execute, ".\\build\\groovy");
    }
}
