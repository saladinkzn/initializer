package ru.shadam.initializer.plugin;

import freemarker.template.TemplateException;
import ru.shadam.initializer.archive.File;
import ru.shadam.initializer.project.Project;
import ru.shadam.initializer.renderer.FreemarkerRenderer;

import java.io.IOException;
import java.util.List;

/**
 *
 */
public abstract class Plugin {
    private final FreemarkerRenderer renderer;

    public Plugin(FreemarkerRenderer renderer) {
        this.renderer = renderer;
    }

    public abstract void register(Project project);

    public void configure(Project project) {}

    public abstract List<File> generateFiles(Project project);

    // TODO: extract renderer interface?
    protected String renderFile(String templateName, Object dataModel) {
        try {
            return renderer.renderTemplate(templateName, dataModel);
        } catch (IOException | TemplateException e) {
            // TODO: add specific exception
            throw new RuntimeException(e);
        }
    }
}
