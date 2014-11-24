package ru.shadam.initializer.dto;

import freemarker.template.TemplateException;
import ru.shadam.initializer.generator.Generator;

import java.io.IOException;
import java.util.List;

/**
 * @author sala
 */
public class GeneratorAndConfig<T> {
    private final Generator<T> generator;
    private final T config;

    public GeneratorAndConfig(T config, Generator<T> generator) {
        this.config = config;
        this.generator = generator;
    }

    public List<File> generate() throws IOException, TemplateException {
        return generator.generateFiles(config);
    }
}
