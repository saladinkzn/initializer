package ru.shadam.initializer.web.domain;

/**
 * @author sala
 */
public class GenerateSpringBootFormBean {
    private String group;
    private String artifact;
    private String version;
    private String bootVersion;
    private String language;
    private boolean useFreemarker;
    private boolean useVelocity;

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getArtifact() {
        return artifact;
    }

    public void setArtifact(String artifact) {
        this.artifact = artifact;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getBootVersion() {
        return bootVersion;
    }

    public void setBootVersion(String bootVersion) {
        this.bootVersion = bootVersion;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public boolean isUseFreemarker() {
        return useFreemarker;
    }

    public void setUseFreemarker(boolean useFreemarker) {
        this.useFreemarker = useFreemarker;
    }

    public boolean isUseVelocity() {
        return useVelocity;
    }

    public void setUseVelocity(boolean useVelocity) {
        this.useVelocity = useVelocity;
    }
}
