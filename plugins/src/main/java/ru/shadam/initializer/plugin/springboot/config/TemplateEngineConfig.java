package ru.shadam.initializer.plugin.springboot.config;

/**
 * @author sala
 */
public class TemplateEngineConfig {
    private boolean useFreemarker;
    private boolean useVelocity;

    public TemplateEngineConfig() {
    }

    public TemplateEngineConfig(boolean useFreemarker, boolean useVelocity) {
        this.useFreemarker = useFreemarker;
        this.useVelocity = useVelocity;
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
