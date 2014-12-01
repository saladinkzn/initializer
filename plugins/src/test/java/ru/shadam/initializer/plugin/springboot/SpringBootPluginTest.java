package ru.shadam.initializer.plugin.springboot;

import freemarker.template.Configuration;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.shadam.initializer.archive.File;
import ru.shadam.initializer.plugin.gradle.GradlePlugin;
import ru.shadam.initializer.plugin.java.JavaPlugin;
import ru.shadam.initializer.project.Project;
import ru.shadam.initializer.renderer.FreemarkerRenderer;

import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author sala
 */
public class SpringBootPluginTest {
    private FreemarkerRenderer freemarkerRenderer;


    @Before
    public void before() {
        final Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(SpringBootPluginTest.class, "/");
        configuration.setDefaultEncoding("UTF-8");
        configuration.setOutputEncoding("UTF-8");
        //
        freemarkerRenderer = new FreemarkerRenderer(configuration);
    }

    @Test
    public void testSpringBootPlugin() {
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
        Assert.assertTrue(fileNames.contains(Paths.get("src", "main", "java", "org", "example", "Program.java").toString()));
        Assert.assertTrue(fileNames.contains(Paths.get("src", "main", "java", "org", "example", "HelloController.java").toString()));
    }

    @Test
    public void testSpringBootPluginWithFreemarker() {
        final Project project = new Project(freemarkerRenderer);

        project.registerPlugins(new GradlePlugin(), new JavaPlugin(), new SpringBootPlugin());
        //
        final SpringBootConfig springBootConfig = project.getConfig(SpringBootPlugin.SPRINGBOOT_CONFIG_KEY);
        //
        springBootConfig.getTemplateEngineConfig().setUseFreemarker(true);
        //
        final List<File> execute = project.execute();
        final Set<String> fileNames = new HashSet<>();
        for(File file: execute) {
            fileNames.add(file.getName());
        }
        //
        Assert.assertTrue(fileNames.contains("build.gradle"));
        Assert.assertTrue(fileNames.contains(Paths.get("src", "main", "java", "org", "example", "Program.java").toString()));
        Assert.assertTrue(fileNames.contains(Paths.get("src", "main", "java", "org", "example", "HelloController.java").toString()));
        Assert.assertTrue(fileNames.contains(Paths.get("src", "main", "resources", "templates", "freemarker.ftl").toString()));
    }

    @Test
    public void testSpringBootPluginWithVelocity() {
        final Project project = new Project(freemarkerRenderer);

        project.registerPlugins(new GradlePlugin(), new JavaPlugin(), new SpringBootPlugin());
        //
        final SpringBootConfig springBootConfig = project.getConfig(SpringBootPlugin.SPRINGBOOT_CONFIG_KEY);
        //
        springBootConfig.getTemplateEngineConfig().setUseVelocity(true);
        //
        final List<File> execute = project.execute();
        final Set<String> fileNames = new HashSet<>();
        for(File file: execute) {
            fileNames.add(file.getName());
        }
        //
        Assert.assertTrue(fileNames.contains("build.gradle"));
        Assert.assertTrue(fileNames.contains(Paths.get("src", "main", "java", "org", "example", "Program.java").toString()));
        Assert.assertTrue(fileNames.contains(Paths.get("src", "main", "java", "org", "example", "HelloController.java").toString()));
        Assert.assertTrue(fileNames.contains(Paths.get("src", "main", "resources", "templates", "velocity.vm").toString()));
    }
}
