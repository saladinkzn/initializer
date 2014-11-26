import ru.shadam.initializer.archive.Emitter;
import ru.shadam.initializer.archive.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class ZipFileStructureTest {
    public static void main(String[] args) throws IOException {
        List<File> files = new ArrayList<>();
        files.add(new File("src/main/java/Program.java", "public class Program {}"));
        files.add(new File("build.gradle", "apply plugin: 'java'"));
        try(FileOutputStream fileOutputStream = new FileOutputStream("hello2.zip")) {
            new Emitter().emitZipArchive(files, fileOutputStream);
        }
    }
}
