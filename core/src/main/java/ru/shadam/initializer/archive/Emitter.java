package ru.shadam.initializer.archive;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author sala
 */
public class Emitter {
    public void emitZipArchive(List<File> files, OutputStream outputStream) throws IOException {
        try(ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream)) {
            for(File file: files) {
                final String fileName = file.getName();
                final String content = file.getContent();
                final ZipEntry zipEntry = new ZipEntry(fileName);
                zipOutputStream.putNextEntry(zipEntry);
                zipOutputStream.write(content.getBytes(Charset.forName("UTF-8")));
                zipOutputStream.closeEntry();
            }
        }
    }

    public void emitFolder(List<File> files, String rootCategory) throws IOException {
        for(File file: files) {
            final Path path = Paths.get(rootCategory, file.getName());
            final java.io.File dirFile = path.getParent().toFile();
            final java.io.File ioFile = path.toFile();
            dirFile.mkdirs();
            try(FileOutputStream fileOutputStream = new FileOutputStream(ioFile);
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream))) {
                bufferedWriter.write(file.getContent());
            }
        }
    }
}
