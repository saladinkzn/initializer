import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import ru.shadam.initializer.archive.Emitter;
import ru.shadam.initializer.generator.FreemarkerUtil;
import ru.shadam.initializer.util.GeneratorUtil;
import ru.shadam.initializer.generator.gradle.GradleGenerator;
import ru.shadam.initializer.generator.gradle.GradleConfig;
import ru.shadam.initializer.dto.GeneratorAndConfig;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sala
 */
public class Program {
    public static void main(String[] args) throws IOException, TemplateException {
        final Configuration configuration = new Configuration();
        configuration.setOutputEncoding("UTF-8");
        configuration.setDefaultEncoding("UTF-8");
        configuration.setClassForTemplateLoading(Program.class, "");
        final FreemarkerUtil freemarkerUtil = new FreemarkerUtil(configuration);
        final Emitter emitter = new Emitter();
        final GradleGenerator gradleGenerator = new GradleGenerator(freemarkerUtil);
        //
        final List<GeneratorAndConfig<?>> list = new ArrayList<>();
        list.add(new GeneratorAndConfig<>(new GradleConfig(), gradleGenerator));
        try(FileOutputStream fileOutputStream = new FileOutputStream("hello.zip")) {
            emitter.emitZipArchive(GeneratorUtil.processGenerators(list), fileOutputStream);
        }
    }
}
