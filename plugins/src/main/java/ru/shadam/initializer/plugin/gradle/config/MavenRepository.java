package ru.shadam.initializer.plugin.gradle.config;

/**
 * @author sala
 */
public class MavenRepository extends Repository {
    public MavenRepository(String url) {
        super("maven { url " + url + " }");
    }
}
