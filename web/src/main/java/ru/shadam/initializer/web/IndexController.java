package ru.shadam.initializer.web;

import freemarker.template.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.shadam.initializer.archive.Emitter;
import ru.shadam.initializer.archive.File;
import ru.shadam.initializer.plugin.Plugin;
import ru.shadam.initializer.plugin.gradle.GradleConfig;
import ru.shadam.initializer.plugin.gradle.GradlePlugin;
import ru.shadam.initializer.plugin.groovy.GroovyPlugin;
import ru.shadam.initializer.plugin.java.JavaPlugin;
import ru.shadam.initializer.plugin.springboot.SpringBootConfig;
import ru.shadam.initializer.plugin.springboot.SpringBootPlugin;
import ru.shadam.initializer.project.Project;
import ru.shadam.initializer.renderer.FreemarkerRenderer;
import ru.shadam.initializer.web.domain.GenerateSpringBootFormBean;

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
        final GradlePlugin gradlePlugin = new GradlePlugin();
        final Plugin languagePlugin;
        switch (generateSpringBootFormBean.getLanguage()) {
            case "java":
                languagePlugin = new JavaPlugin();
                break;
            case "groovy":
                languagePlugin = new GroovyPlugin();
                break;
            default:
                throw new IllegalArgumentException("invalid language: " + generateSpringBootFormBean.getLanguage());
        }
        final SpringBootPlugin springBootPlugin = new SpringBootPlugin();

        final Project project = new Project(renderer);
        project.registerPlugins(gradlePlugin, languagePlugin, springBootPlugin);
        //
        final GradleConfig gradleConfig = project.getConfig(GradlePlugin.GRADLE_CONFIG_KEY);
        gradleConfig.setGroup(generateSpringBootFormBean.getGroup());
        gradleConfig.setVersion(generateSpringBootFormBean.getVersion());
        // TODO: artifact
        //
        final SpringBootConfig config = project.getConfig(SpringBootPlugin.SPRINGBOOT_CONFIG_KEY);
        config.setSpringBootVersion(generateSpringBootFormBean.getBootVersion());
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
