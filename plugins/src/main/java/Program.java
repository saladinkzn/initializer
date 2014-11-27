import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import ru.shadam.initializer.archive.Emitter;
import ru.shadam.initializer.project.Project;
import ru.shadam.initializer.renderer.FreemarkerRenderer;
import ru.shadam.initilizer.plugin.gradle.GradleConfig;
import ru.shadam.initilizer.plugin.gradle.GradlePlugin;
import ru.shadam.initilizer.plugin.java.JavaConfig;
import ru.shadam.initilizer.plugin.java.JavaPlugin;
import ru.shadam.initilizer.plugin.java.config.JavaClass;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

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
        final GradleConfig gradle = project.getConfig(GradlePlugin.GRADLE_CONFIG_KEY);
        gradle.setGradleVersion("2.2");
        //
        final JavaConfig javaConfig = project.getConfig(JavaPlugin.JAVA_CONFIG_KEY);
        //
        final JavaClass program = new JavaClass("org.example", "Program", "templates/program.java.ftl");
        javaConfig.getClasses().add(program);
        //
        try(FileOutputStream fileOutputStream = new FileOutputStream(".." + File.separator + "hello.zip")) {
            emitter.emitZipArchive(project.execute(), fileOutputStream);
        }
    }
}
