package ru.shadam.initializer.generator.gradle;

import ru.shadam.initializer.dto.Dependency;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sala
 */
public class GradleConfig {
    private final String javaVersion;
    private final String gradleVersion;
    private final List<Dependency> dependencies;

    public GradleConfig() {
        this.javaVersion = "1.7";
        this.gradleVersion = "2.2";
        // TODO: immutable
        this.dependencies = new ArrayList<>();
    }

    public GradleConfig(String javaVersion, String gradleVersion, List<Dependency> dependencies) {
        this.javaVersion = javaVersion;
        this.gradleVersion = gradleVersion;
        this.dependencies = dependencies;
    }

    public String getJavaVersion() {
        return javaVersion;
    }

    public String getGradleVersion() {
        return gradleVersion;
    }

    public List<Dependency> getDependencies() {
        return dependencies;
    }
}
