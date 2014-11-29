package ru.shadam.initializer.plugin.springboot;

import freemarker.template.Configuration;
import org.junit.Assert;
import org.junit.Test;
import ru.shadam.initializer.archive.File;
import ru.shadam.initializer.plugin.gradle.GradlePlugin;
import ru.shadam.initializer.plugin.java.JavaPlugin;
import ru.shadam.initializer.project.Project;
import ru.shadam.initializer.renderer.FreemarkerRenderer;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author sala
 */
public class SpringBootPluginTest {
    @Test
    public void testSpringBootPlugin() {
        final Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(SpringBootPluginTest.class, "/");
        configuration.setDefaultEncoding("UTF-8");
        configuration.setOutputEncoding("UTF-8");
        //
        final FreemarkerRenderer freemarkerRenderer = new FreemarkerRenderer(configuration);
        //
        final Project project = new Project(freemarkerRenderer);
        //
        project.registerPlugins(new GradlePlugin(), new JavaPlugin(), new SpringBootPlugin());
        //
        final List<File> execute = project.execute();
        final Set<String> fileNames = new HashSet<>();
        for(File file: execute) {
            fileNames.add(file.getName());
        }
        //
        Assert.assertTrue(fileNames.contains("build.gradle"));
        Assert.assertTrue(fileNames.contains("src/main/java/org/example/Program.java"));
        Assert.assertTrue(fileNames.contains("src/main/java/org/example/HelloController.java"));
    }
}
