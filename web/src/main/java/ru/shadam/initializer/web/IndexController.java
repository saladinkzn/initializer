package ru.shadam.initializer.web;

import freemarker.template.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.shadam.initializer.archive.Emitter;
import ru.shadam.initializer.archive.File;
import ru.shadam.initializer.project.Project;
import ru.shadam.initializer.renderer.FreemarkerRenderer;
import ru.shadam.initializer.web.domain.GenerateSpringBootFormBean;
import ru.shadam.initilizer.plugin.gradle.GradlePlugin;
import ru.shadam.initilizer.plugin.java.JavaPlugin;
import ru.shadam.initilizer.plugin.springboot.SpringBootConfig;
import ru.shadam.initilizer.plugin.springboot.SpringBootPlugin;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * @author sala
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    public String renderIndex() {
        return "index";
    }

    @RequestMapping("/generate/springBoot.zip")
    public ResponseEntity<byte[]> generateBootProject(GenerateSpringBootFormBean generateSpringBootFormBean) throws IOException {
        final Configuration configuration = new Configuration();
        configuration.setOutputEncoding("UTF-8");
        configuration.setDefaultEncoding("UTF-8");
        configuration.setClassForTemplateLoading(Program.class, "/");
        //
        final FreemarkerRenderer renderer = new FreemarkerRenderer(configuration);
        //
        final Emitter emitter = new Emitter();
        final GradlePlugin gradlePlugin = new GradlePlugin(renderer);
        final JavaPlugin javaPlugin = new JavaPlugin(renderer);
        final SpringBootPlugin springBootPlugin = new SpringBootPlugin(renderer);

        final Project project = new Project();
        project.registerPlugins(gradlePlugin, javaPlugin, springBootPlugin);
        //
        final SpringBootConfig config = project.getConfig(SpringBootPlugin.SPRINGBOOT_CONFIG_KEY);
        config.setSpringBootVersion(generateSpringBootFormBean.getVersion());
        //
        final List<File> files = project.execute();
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try(OutputStream fileOutputStream = byteArrayOutputStream) {
            emitter.emitZipArchive(files, fileOutputStream);
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.parseMediaType("application/zip"))
                .body(byteArrayOutputStream.toByteArray());
    }
}
