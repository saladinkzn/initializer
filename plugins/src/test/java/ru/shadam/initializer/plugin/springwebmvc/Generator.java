package ru.shadam.initializer.plugin.springwebmvc;

import freemarker.template.Configuration;
import ru.shadam.initializer.archive.Emitter;
import ru.shadam.initializer.archive.File;
import ru.shadam.initializer.plugin.gradle.GradlePlugin;
import ru.shadam.initializer.plugin.java.JavaPlugin;
import ru.shadam.initializer.plugin.war.WarPlugin;
import ru.shadam.initializer.project.Project;
import ru.shadam.initializer.renderer.FreemarkerRenderer;

import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 * @author sala
 */
public class Generator {
    public static void main(String[] args) throws Exception {
        Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(Generator.class, "/");
        configuration.setDefaultEncoding("UTF-8");
        configuration.setOutputEncoding("UTF-8");
        //
        final FreemarkerRenderer freemarkerRenderer = new FreemarkerRenderer(configuration);
        //
        final Project project = new Project(freemarkerRenderer);
        project.registerPlugins(new GradlePlugin(), new JavaPlugin(), new WarPlugin(), new SpringWebMVCPlugin());
        //
        final List<File> files = project.execute();

        final Emitter emitter = new Emitter();
        try(OutputStream fileOutputStream = Files.newOutputStream(Paths.get("helloSpringMvc.zip"), StandardOpenOption.CREATE, StandardOpenOption.WRITE)) {
            emitter.emitZipArchive(files, fileOutputStream);
        }

    }
}
