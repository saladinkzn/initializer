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
    /**
     * Create zip archive for list of files
     *
     * @param files list of files
     * @param outputStream output stream to write zip archive to
     * @throws IOException
     */
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

    /**
     * Create folder for list of files
     *
     * @param files list of files
     * @param rootCategory root folder
     * @throws IOException
     */
    public void emitFolder(List<File> files, String rootCategory) throws IOException {
        for(File file: files) {
            final Path path = Paths.get(rootCategory, file.getName());
            final java.io.File dirFile = path.getParent().toFile();
            final java.io.File ioFile = path.toFile();
            if(!dirFile.exists() && !dirFile.mkdirs()) {
                throw new IllegalStateException("Subfolder was not created");
            }
            try(FileOutputStream fileOutputStream = new FileOutputStream(ioFile);
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "UTF-8");
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter)) {
                bufferedWriter.write(file.getContent());
            }
        }
    }
}
