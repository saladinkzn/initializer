package ru.shadam.initializer.plugin.java;

import freemarker.template.Configuration;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.shadam.initializer.archive.File;
import ru.shadam.initializer.plugin.gradle.GradlePlugin;
import ru.shadam.initializer.plugin.java.config.JavaClass;
import ru.shadam.initializer.plugin.jvm.config.Resource;
import ru.shadam.initializer.project.Project;
import ru.shadam.initializer.renderer.FreemarkerRenderer;

import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author sala
 */
public class JavaPluginTest {
    private FreemarkerRenderer freemarkerRenderer;

    @Before
    public void initTest() {
        final Configuration configuration = new Configuration();
        configuration.setDefaultEncoding("UTF-8");
        configuration.setOutputEncoding("UTF-8");
        configuration.setClassForTemplateLoading(JavaPluginTest.class, "/");
        //
        freemarkerRenderer = new FreemarkerRenderer(configuration);
    }

    @Test
    public void testJavaPlugin() {
        final Project project = new Project(freemarkerRenderer);
        //
        project.registerPlugin(new GradlePlugin());
        project.registerPlugin(new JavaPlugin());
        //
        final JavaConfig config = project.getConfig(JavaPlugin.JAVA_CONFIG_KEY);
        Assert.assertEquals("1.7", config.getSource());
        Assert.assertEquals("1.7", config.getTarget());
        Assert.assertEquals("src/main/java", config.getSourceDirectory());
        //
        config.getClasses()
                .add(new JavaClass("org.example", "Program", "program.java.ftl"));
        //
        final List<File> files = project.execute();
        final Set<String> fileNames = new HashSet<>();
        //
        for (File file : files) {
            fileNames.add(file.getName());
        }
        //
        Assert.assertTrue(fileNames.contains("build.gradle"));
        Assert.assertTrue(fileNames.contains("src/main/java/org/example/Program.java"));
    }

    @Test
    public void testJavaResourcePlugin() {
        final Project project = new Project(freemarkerRenderer);
        //
        project.registerPlugins(new GradlePlugin(), new JavaPlugin());
        //
        final JavaConfig config = project.getConfig(JavaPlugin.JAVA_CONFIG_KEY);
        //
        config.getClasses()
                .add(new JavaClass("org.example", "Program", "program.java.ftl"));
        config.getResources()
                .add(new Resource("", "application.properties", "application.properties.ftl"));
        //
        final List<File> files = project.execute();
        final Set<String> fileNames = new HashSet<>();
        //
        for (File file: files ) {
            fileNames.add(file.getName());
        }
        //
        Assert.assertTrue(fileNames.contains("build.gradle"));
        Assert.assertTrue(fileNames.contains(Paths.get("src", "main", "java", "org", "example", "Program.java").toString()));
        Assert.assertTrue(fileNames.contains(Paths.get("src", "main", "resources", "application.properties").toString()));
    }
}
