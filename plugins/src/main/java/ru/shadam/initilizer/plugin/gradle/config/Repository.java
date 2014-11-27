package ru.shadam.initilizer.plugin.gradle.config;

/**
 * @author sala
 */
public abstract class Repository {
    private static final Repository JCENTER_REPOSITORY = new JcenterRepository();
    private static final Repository MAVEN_CENTRAL_REPOSITORY = new MavenCentralRepository();

    private static class JcenterRepository extends Repository {
        @Override
        public String getString() {
            return "jcenter()";
        }
    }

    private static class MavenCentralRepository extends Repository {
        @Override
        public String getString() {
            return "mavenCentral()";
        }
    }

    public abstract String getString();

    public static Repository jcenter() {
        return JCENTER_REPOSITORY;
    }

    public static Repository mavenCentral() {
        return MAVEN_CENTRAL_REPOSITORY;
    }

    public static Repository maven(String url) {
        return new MavenRepository(url);
    }
}
