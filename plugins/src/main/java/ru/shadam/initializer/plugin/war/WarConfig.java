package ru.shadam.initializer.plugin.war;

import ru.shadam.initializer.plugin.jvm.config.Resource;
import ru.shadam.initializer.plugin.war.config.Filter;
import ru.shadam.initializer.plugin.war.config.Servlet;
import ru.shadam.initializer.plugin.war.config.WebConfig;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sala
 */
public class WarConfig {
    private WebConfig webConfig = WebConfig.WEBXML;
    private String webAppDir = Paths.get("src", "main", "webapp").toString();
    //
    private List<Resource> resources = new ArrayList<>();
    private WebInfXmlResource webInfXmlResource = new WebInfXmlResource("WEB-INF", "web.xml", "templates/war/web.xml.ftl");
    //
    private List<Filter> filters = new ArrayList<>();
    private List<Servlet> servlets = new ArrayList<>();


    public WebConfig getWebConfig() {
        return webConfig;
    }

    public void setWebConfig(WebConfig webConfig) {
        this.webConfig = webConfig;
    }

    public String getWebAppDir() {
        return webAppDir;
    }

    public void setWebAppDir(String webAppDir) {
        this.webAppDir = webAppDir;
    }

    public WebInfXmlResource getWebInfXmlResource() {
        return webInfXmlResource;
    }

    public void setWebInfXmlResource(WebInfXmlResource webInfXmlResource) {
        this.webInfXmlResource = webInfXmlResource;
    }

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }

    public List<Filter> getFilters() {
        return filters;
    }

    public void setFilters(List<Filter> filters) {
        this.filters = filters;
    }

    public List<Servlet> getServlets() {
        return servlets;
    }

    public void setServlets(List<Servlet> servlets) {
        this.servlets = servlets;
    }

    public class WebInfXmlResource extends Resource {
        public WebInfXmlResource(String packageName, String fileName, String templateName) {
            super(packageName, fileName, templateName);
        }

        public List<Filter> getFilters() {
            return filters;
        }

        public void setFilters(List<Filter> filters) {
            WarConfig.this.filters = filters;
        }

        public List<Servlet> getServlets() {
            return servlets;
        }

        public void setServlets(List<Servlet> servlets) {
            WarConfig.this.servlets = servlets;
        }
    }
}
