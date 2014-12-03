package ru.shadam.initializer.plugin;

import freemarker.template.Configuration;
import org.junit.Before;
import ru.shadam.initializer.renderer.FreemarkerRenderer;

/**
 * @author sala
 */
public class BasePluginTest {
    protected FreemarkerRenderer freemarkerRenderer;


    @Before
    public void before() {
        final Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(BasePluginTest.class, "/");
        configuration.setDefaultEncoding("UTF-8");
        configuration.setOutputEncoding("UTF-8");
        //
        freemarkerRenderer = new FreemarkerRenderer(configuration);
    }
}
