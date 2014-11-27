package ru.shadam.initilizer.plugin.springboot;

/**
 * @author sala
 */
public class SpringBootConfig {
    private String springBootVersion = "1.1.9.RELEASE";

    public String getSpringBootVersion() {
        return springBootVersion;
    }

    public void setSpringBootVersion(String springBootVersion) {
        this.springBootVersion = springBootVersion;
    }
}
