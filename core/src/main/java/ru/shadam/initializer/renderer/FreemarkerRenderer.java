package ru.shadam.initializer.renderer;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;

/**
 * @author sala
 */
public class FreemarkerRenderer {
    private Configuration configuration;

    public FreemarkerRenderer(Configuration configuration) {
        this.configuration = configuration;
    }

    /**
     * Renders template
     * @param templateName name of tempalte to be rendered
     * @return rendered template
     * @throws IOException
     * @throws TemplateException
     */
    public String renderTemplate(String templateName) throws IOException, TemplateException {
        return renderTemplate(templateName, new HashMap<>());
    }

    /**
     * Renders template
     * @param templateName name of tempalte to be rendered
     * @param dataModel dataModel, e.g. Map&lt;String, Object&gt;
     * @return rendered template
     * @throws IOException
     * @throws TemplateException
     */
    public String renderTemplate(String templateName, Object dataModel) throws IOException, TemplateException {
        final StringWriter stringWriter = new StringWriter();
        final Template template = configuration.getTemplate(templateName);
        template.process(dataModel, stringWriter);
        return stringWriter.toString();
    }
}
