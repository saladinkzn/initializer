package ru.shadam.initializer.plugin.war;

import freemarker.template.Configuration;
import org.junit.Assert;
import org.junit.Test;
import ru.shadam.initializer.archive.Emitter;
import ru.shadam.initializer.archive.File;
import ru.shadam.initializer.plugin.BasePluginTest;
import ru.shadam.initializer.plugin.gradle.GradleConfig;
import ru.shadam.initializer.plugin.gradle.GradlePlugin;
import ru.shadam.initializer.plugin.gradle.config.Dependency;
import ru.shadam.initializer.plugin.gradle.config.Repository;
import ru.shadam.initializer.plugin.war.config.Filter;
import ru.shadam.initializer.plugin.war.config.Servlet;
import ru.shadam.initializer.project.Project;
import ru.shadam.initializer.renderer.FreemarkerRenderer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author sala
 */
public class WarPluginTest extends BasePluginTest {
    @Test
    public void testWarPlugin() {
        final Project project = new Project(freemarkerRenderer);
        //
        project.registerPlugins(new GradlePlugin(), new WarPlugin());
        //
        final WarConfig warConfig = WarPlugin.getWarConfig(project);
        warConfig.getWebInfXmlResource().getServlets()
                .add(new Servlet("main", "org.springframework.web.servlet.DispatcherServlet", "/"));
        final Filter encodingFilter = Filter.withUrlPattern(
                "encodingFilter", "org.springframework.web.filter.CharacterEncodingFilter", "/"
        );
        final Map<String, String> initParams = encodingFilter.getInitParams();
        initParams.put("encoding", "UTF-8");
        warConfig.getWebInfXmlResource().getFilters()
                .add(encodingFilter);
        //
        final List<File> execute = project.execute();
        final Set<String> fileNames = new HashSet<>();
        for (File file : execute) {
            fileNames.add(file.getName());
        }

        Assert.assertTrue(fileNames.contains(Paths.get("src", "main", "webapp", "WEB-INF", "web.xml").toString()));
        Assert.assertTrue(fileNames.contains(Paths.get("build.gradle").toString()));

        for(File file: execute) {
            System.out.println(file.getName());
            System.out.println(file.getContent());
        }
    }

    public static void main(String[] args) throws IOException {
        Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(WarPluginTest.class, "/");
        configuration.setDefaultEncoding("UTF-8");
        configuration.setOutputEncoding("UTF-8");
        //
        FreemarkerRenderer freemarkerRenderer = new FreemarkerRenderer(configuration);
        //
        Project project = new Project(freemarkerRenderer);
        //
        project.registerPlugins(new GradlePlugin(), new WarPlugin());
        //
        final GradleConfig gradleConfig = project.getConfig(GradlePlugin.GRADLE_CONFIG_KEY);
        gradleConfig.getRepositories()
                .add(Repository.jcenter());
        gradleConfig.getDependencies()
                .add(new Dependency("org.springframework", "spring-webmvc", "4.1.2.RELEASE"));
        //
        final WarConfig warConfig = WarPlugin.getWarConfig(project);
        warConfig.getWebInfXmlResource().getServlets()
                .add(new Servlet("main", "org.springframework.web.servlet.DispatcherServlet", "/"));
        final Filter encodingFilter = Filter.withUrlPattern(
                "encodingFilter", "org.springframework.web.filter.CharacterEncodingFilter", "/"
        );
        final Map<String, String> initParams = encodingFilter.getInitParams();
        initParams.put("encoding", "UTF-8");
        warConfig.getWebInfXmlResource().getFilters()
                .add(encodingFilter);
        //
        final List<File> execute = project.execute();
        //
        final Emitter emitter = new Emitter();
        try(FileOutputStream fileOutputStream = new FileOutputStream("helloWar.zip")) {
            emitter.emitZipArchive(execute, fileOutputStream);
        }

    }
}
