package util;

import ru.shadam.initializer.archive.File;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * @author sala
 */
public abstract class Collector {
    public static Map<String, File> collectFromZipArchive(InputStream inputStream) throws IOException {
        final Map<String, File> outputFiles = new HashMap<>();
        try(ZipInputStream zipInputStream = new ZipInputStream(inputStream);
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
        return outputFiles;
    }

    public static Map<String, File> collectFromFolder(String rootFolder) throws IOException {
        final Path tempDir = Paths.get(rootFolder);
        final Map<String, File> outputFiles = new HashMap<>();
        Files.walkFileTree(tempDir, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                final String fileName = tempDir.relativize(file).toString();
                try (FileInputStream fileInputStream = new FileInputStream(file.toFile());
                     BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream))) {
                    final StringBuilder stringBuilder = new StringBuilder();
                    String str;
                    if ((str = bufferedReader.readLine()) != null) {
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
        return outputFiles;
    }
}
