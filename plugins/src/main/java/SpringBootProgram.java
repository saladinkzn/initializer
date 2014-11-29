import freemarker.template.Configuration;
import ru.shadam.initializer.archive.Emitter;
import ru.shadam.initializer.archive.File;
import ru.shadam.initializer.project.Project;
import ru.shadam.initializer.renderer.FreemarkerRenderer;
import ru.shadam.initializer.plugin.gradle.GradlePlugin;
import ru.shadam.initializer.plugin.java.JavaPlugin;
import ru.shadam.initializer.plugin.springboot.SpringBootPlugin;

import java.io.FileOutputStream;
import java.util.List;

/**
 * @author sala
 */
public class SpringBootProgram {
    public static void main(String[] args) throws Exception {
        final Configuration configuration = new Configuration();
        configuration.setOutputEncoding("UTF-8");
        configuration.setDefaultEncoding("UTF-8");
        configuration.setClassForTemplateLoading(Program.class, "");
        //
        final FreemarkerRenderer renderer = new FreemarkerRenderer(configuration);
        //
        final Emitter emitter = new Emitter();
        final GradlePlugin gradlePlugin = new GradlePlugin();
        final JavaPlugin javaPlugin = new JavaPlugin();
        final SpringBootPlugin springBootPlugin = new SpringBootPlugin();

        final Project project = new Project(renderer);
        project.registerPlugins(gradlePlugin, javaPlugin, springBootPlugin);
        //
        final List<File> files = project.execute();
        try(FileOutputStream fileOutputStream = new FileOutputStream("helloBoot.zip")) {
            emitter.emitZipArchive(files, fileOutputStream);
        }
    }
}
