import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import ru.shadam.initializer.archive.Emitter;
import ru.shadam.initializer.plugin.gradle.GradleConfig;
import ru.shadam.initializer.plugin.gradle.GradlePlugin;
import ru.shadam.initializer.plugin.java.JavaConfig;
import ru.shadam.initializer.plugin.java.JavaPlugin;
import ru.shadam.initializer.plugin.java.config.JavaClass;
import ru.shadam.initializer.project.Project;
import ru.shadam.initializer.renderer.FreemarkerRenderer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author sala
 */
public class Program {
    public static void main(String[] args) throws IOException, TemplateException {
        final Configuration configuration = new Configuration();
        configuration.setOutputEncoding("UTF-8");
        configuration.setDefaultEncoding("UTF-8");
        configuration.setClassForTemplateLoading(Program.class, "");
        //
        final FreemarkerRenderer renderer = new FreemarkerRenderer(configuration);
        //
        final Emitter emitter = new Emitter();
        final GradlePlugin gradlePlugin = new GradlePlugin(renderer);
        final JavaPlugin javaPlugin = new JavaPlugin(renderer);
        //
        final Project project = new Project();
        // register plugin
        project.registerPlugin(gradlePlugin);
        project.registerPlugin(javaPlugin);
        //
        final GradleConfig gradle = project.getConfig("gradle");
        gradle.setGradleVersion("2.2");
        //
        final JavaConfig javaConfig = project.getConfig("compileJava");
        final ArrayList<JavaClass> classes = new ArrayList<>();
        final JavaClass program = new JavaClass();
        program.setPackageName("org.example");
        program.setClassName("Program");
        classes.add(program);
        javaConfig.setClasses(
                classes
        );
        //
        try(FileOutputStream fileOutputStream = new FileOutputStream("hello.zip")) {
            emitter.emitZipArchive(project.execute(), fileOutputStream);
        }
    }
}
