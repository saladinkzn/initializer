package ru.shadam.initializer.util;

import freemarker.template.TemplateException;
import ru.shadam.initializer.dto.File;
import ru.shadam.initializer.dto.GeneratorAndConfig;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sala
 */
public abstract class GeneratorUtil {


    public static List<File> processGenerators(List<GeneratorAndConfig<?>> generatorAndConfigs) throws IOException, TemplateException {
        final List<File> result = new ArrayList<>();
        for (GeneratorAndConfig<?> generatorAndConfig : generatorAndConfigs) {
            result.addAll(generatorAndConfig.generate());
        }
        return result;
    }


}
