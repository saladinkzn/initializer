package ru.shadam.initializer.archive;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
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
}
