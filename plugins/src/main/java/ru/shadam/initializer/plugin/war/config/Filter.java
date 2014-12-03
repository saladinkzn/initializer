package ru.shadam.initializer.plugin.war.config;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author sala
 */
public class Filter {
    private String name;
    private String filterClass;
    private String urlPattern;
    private String servletName;
    private Map<String, String> initParams = new LinkedHashMap<>();

    public static Filter withUrlPattern(String name, String filterClass, String urlPattern) {
        final Filter filter = new Filter();
        filter.setName(name);
        filter.setFilterClass(filterClass);
        filter.setUrlPattern(urlPattern);
        return filter;
    }

    public static Filter withServletName(String name, String filterClass, String servletName) {
        final Filter filter = new Filter();
        filter.setName(name);
        filter.setFilterClass(filterClass);
        filter.setServletName(servletName);
        return filter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFilterClass() {
        return filterClass;
    }

    public void setFilterClass(String filterClass) {
        this.filterClass = filterClass;
    }

    public String getUrlPattern() {
        return urlPattern;
    }

    public void setUrlPattern(String urlPattern) {
        this.urlPattern = urlPattern;
    }

    public String getServletName() {
        return servletName;
    }

    public void setServletName(String servletName) {
        this.servletName = servletName;
    }

    public Map<String, String> getInitParams() {
        return initParams;
    }

    public void setInitParams(Map<String, String> initParams) {
        this.initParams = initParams;
    }
}
