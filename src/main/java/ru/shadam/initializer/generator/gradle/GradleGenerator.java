package ru.shadam.initializer.generator.gradle;

import freemarker.template.TemplateException;
import ru.shadam.initializer.generator.FreemarkerUtil;
import ru.shadam.initializer.dto.File;
import ru.shadam.initializer.generator.Generator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sala
 */
public class GradleGenerator extends Generator<GradleConfig> {
    public GradleGenerator(FreemarkerUtil freemarkerUtil) {
        super(freemarkerUtil);
    }

    @Override
    public List<File> generateFiles(GradleConfig config) throws IOException, TemplateException {
        final List<File> result = new ArrayList<>();
        final String content = freemarkerUtil.renderTemplate("ru/shadam/initializer/generator/gradle/build.gradle.ftl", config);
        result.add(new File("build.gradle", content));
        return result;
    }
}
