package ru.shadam.initializer.archive;

import org.junit.Assert;
import org.junit.Test;
import util.Collector;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author sala
 */
public class EmitterTest {
    @Test
    public void testZipArchive() throws IOException {
        final List<File> files = new ArrayList<>();
        files.add(new File("test.txt", "Test, test, test"));
        files.add(new File("src/main/java/test.txt", "test, Test, TEST"));
        //
        final Path tempFile = Files.createTempFile("temp", ".zip");

        final Emitter emitter = new Emitter();
        try(FileOutputStream fileOutputStream = new FileOutputStream(tempFile.toFile())) {
            emitter.emitZipArchive(files, fileOutputStream);
        }

        final FileInputStream fileInputStream = new FileInputStream(tempFile.toFile());
        final Map<String, File> outputFiles = Collector.collectFromZipArchive(fileInputStream);
        for(File file: files) {
            Assert.assertTrue(outputFiles.containsKey(file.getName()));
            Assert.assertEquals(file.getContent(), outputFiles.get(file.getName()).getContent());
        }
    }

    @Test
    public void testEmitDirectory() throws IOException {
        final List<File> files = new ArrayList<>();
        final String separator = java.io.File.separator;
        files.add(new File("src" + separator + "main" + separator + "java" + separator + "Program.java", "public class Program {\n\n}"));

        final Path tempDir = Files.createTempDirectory("temp");

        final Emitter emitter = new Emitter();
        emitter.emitFolder(files, tempDir.toString());
        //
        final Map<String, File> outputFiles = Collector.collectFromFolder(tempDir.toString());
        for(File file: files) {
            Assert.assertTrue(outputFiles.containsKey(file.getName()));
            Assert.assertEquals(file.getContent(), outputFiles.get(file.getName()).getContent());
        }
    }
}
