package ru.shadam.initializer.archive;

import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

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

        final Map<String, File> outputFiles = new HashMap<>();
        try(ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(tempFile.toFile()));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(zipInputStream))) {
            for(ZipEntry zipEntry = zipInputStream.getNextEntry(); zipEntry != null; zipEntry = zipInputStream.getNextEntry()) {
                StringWriter stringWriter = new StringWriter();
                String str;
                while ((str = bufferedReader.readLine()) != null) {
                    stringWriter.append(str);
                }
                final String fileName = zipEntry.getName();
                outputFiles.put(fileName, new File(fileName, stringWriter.toString()));
            }
        }
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
        final Map<String, File> outputFiles = new HashMap<>();
        Files.walkFileTree(tempDir, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                final String fileName = tempDir.relativize(file).toString();
                try(FileInputStream fileInputStream = new FileInputStream(file.toFile());
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream))) {
                    final StringBuilder stringBuilder = new StringBuilder();
                    String str;
                    if((str = bufferedReader.readLine()) != null) {
                        stringBuilder.append(str);
                    }
                    while ((str = bufferedReader.readLine()) != null) {
                        stringBuilder.append("\n");
                        stringBuilder.append(str);
                    }
                    outputFiles.put(fileName, new File(fileName, stringBuilder.toString()));
                }
                return super.visitFile(file, attrs);
            }
        });
        for(File file: files) {
            Assert.assertTrue(outputFiles.containsKey(file.getName()));
            Assert.assertEquals(file.getContent(), outputFiles.get(file.getName()).getContent());
        }
    }
}
