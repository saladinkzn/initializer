package ru.shadam.initializer.plugin.war.config;

/**
 * @author sala
 */
public class Servlet {
    private String servletClass;
    private String name;
    private String urlPattern;
    private boolean loadOnStartup;

    public Servlet(String name, String servletClass, String urlPattern) {
        this.servletClass = servletClass;
        this.name = name;
        this.urlPattern = urlPattern;
    }

    public String getServletClass() {
        return servletClass;
    }

    public void setServletClass(String servletClass) {
        this.servletClass = servletClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlPattern() {
        return urlPattern;
    }

    public void setUrlPattern(String urlPattern) {
        this.urlPattern = urlPattern;
    }

    public boolean isLoadOnStartup() {
        return loadOnStartup;
    }

    public void setLoadOnStartup(boolean loadOnStartup) {
        this.loadOnStartup = loadOnStartup;
    }
}
