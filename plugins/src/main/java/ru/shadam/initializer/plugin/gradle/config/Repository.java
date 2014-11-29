package ru.shadam.initializer.plugin.gradle.config;

/**
 * @author sala
 */
public abstract class Repository {
    private static final Repository JCENTER_REPOSITORY = new JcenterRepository();
    private static final Repository MAVEN_CENTRAL_REPOSITORY = new MavenCentralRepository();
    private final String string;

    public Repository(String string) {
        this.string = string;
    }

    private static class JcenterRepository extends Repository {

        public JcenterRepository() {
            super("jcenter()");
        }
    }

    private static class MavenCentralRepository extends Repository {

        public MavenCentralRepository() {
            super("mavenCentral()");
        }
    }

    public String getString() {
        return string;
    }

    public static Repository jcenter() {
        return JCENTER_REPOSITORY;
    }

    public static Repository mavenCentral() {
        return MAVEN_CENTRAL_REPOSITORY;
    }

    public static Repository maven(String url) {
        return new MavenRepository(url);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Repository that = (Repository) o;

        if (string != null ? !string.equals(that.string) : that.string != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return string != null ? string.hashCode() : 0;
    }
}
