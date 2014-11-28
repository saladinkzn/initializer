package ru.shadam.initializer.archive;

/**
 * Representation of generated file
 *
 * @author sala
 */
public class File {
    private final String name;
    private final String content;

    /**
     * Creates new file instance
     *
     * @param name path to file
     * @param content content of file
     */
    public File(String name, String content) {
        this.name = name;
        this.content = content;
    }

    /**
     * @return path to file
     */
    public String getName() {
        return name;
    }

    /**
     * @return content of file
     */
    public String getContent() {
        return content;
    }
}
