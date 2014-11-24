package ru.shadam.initializer.generator;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;

/**
 * @author sala
 */
public class FreemarkerUtil {
    private Configuration configuration;

    public FreemarkerUtil(Configuration configuration) {
        this.configuration = configuration;
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
        StringWriter stringWriter = new StringWriter();
        final Template template = configuration.getTemplate(templateName);
        template.process(dataModel, stringWriter);
        return stringWriter.toString();
    }
}
