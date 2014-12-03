package ru.shadam.initializer.plugin.war;

import org.junit.Assert;
import org.junit.Test;
import ru.shadam.initializer.archive.File;
import ru.shadam.initializer.plugin.BasePluginTest;
import ru.shadam.initializer.plugin.gradle.GradlePlugin;
import ru.shadam.initializer.plugin.war.config.Filter;
import ru.shadam.initializer.plugin.war.config.Servlet;
import ru.shadam.initializer.project.Project;

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
}
