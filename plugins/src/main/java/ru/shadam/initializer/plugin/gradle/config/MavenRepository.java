package ru.shadam.initializer.plugin.gradle.config;

/**
 * @author sala
 */
public class MavenRepository extends Repository {
    private String url;

    public String getUrl() {
        return url;
    }

    public MavenRepository(String url) {
        this.url = url;
    }

    @Override
    public String getString() {
        if(url == null) {
            throw new IllegalStateException("url must be specified");
        }
        return "maven { url " + url + " }";
    }
}
