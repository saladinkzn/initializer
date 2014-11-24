package ru.shadam.initializer.generator;

import freemarker.template.TemplateException;
import ru.shadam.initializer.dto.File;

import java.io.IOException;
import java.util.List;

/**
 * @author sala
 */
public abstract class Generator<T> {
    protected final FreemarkerUtil freemarkerUtil;

    public Generator(FreemarkerUtil freemarkerUtil) {
        this.freemarkerUtil = freemarkerUtil;
    }

    public abstract List<File> generateFiles(T config) throws IOException, TemplateException;
}
